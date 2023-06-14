package com.trashgo.app;

import android.content.res.Resources;

/*by dotom*/
public class ploggingDataRecyclerViewItem {
    private String mImgName;
    private String mDate;

    public String getImgName() {
        return mImgName;
    }

    public void setImgName(String imgName) {
        this.mImgName = imgName;
    }

    public void setDate(String mainText) {
        this.mDate = mainText;
    }

    public String getDate() {
        return mDate;
    }
}
