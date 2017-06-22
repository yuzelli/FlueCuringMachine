package com.example.yuzelli.fluecuringmachine.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/6/17.
 */

public class WordBean implements Serializable{
    private String titleName;
    private String assetName;
    private String fileName;

    public WordBean(String titleName, String assetName, String fileName) {
        this.titleName = titleName;
        this.assetName = assetName;
        this.fileName = fileName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
