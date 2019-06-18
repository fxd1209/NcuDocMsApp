package com.ncusoft.ncudocmsapp.service.downloadservice;

/**
 * 下载过程中各种状态进行监听
 */
public interface DownloadListener {

    void onProgress(int progress);  //通知下载进度
    void onSuccess();               //下载成功
    void onFaild();
    void onPaused();
    void onCanceled();
}
