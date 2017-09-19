package kr.ac.kaist.resl.gs1_car_manager;

import android.graphics.drawable.Drawable;

/**
 * Created by Mac02 on 2015-12-08.
 */

public class IconTextItem {
    private Drawable mIcon;
    private String[] mData;
    private boolean mSelectable = true;

    public IconTextItem(Drawable mIcon, String[] mData) {
        this.mIcon = mIcon;
        this.mData = mData;
    }

    public IconTextItem(Drawable mIcon, String obj01, String obj02, String obj03) {
        this.mIcon = mIcon;
        mData = new String[3];
        mData[0] = obj01;
        mData[1] = obj02;
        mData[2] = obj03;
    }

    public boolean isSelectable(){
        return mSelectable;
    }

    public void setmSelectable(boolean mSelectable) {
        this.mSelectable = mSelectable;
    }

    public String[] getData(){
        return mData;
    }

    public String getData(int index){
        if (mData == null || index >= mData.length){
            return null;
        }
        return mData[index];
    }

    public void setData(String[] obj){
        mData = obj;
    }
    public void setIcon(Drawable Icon){
        mIcon = Icon;
    }
    public Drawable getIcon(){
        return mIcon;
    }
}