package com.example.dokdofamily01.Data;

/**
 * Created by hero on 2017-11-22.
 */

public class SubTitleDataTest {

    public SubTitleDataTest(int id, int time){
        this.finishtime = time;
        this.subtitle = id;
    }

    public int getSubTitle() {
        return subtitle;
    }

    public int getFinishTime() {
        return finishtime;
    }

    int subtitle;
    int finishtime;


}
