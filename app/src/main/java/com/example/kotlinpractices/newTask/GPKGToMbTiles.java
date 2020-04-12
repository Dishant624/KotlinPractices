package com.example.kotlinpractices.newTask;

import android.util.Log;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GPKGToMbTiles {


    private static final String TAG = "GPKGToMbTiles";

    private static Connection connection;

    private static HashSet<String> _MBTileFileNameList = new HashSet<>();

    private GPKGToMbTiles() { }

    public static void convertGPKGToMbTilesFiles(File _GPKGFile) throws MBTilesException {
        connection = SQLHelper.establishConnection(_GPKGFile);

        String fileDir = _GPKGFile.getParent();

        Log.d(TAG, "convertGPKGToMbTiles: file name" + _GPKGFile.getName());
        Log.d(TAG, "convertGPKGToMbTiles: file parent" + _GPKGFile.getParent());

        int filenameIndex = _GPKGFile.getName().lastIndexOf(".");

        String fileNameIndex = _GPKGFile.getName().substring(0, filenameIndex);

        List<String> tableNameList = getTableNameValueFromGpkgContent();

        for (String tableName : tableNameList) {

            String newFileName = fileNameIndex + "-" + tableName + ".mbtile";

            Log.d(TAG, "convertGPKGToMbTiles: " + newFileName);

            _MBTileFileNameList.add(newFileName);

            String directoryPath = _GPKGFile.getParent();

            File _MBTileFile = new File(directoryPath + "/" + newFileName);

            deleteAndCreateNewFile(_MBTileFile);

            copyGPKGFileToMbtilesFile(_GPKGFile, _MBTileFile);

            MBTilesFileHandler.alterAndDropTables(_MBTileFile, tableName);

            MBTilesFileHandler.createMetaDataTable(_MBTileFile);

            addMetaDataValues(newFileName, _MBTileFile);

            MBTilesFileHandler.createPowerOfTwoTable(_MBTileFile);

            MBTilesFileHandler.addPowerOfTwoValues(_MBTileFile);

            MBTilesFileHandler.createTile2Table(_MBTileFile);

            MBTilesFileHandler.dropTilesAndPowerOfTwoTables(_MBTileFile);

            MBTilesFileHandler.alterTile2TableName(_MBTileFile);


        }


        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void deleteAndCreateNewFile(File _MBTileFile) {
        if(_MBTileFile.exists()){
            boolean isDeleted =_MBTileFile.delete();
            Log.d(TAG, "convertGPKGToMbTilesFiles: isDeleted="+isDeleted);
        }

        try {
            boolean isCreated = _MBTileFile.createNewFile();
            Log.d(TAG, "convertGPKGToMbTilesFiles: "+isCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> getTableNameValueFromGpkgContent() throws MBTilesException {

        List<String> tableNameList = new ArrayList<>();
        ResultSet resultSet =
                SQLHelper.executeQuery
                        (connection, "select table_name from gpkg_contents");

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String tableName = resultSet.getString(1);
                    tableNameList.add(tableName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return tableNameList;
        }

        return Collections.emptyList();
    }


    private static void copyGPKGFileToMbtilesFile(File source, File destination) {


        FileChannel inputStream = null;
        FileChannel outputStream = null;
        try {
            inputStream = new FileInputStream(source).getChannel();

            outputStream = new FileOutputStream(destination).getChannel();


            outputStream.transferFrom(inputStream, 0, inputStream.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static void addMetaDataValues(String newFileName, File _MBTileFile) {
        MBTilesFileHandler.deleteAllMetaData(_MBTileFile);

        MBTilesFileHandler.addMetaData(_MBTileFile, "Name", newFileName);

        MBTilesFileHandler.addMetaData(_MBTileFile, "description", newFileName);

        String blobFileFormat = MBTilesFileHandler.getBlobFileFormat(_MBTileFile);

        Log.d(TAG, "addMetaDataValues: blobFileFormat = "+blobFileFormat);
        MBTilesFileHandler.addMetaData(_MBTileFile, "format", blobFileFormat);

        String minZoom = MBTilesFileHandler.getMinZoomLevel(_MBTileFile);

        MBTilesFileHandler.addMetaData(_MBTileFile, "Minzoom", minZoom);

        String maxZoom = MBTilesFileHandler.getMaxZoomLevel(_MBTileFile);

        MBTilesFileHandler.addMetaData(_MBTileFile, "Maxzoom", maxZoom);
    }


    private static class MBTilesFileHandler {

        private static final String TAG = "MBTilesFile";

        private static final String MetaDataTableName = "metadata";

        private static final String MetaDataColumn1 = "name";

        private static final String MetaDataColumn2 = "value";


        private static final String PowersOfTwoTableName = "PowersOfTwo";

        private static final String PowersOfTwoColumn1 = "exponent";

        private static final String PowersOfTwoColumn2 = "value";

        private static final String Tile2TableName = "tiles2";


        private static Connection connection;

        private MBTilesFileHandler() { }

        static void alterAndDropTables(File destinationMBTileFile, String tableName) {


            Log.d(TAG, "alterTable: " + tableName);
            try {
                connection = SQLHelper.establishConnection(destinationMBTileFile);

                ResultSet resultSet = SQLHelper.executeQuery(connection, "select name,type from sqlite_master");

                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {
                            String table = resultSet.getString(1);
                            String type = resultSet.getString(2);

                            Log.d(TAG, "alterTable: " + table + " type= " + type);


                            if (!table.equals("tiles") && table.equals(tableName) && type.equals("table")) {
                                Log.d(TAG, "alterTable: alter =" + table);

                                SQLHelper.execute(connection, "alter table '" + tableName + "' rename to 'tiles'");
                            }

                            if (!table.equals(tableName) && type.equals("table")) {
                                Log.d(TAG, "alterTable: drop table" + table);

                                SQLHelper.execute(connection, "drop table if exists " + table);

                            }
                            if (type.equals("view")) {
                                Log.d(TAG, "alterTable: drop view" + table);
                                SQLHelper.execute(connection, "drop view if exists" + table);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (connection != null)
                                connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }

            } catch (MBTilesException e) {
                e.printStackTrace();
            }
        }


        private static boolean tableExists(Connection connection, String tableName) throws MBTilesException {
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "';";
            ResultSet resultSet = SQLHelper.executeQuery(connection, sql);
            try {
                boolean tableExists = resultSet.next();
                resultSet.close();
                return tableExists;
            } catch (SQLException e) {
                throw new MBTilesException("Close Result Set", e);
            }
        }


        static void createMetaDataTable(File mbtileFile) {

            try {
                connection = SQLHelper.establishConnection(mbtileFile);

                if (!tableExists(connection, MetaDataTableName)) {

                    String sql = "CREATE TABLE " + MetaDataTableName + " (\n" +
                            "" + MetaDataColumn1 + " TEXT ,\n" +
                            "" + MetaDataColumn2 + " TEXT \n" +
                            ")";

                    SQLHelper.execute(connection, sql);

                }

            } catch (MBTilesException e) {
                e.printStackTrace();
                Log.d(TAG, "createMetaDataTable: " + e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


        static void deleteAllMetaData(File mbTileFile) {

            try {
                connection =SQLHelper.establishConnection(mbTileFile);

                if (!tableExists(connection, MetaDataTableName))
                    SQLHelper.execute(connection,"Delete from "+ MetaDataTableName);

            } catch (MBTilesException e) {
                e.printStackTrace();
            }finally {
                if(connection !=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());
                    }
                }
            }
        }

        static void addMetaData(File mbtileFile, String name, String value) {

            try {
                connection = SQLHelper.establishConnection(mbtileFile);

                PreparedStatement stmt = connection.prepareStatement
                        ("INSERT INTO " + MetaDataTableName + " (" + MetaDataColumn1 + "," + MetaDataColumn2 + ") VALUES(?,?)");

                stmt.setString(1, name);

                stmt.setString(2, value);

                stmt.execute();

                stmt.close();

            } catch (SQLException e) {
                Log.d(TAG, "addMetaData: " + e.getMessage());
            } catch (MBTilesException e) {
                Log.d("Error------", "addMetaData: " + e.getMessage());

            }finally {
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());
                    }
                }
            }

        }

        static String getMinZoomLevel(File mbTile) {

            String minZoomLevel = "";
            ResultSet resultSet =
                    null;

            try {
                connection = SQLHelper.establishConnection(mbTile);

                resultSet = SQLHelper.executeQuery
                        (connection, "select min(zoom_level) from tiles");

                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {
                            minZoomLevel = resultSet.getString(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (MBTilesException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }


            return minZoomLevel;
        }


        static String getMaxZoomLevel(File mbTile) {

            String maxZoomLevel = "";
            ResultSet resultSet =
                    null;
            try {
                connection = SQLHelper.establishConnection(mbTile);
                resultSet = SQLHelper.executeQuery
                        (connection, "select max(zoom_level) from tiles");

                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {
                            maxZoomLevel = resultSet.getString(1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (MBTilesException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }


            return maxZoomLevel;
        }


        static void createPowerOfTwoTable(File mbtileFile) {

            try {
                connection = SQLHelper.establishConnection(mbtileFile);

                if (!tableExists(connection, PowersOfTwoTableName)) {

                    String sql = "CREATE TABLE " + PowersOfTwoTableName + " (\n" +
                            "" + PowersOfTwoColumn1 + " INTEGER ,\n" +
                            "" + PowersOfTwoColumn2 + " TEXT \n" +
                            ")";

                    SQLHelper.execute(connection, sql);

                }

            } catch (MBTilesException e) {
                Log.d(TAG, "createPowerOfTwoTable: " + e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }
        }

        static void addPowerOfTwoValues(File mbTileFile) {

            try {
                connection = SQLHelper.establishConnection(mbTileFile);

                if (tableExists(connection, PowersOfTwoTableName)) {

                    SQLHelper.execute(connection, "delete from " + PowersOfTwoTableName);

                    PreparedStatement stmt = connection.prepareStatement
                            ("INSERT INTO " + PowersOfTwoTableName + " (" + PowersOfTwoColumn1 + "," + PowersOfTwoColumn2 + ") VALUES(?,?)");

                    for (int i = 0; i <= 32; i++) {
                        stmt.setInt(1, i);

                        stmt.setString(2, String.valueOf(Double.valueOf(Math.pow(2, i)).intValue()));

                        stmt.execute();
                    }

                    stmt.close();
                }

            } catch (SQLException e) {
                Log.d(TAG, "addPowerOfTwoValues: " + e.getMessage());
            } catch (MBTilesException e) {
                Log.d(TAG, "addPowerOfTwoValues: " + e.getMessage());

            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }
        }

        static void createTile2Table(File mbTileFile) {
            try {
                connection = SQLHelper.establishConnection(mbTileFile);

                if (!tableExists(connection, Tile2TableName)) {

                    String sql = "CREATE TABLE " + Tile2TableName + " AS SELECT " +
                            "(SELECT " + PowersOfTwoColumn2 + " FROM " + PowersOfTwoTableName + "" +
                            " WHERE " + PowersOfTwoColumn1 + " = zoom_level)" +
                            " - tile_row - 1 AS tile_row, zoom_level, tile_column, tile_data" +
                            " FROM tiles;";
                    SQLHelper.execute(connection, sql);

                }

            } catch (MBTilesException e) {
                Log.d(TAG, "createTile2Table: " + e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }
        }

        static void dropTilesAndPowerOfTwoTables(File mbTileFile) {
            try {
                connection = SQLHelper.establishConnection(mbTileFile);

                SQLHelper.execute(connection, "drop table if exists tiles");

                SQLHelper.execute(connection, "drop table if exists " + PowersOfTwoTableName + "");

            } catch (MBTilesException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }
        }

        static void alterTile2TableName(File mbTileFile) {
            try {
                connection = SQLHelper.establishConnection(mbTileFile);

                SQLHelper.execute(connection, "alter table '" + Tile2TableName + "' rename to 'tiles'");

            } catch (MBTilesException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }

        }

        static String getBlobFileFormat(File mbTileFile) {

            String mimeType = "";
            String fileExtension = "";

            try {
                connection = SQLHelper.establishConnection(mbTileFile);

                ResultSet resultSet = SQLHelper.executeQuery(connection,
                        "select tile_data from tiles LIMIT 1");


                if (resultSet != null) {
                    try {
                        while (resultSet.next()) {
                            Blob tile_data = resultSet.getBlob(1);

                            byte[] tileDataArray = tile_data.getBytes(1, (int) tile_data.length());

                            InputStream is = new ByteArrayInputStream(tileDataArray);

                            //Find out image type

                            try {
                                //mimeType is something like "image/jpeg"
                                mimeType = URLConnection.guessContentTypeFromStream(is);
                                Log.d(TAG, "getBlobFileFormat: mimeType=" + mimeType);

                                String delimiter = "[/]";
                                String[] tokens = mimeType.split(delimiter);
                                fileExtension = tokens[1];

                            } catch (IOException ioException) {
                                Log.d(TAG, "getBlobFileFormat: " + ioException.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            resultSet.close();
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (MBTilesException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    Log.d(TAG, "deleteAllMetaData: connection = "+e.getMessage());

                }
            }
            return fileExtension;
        }

    }

}
