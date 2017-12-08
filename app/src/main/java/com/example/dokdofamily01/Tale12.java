package com.example.dokdofamily01;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dokdofamily01.Data.SubTitleData;

import java.util.ArrayList;

import static com.example.dokdofamily01.TaleActivity.homeKeyFlag;
import static com.example.dokdofamily01.TaleActivity.screenFlag;
import static com.example.dokdofamily01.TaleActivity.subtitleTextView;

/**
 * Created by heronation on 2017-11-06.
 */

public class Tale12 extends BaseFragment {



    boolean isAttached = false;
    boolean isHint;
    MediaPlayer mp = null;
    MusicController musicController;

    ArrayList<SubTitleData> subtitleList;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isAttached = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isHint = isVisibleToUser;
        super.setUserVisibleHint(isVisibleToUser);
        if(isAttached ){
            if (isVisibleToUser) {
                System.out.println("PlayByHint");
                soundPlayFunc();
            } else {
                if (musicController != null) {
                    musicController.getMp().release();
                }
            }
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        xml = R.layout.tale12;

        subtitleTextView.setText(null);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        if (isHint && !homeKeyFlag && screenFlag) {
            soundPlayFunc();
        }
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (musicController != null) {
            musicController.getMp().release();
            musicController = null;
        }
    }

    @Override
    public void bindViews() {
        super.bindViews();
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void setAnimation() {
        super.setAnimation();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

    public void soundPlayFunc(){
        musicController = new MusicController(getActivity(), R.raw.scene_12);
        subtitleList = new ArrayList<>();
        subtitleList = musicController.makeSubTitleList(
                new String[]{"찰랑찰랑~ ","1500"},
                new String[]{"언제나 부지런한 오징어 이모의 세모난 머리가 바다 위로 쑤욱~ 올라와요.", "8000"},
                new String[]{"별아 나랑 혹돔굴에 가보자!","12000"},
                new String[]{"우와~ 혹돔 삼촌도 만나요?","16500"},
                new String[]{"오징어 이모는 쭉쭉 긴 다리로 별이의 팔과 다리를 잡고서 ","23700"},
                new String[]{"하나 둘~ 하나 둘~ 준비운동을 시켜요. ","28600"}
        );
        musicController.excuteAsync();
        mp = musicController.getMp();

    }


}
