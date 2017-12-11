package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.AsyncTask;

/**
 * Created by hero on 2017-12-11.
 */

public class CheckMP extends AsyncTask<Void, Void, MediaPlayer> {

    MusicController mc;

    public CheckMP(MusicController musicController) {
        super();
        this.mc = musicController;
    }

    @Override
    protected void onPostExecute(MediaPlayer o) {
        super.onPostExecute(o);
        try {
            o.release();
        } catch (Exception e) {
            e.getStackTrace();
            doInBackground();
        }

    }

    @Override
    protected MediaPlayer doInBackground(Void... voids) {
        MediaPlayer mp_async;
        mp_async = mc.getMp();
        return mp_async;
    }
}



