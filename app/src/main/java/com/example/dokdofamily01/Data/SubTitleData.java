package com.example.dokdofamily01.Data;

/**
 * Created by hero on 2017-11-21.
 */

public class SubTitleData {

    String subTitle;
    int finishTime;

    public SubTitleData(String subTitle, int finishTime) {
        this.subTitle = subTitle;
        this.finishTime = finishTime;
    }


    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


}
