package com.example.kotlinpractices.newTask;


import android.content.Context;

import android.os.Build;
import android.os.Environment;


import java.io.File;



/**
 * Created by Droid on 09/06/2019.
 */

public class CommonUtils {
    public static final String appName = "MapDiscovery";

    public static final String HTML_PATH = "/httpd/";
    private static String returntext = "";


    public static final String STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().getPath();

    public static final String DOWNLOAD_DIRECTORY = STORAGE_DIRECTORY + "/" + appName + "/Downloads";

    public static String getFilesDirString(Context c) {
        File filesDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (Build.VERSION.SDK_INT <= 18)
                filesDir = new File(Environment.getExternalStorageDirectory()
                        + "/Android/data/"
                        + c.getPackageName()
                        + "/files"
                );
            else
                filesDir = c.getExternalFilesDir(null);
        } else {
            filesDir = c.getFilesDir();
        }
        return (filesDir != null) ? filesDir.getAbsolutePath() : "";
    }

}
