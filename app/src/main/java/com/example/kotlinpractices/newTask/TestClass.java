package com.example.kotlinpractices.newTask;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.kotlinpractices.R;

import java.io.File;

public class TestClass extends AppCompatActivity {

    private static final String TAG = "TestClass";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        String directoryPath = CommonUtils.getFilesDirString(this);


        Log.d(TAG, "onCreate: directoryPath "+directoryPath);
        
        File gpkgFile = new File(directoryPath + "/IFR_AREA_FAA_AVIATION_CHART.gpkg");

        if(gpkgFile.exists()){
            try {
                GPKGToMbTiles.convertGPKGToMbTilesFiles(gpkgFile);
            } catch (MBTilesException e) {
                e.printStackTrace();
                Log.d(TAG, "onCreate: ");
            }
        }else{
            Toast.makeText(this, "file not exits", Toast.LENGTH_SHORT).show();
        }
        

    }


}
