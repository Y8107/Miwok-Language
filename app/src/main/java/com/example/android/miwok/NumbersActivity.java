package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    MediaPlayer mediaplayer;

    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaplayer.pause();
                mediaplayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaplayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
//        ArrayList<String> words=new ArrayList<String>();
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));
//        words.add(new Word("two"));
//        words.add(new Word("three");
//        words.add(new Word("four");
//        words.add(new Word("five");
//        words.add(new Word("six");
//        words.add(new Word("seven");
//        words.add(new Word("eight");
//        words.add(new Word("nine");
//        words.add(new Word("ten");
//        LinearLayout rootView=(LinearLayout)findViewById(R.id.rootview);
//        int i=0;
//        while(i<10) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//            i++;
//        }

//        for(int j=0;j<words.size();j++){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(j));
//            rootView.addView(wordView);
//        }
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this,R.layout.list_item, words);

        WordAdapter adapter = new WordAdapter(this, 0, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();
                mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                int result=mAudioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaplayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceID());
                    mediaplayer.start();
                    mediaplayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

        /**
         * Clean up the media player by releasing its resources.
         */
        private void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mediaplayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mediaplayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mediaplayer = null;

                mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
            }
        }


}

