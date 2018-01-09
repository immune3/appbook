package com.example.dokdofamily01;

import com.example.dokdofamily01.Data.SubTitleDataById;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.checkedAnimation;

/**
 * Created by mapl0 on 2018-01-08.
 */

public class SubtitleController {

    ArrayList<SubTitleDataById> subTitleList;
    CustomViewPager viewPager;
    int storyFlag = 0;

    public SubtitleController(CustomViewPager vp, int... sub) {
        makeSubTitleList(sub);
        setVP(vp);
    }

    private void makeSubTitleList(int... sub) {
        subTitleList = new ArrayList<SubTitleDataById>();

        for (int name : sub) {
            subTitleList.add(new SubTitleDataById(name));
        }

        TaleActivity.subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());

    }

    private void setVP(CustomViewPager vp) {
        viewPager = vp;
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
            TaleActivity.subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());
        } else if(checkedAnimation) viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    private boolean canFront() {
        TaleActivity.subtitleImageVIew.getId();
        if (storyFlag > 0) {
            return true;
        } else return false;

    }

    public void front() {
        if (canFront()) {
            storyFlag--;
            TaleActivity.subtitleImageVIew.setImageResource(subTitleList.get(storyFlag).getSubTitle());
        } else if(checkedAnimation) viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

}
