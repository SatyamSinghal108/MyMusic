package com.library.mymusic.UI.PLAYLIST;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.Manifest;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.library.mymusic.R;

import java.io.File;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    ListView lv;
    String[]  items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        lv = (ListView) findViewById(R.id.mylistview);
        runtimePermission();
    }

    public void runtimePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findSong(File file) {
        ArrayList<File> arrayList=new ArrayList<>();

        File[] files=file.listFiles();

        for (File singleFile: files){

            if (singleFile.isDirectory() && !singleFile.isHidden()){
                arrayList.addAll(findSong(singleFile));
            }
            else{
                if (singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".mp4")||singleFile.getName().endsWith(".wav")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }

    void Display(){
        final ArrayList<File> mySongs=findSong(Environment.getExternalStorageDirectory());

        items=new String[mySongs.size()];

        for (int i=0;i<mySongs.size();i++){
            items[i]=mySongs.get(i).getName().toString().replace(".mp3","");
        }
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String songname=lv.getItemAtPosition(position).toString();

                startActivity(new Intent(getApplicationContext(),PlayerActivity.class)
                        .putExtra("songs",mySongs).putExtra("songname",songname).putExtra("pos",position));
            }
        });
    }

}