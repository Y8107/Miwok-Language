package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
public class Word{
    private String mDefaultTranslation;

    private String mMiwokTranslation;


    private static final int NO_IMAGE=-1;
    private int mImageResourceID=NO_IMAGE;
    private int mAudioResourceID;


    public Word(String defaultTranslation, String miwokTranslation,int audioResourceID){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mAudioResourceID=audioResourceID;
    }

    public Word(String defaultTranslation, String miwokTranslation,int ImageResourceID,int audioResourceID){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceID=ImageResourceID;
        mAudioResourceID=audioResourceID;
    }
    public String  getMiwokTranslation(){
            return mMiwokTranslation;
    }

    public String getDefaultTranslation(){
            return mDefaultTranslation;
    }
    public int getImageResourceID(){
        return  mImageResourceID;
    }
    public boolean hasImage(){
        return mImageResourceID!=NO_IMAGE;
    }

    public int getAudioResourceID() {
        return mAudioResourceID;
    }


}
