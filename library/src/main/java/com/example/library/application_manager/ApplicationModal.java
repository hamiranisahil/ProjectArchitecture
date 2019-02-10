package com.example.library.application_manager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationModal {

    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("sourceDir")
    @Expose
    private String sourceDir;
    @SerializedName("size")
    @Expose
    private Long size;
    @SerializedName("sizeReadable")
    @Expose
    private String sizeReadable;
    @SerializedName("launchActivity")
    @Expose
    private String launchActivity;
    @SerializedName("isSystemApp")
    @Expose
    private Boolean isSystemApp;
    @SerializedName("versionName")
    @Expose
    private String versionName;
    @SerializedName("versionCode")
    @Expose
    private String versionCode;
    @SerializedName("applicationLabel")
    @Expose
    private String applicationLabel;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("firstInstallTime")
    @Expose
    private String firstInstallTime;
    @SerializedName("lastUpdateTime")
    @Expose
    private String lastUpdateTime;
    @SerializedName("dataDir")
    @Expose
    private String dataDir;
    @SerializedName("deviceProtectedDataDir")
    @Expose
    private String deviceProtectedDataDir;
    @SerializedName("app_link")
    @Expose
    private String appLink;
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSizeReadable() {
        return sizeReadable;
    }

    public void setSizeReadable(String sizeReadable) {
        this.sizeReadable = sizeReadable;
    }

    public String getLaunchActivity() {
        return launchActivity;
    }

    public void setLaunchActivity(String launchActivity) {
        this.launchActivity = launchActivity;
    }

    public Boolean getIsSystemApp() {
        return isSystemApp;
    }

    public void setIsSystemApp(Boolean isSystemApp) {
        this.isSystemApp = isSystemApp;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getApplicationLabel() {
        return applicationLabel;
    }

    public void setApplicationLabel(String applicationLabel) {
        this.applicationLabel = applicationLabel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(String firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDataDir() {
        return dataDir;
    }

    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    public String getDeviceProtectedDataDir() {
        return deviceProtectedDataDir;
    }

    public void setDeviceProtectedDataDir(String deviceProtectedDataDir) {
        this.deviceProtectedDataDir = deviceProtectedDataDir;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

}