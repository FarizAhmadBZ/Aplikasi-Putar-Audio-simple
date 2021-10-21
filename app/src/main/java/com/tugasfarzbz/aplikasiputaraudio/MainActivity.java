package com.tugasfarzbz.aplikasiputaraudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    int currentSongIndex;
    boolean musicPlayed = false;
    MediaPlayer mp;
    GifImageView gifImg;
    TextView txtAudio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentSongIndex = 0;
        gifImg = findViewById(R.id.gifImg);
        txtAudio = findViewById(R.id.txtAudio);
        Button btnPlayPause = findViewById(R.id.btnPlayPause);
        gifImg.setVisibility(View.INVISIBLE);


        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.nggyu);
        songs.add(1, R.raw.stickbug);
        songs.add(2, R.raw.coconutmalld);

        mp = MediaPlayer.create(getApplicationContext(), songs.get(currentSongIndex));

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicPlayed == false){
                    btnPlayPause.setText("Pause");
                    mp.start();
                    musicPlayed = true;
                    setSongData();
                    gifImg.setVisibility(View.VISIBLE);

                }else{
                    btnPlayPause.setText("Play");
                    mp.pause();
                    musicPlayed = false;
                    gifImg.setVisibility(View.INVISIBLE);
                    txtAudio.setText("Audio Player");
                }
            }
        });
        Button btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp.stop();
                    mp.prepare();
                    mp.seekTo(0);
                    btnPlayPause.setText("Play");
                    musicPlayed = false;
                    gifImg.setVisibility(View.INVISIBLE);
                    txtAudio.setText("Audio Player");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSongIndex == songs.size()-1){
                    Toast.makeText(getApplicationContext(), "Lagu terakhir, tidak dapat maju lagi", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        currentSongIndex ++;
                        if (mp.isPlaying()){
                            mp.stop();
                        }
                        mp = MediaPlayer.create(getApplicationContext(), songs.get(currentSongIndex));
                        if (musicPlayed == true){
                            mp.start();
                            setSongData();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSongIndex == 0){
                    Toast.makeText(getApplicationContext(), "Lagu pertama, tidak dapat mundur", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        currentSongIndex --;
                        if (mp.isPlaying()){
                            mp.stop();
                        }
                        mp = MediaPlayer.create(getApplicationContext(), songs.get(currentSongIndex));
                        if (musicPlayed == true){
                            mp.start();
                            setSongData();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void setSongData() {
        if (currentSongIndex==0){
            txtAudio.setText("RICKROLLED");
            gifImg.setImageResource(R.drawable.rick);
        }else if (currentSongIndex == 1){
            txtAudio.setText("STICKBUGGED");
            gifImg.setImageResource(R.drawable.stickbug);
        }
        else if (currentSongIndex == 2){
            txtAudio.setText("COCONUT MALLD");
            gifImg.setImageResource(R.drawable.coconutmalld);
        }
    }

}