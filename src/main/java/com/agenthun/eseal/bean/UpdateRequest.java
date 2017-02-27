package com.agenthun.eseal.bean;

/**
 * Created by agenthun on 2017/2/28.
 */
public class UpdateRequest {
    String token;
    String module;
    String versionCode;
    String versionNumber;
    String url;
    String size;
    String updateContent;

    public UpdateRequest(String module, String versionCode, String versionNumber, String url, String size, String updateContent) {
        this.module = module;
        this.versionCode = versionCode;
        this.versionNumber = versionNumber;
        this.url = url;
        this.size = size;
        this.updateContent = updateContent;
    }

    public UpdateRequest(String token, String module, String versionCode, String versionNumber, String url, String size, String updateContent) {
        this.token = token;
        this.module = module;
        this.versionCode = versionCode;
        this.versionNumber = versionNumber;
        this.url = url;
        this.size = size;
        this.updateContent = updateContent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "token='" + token + '\'' +
                ", module='" + module + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionNumber='" + versionNumber + '\'' +
                ", url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", updateContent='" + updateContent + '\'' +
                '}';
    }
}
