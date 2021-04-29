package com.library.mymusic.UI.PLAYLIST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.library.mymusic.R;
import com.library.mymusic.UI.FRAGMENTS.MyDialogFragment;

import java.io.File;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    Button btn_next,btn_prev,btn_pause,btn_shuffle,btn_menu;
    SeekBar songbar;
    TextView Txtsongname;

    static MediaPlayer mediaPlayer;
    int position;
    String sname;
    ArrayList<File>  mySongs;
    Thread updateseekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);



        final boolean[] isShuffle = {false};
        //final boolean[] isRepeat = {false};

        btn_next = (Button) findViewById(R.id.forward);
        btn_prev = (Button) findViewById(R.id.backward);
        btn_pause = (Button) findViewById(R.id.pause);
        btn_shuffle = (Button) findViewById(R.id.shuffle);
        btn_menu = (Button) findViewById(R.id.menu);
        Txtsongname = (TextView) findViewById(R.id.songname);
        songbar = (SeekBar) findViewById(R.id.seekbar);

        updateseekbar = new Thread(){
            @Override
            public void run() {
                int totalduration = mediaPlayer.getDuration();
                int currentpos=0;

                while (currentpos < totalduration){
                    try {
                        sleep(500);
                        currentpos = mediaPlayer.getCurrentPosition();
                        songbar.setProgress(currentpos);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };

        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i=getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName().toString();
        String songname = i.getStringExtra("songname");

        Txtsongname.setText(songname);
        Txtsongname.setSelected(true);

        position = bundle.getInt("pos",0);

        Uri u = Uri.parse(mySongs.get(position).toString());

        mediaPlayer = MediaPlayer.create(getApplicationContext(),u);

        mediaPlayer.start();
        songbar.setMax(mediaPlayer.getDuration());

        songbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mediaPlayer.seekTo(songbar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songbar.setMax(mediaPlayer.getDuration());

                if (mediaPlayer.isPlaying()){
                    btn_pause.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                }
                else{
                    btn_pause.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                }
            }
        });

        btn_shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isShuffle[0]) {
                    btn_shuffle.setBackgroundResource(R.drawable.ic_baseline_shuffle_24);


                } else {
                    btn_shuffle.setBackgroundResource(R.drawable.ic_baseline_shuffles);
                }
                isShuffle[0] = !isShuffle[0];
            }
        });





        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position+1)%mySongs.size());

                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                sname = mySongs.get(position).getName().toString();
                Txtsongname.setText(sname);


                mediaPlayer.start();
                btn_pause.setBackgroundResource(R.drawable.ic_baseline_pause_24);
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position-1)<0)?(mySongs.size()-1):(position-1);

                Uri u = Uri.parse(mySongs.get(position).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                sname = mySongs.get(position).getName().toString();
                Txtsongname.setText(sname);

                mediaPlayer.start();
            }
        });


        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialogFragment().show(getSupportFragmentManager(),"MyDialogFragement");
            }
        });
    }


}