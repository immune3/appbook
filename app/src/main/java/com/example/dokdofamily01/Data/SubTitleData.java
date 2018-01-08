package com.example.dokdofamily01.Data;

/**
 * Created by hero on 2017-11-22.
 */

public class SubTitleData {

    public SubTitleData(String s, int i){
        this.finishtime = i;
        this.subtitle = s;
    }

    public SubTitleData(String s) {
        subtitle = s;
    }

    public String getSubTitle() {
        return subtitle;
    }

    public int getFinishTime() {
        return finishtime;
    }

    String subtitle;
    int finishtime;


}
