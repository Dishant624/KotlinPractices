package com.example.kotlinpractices.newTask;

import android.util.Log;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MBTilesFileHandler {

    private static final String TAG = "MBTilesFile";

    private static final String MetaDataTableName = "metadata";

    private static final String MetaDataColumn1 = "name";

    private static final String MetaDataColumn2 = "value";

    private static Connection connection;


    static void alterTable(File destinationMBTileFile, String tableName) {


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


                        if (table.equals(tableName) && type.equals("table")) {
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

            if (!tableExists(connection, "metadata")) {

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

        }

    }

    static String getMinZoomLevel(File mbTile) {

        String minZoomLevel = "";
        ResultSet resultSet =
                null;
        try {
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
                e.printStackTrace();
            }
        }


        return minZoomLevel;
    }


    static String getMaxZoomLevel(File mbTile) {

        String maxZoomLevel = "";
        ResultSet resultSet =
                null;
        try {
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
                e.printStackTrace();
            }
        }


        return maxZoomLevel;
    }


}
