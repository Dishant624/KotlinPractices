package com.example.kotlinpractices.newTask;

import android.util.Log;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
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


    public static void convertGPKGToMbTilesFiles(File gpkgFile) throws MBTilesException, IOException {
        connection = SQLHelper.establishConnection(gpkgFile);

        String fileDir = gpkgFile.getParent();

        Log.d(TAG, "convertGPKGToMbTiles: file name" + gpkgFile.getName());
        Log.d(TAG, "convertGPKGToMbTiles: file parent" + gpkgFile.getParent());

        int filenameIndex = gpkgFile.getName().lastIndexOf(".");

        String fileNameIndex = gpkgFile.getName().substring(0, filenameIndex);

        List<String> tableNameList = getTableName();

        for (String tableName : tableNameList) {

            String newFileName = fileNameIndex + "-" + tableName + ".mbtile";

            Log.d(TAG, "convertGPKGToMbTiles: " + newFileName);

            _MBTileFileNameList.add(newFileName);

            String directoryPath = gpkgFile.getParent();

            File destinationMBTileFile = new File(directoryPath + "/" + newFileName);

            copyGPKGFileToMbtilesFiles(gpkgFile, destinationMBTileFile);

            MBTilesFileHandler.alterTable(destinationMBTileFile, tableName);

            MBTilesFileHandler.createMetaDataTable(destinationMBTileFile);

            addMetaDataValues(newFileName, destinationMBTileFile);


        }


        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private static List<String> getTableName() throws MBTilesException {

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


    private static void copyGPKGFileToMbtilesFiles(File source, File destination) throws IOException {

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
            if (inputStream != null)
                inputStream.close();

            if (outputStream != null)
                outputStream.close();
        }

    }


    private static void addMetaDataValues(String newFileName, File destinationMBTileFile) {
        MBTilesFileHandler.addMetaData(destinationMBTileFile, "Name", newFileName);

        MBTilesFileHandler.addMetaData(destinationMBTileFile, "description", newFileName);

        MBTilesFileHandler.addMetaData(destinationMBTileFile, "format", " ");

        String minZoom = MBTilesFileHandler.getMinZoomLevel(destinationMBTileFile);

        MBTilesFileHandler.addMetaData(destinationMBTileFile, "Minzoom", minZoom);

        String maxZoom = MBTilesFileHandler.getMaxZoomLevel(destinationMBTileFile);

        MBTilesFileHandler.addMetaData(destinationMBTileFile, "Maxzoom", maxZoom);
    }

}
