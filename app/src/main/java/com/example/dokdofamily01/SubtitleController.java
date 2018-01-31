package com.example.dokdofamily01;

import android.util.Log;

import com.example.dokdofamily01.Data.SubTitleDataById;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;

/**
 * Created by mapl0 on 2018-01-08.
 */

public class SubtitleController {

    private ArrayList<SubTitleDataById> subTitleList;
    private TaleActivity taleActivity;
    private CustomViewPager viewPager;
    private int storyFlag = 0;

    public SubtitleController(TaleActivity taleAct, CustomViewPager vp, int... sub) {

        setTaleActivity(taleAct);
        makeSubTitleList(sub);
        setVP(vp);
    }

    private void makeSubTitleList(int... sub) {
        subTitleList = new ArrayList<SubTitleDataById>();

        for (int name : sub) {
            subTitleList.add(new SubTitleDataById(name));
        }

        getTaleActivity().subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());

    }

    private void setVP(CustomViewPager vp) {
        viewPager = vp;
    }

    private void setTaleActivity(TaleActivity taleAct) {
        if(taleActivity == null) {
            this.taleActivity = taleAct;
        }
    }

    private TaleActivity getTaleActivity() {
        if(taleActivity != null) return taleActivity;
        else {
            Log.d("taleActivity", "NULL : setTaleActivity를 먼저 사용하여야 합니다.");
            return null;
        }
    }

    private boolean canNext() {
//        TaleActivity.subtitleImageVIew.getId();
        if (storyFlag < subTitleList.size() - 1) {
            return true;
        } else return false;

    }

    public void next() {
        if (canNext()) {
            storyFlag++;
            taleActivity.subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());
        } else if(checkedAnimation) viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void nextInActionUp() {
        if (canNext()) {
            storyFlag++;
            getTaleActivity().subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());
        }
    }

    private boolean canFront() {
        getTaleActivity().subtitleImageVIew.getId();
        if (storyFlag > 0) {
            return true;
        } else return false;

    }

    public void front() {
        if (canFront()) {
            storyFlag--;
            getTaleActivity().subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());
        } else if(checkedAnimation) viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

}
