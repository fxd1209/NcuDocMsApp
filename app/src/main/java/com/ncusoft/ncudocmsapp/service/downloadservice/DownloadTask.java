package com.ncusoft.ncudocmsapp.service.downloadservice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {
    public static final int TYPE_SUCCESS=0;
    public static final int TYPE_FAILED=1;
    public static final int TYPE_PAUSED=2;
    public static final int TYPE_CANCELED=3;

    private DownloadListener listener;
    private boolean isCanceled=false;
    private boolean isPaused=false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener){
        this.listener=listener;
    }
    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is=null;
        RandomAccessFile savedFiled=null;
        File file=null;
        try{
            long downloadedLength=0;//记录下下载的文件长度
            String downloadUrl=strings[0];
            String fileName=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory= Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(directory+fileName);
            if (file.exists()){
                downloadedLength=file.length();
            }
            long contentLength=getContentLength(downloadUrl);
            if (contentLength==0){
                return TYPE_FAILED;
            }else if (contentLength==downloadedLength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .addHeader("RANGE","bytes="+ downloadedLength +"-")
                    .url(downloadUrl)
                    .build();
            Response response=client.newCall(request).execute();
            if (response!=null){
                is=response.body().byteStream();
                savedFiled=new RandomAccessFile(file,"rw");
                savedFiled.seek(downloadedLength); //跳过已下载字节
                byte[] b=new byte[1024];
                int total=0;
                int len;
                while ((len=is.read(b)) !=-1){
                    if (isCanceled){
                        return TYPE_CANCELED;
                    }else if (isPaused){
                        return TYPE_PAUSED;
                    } else{
                        total+=len;
                        savedFiled.write(b,0,len);
                        //计算已经下载百分比
                        int progress=(int)((total+downloadedLength)*100/contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (is!=null){
                    is.close();
                }
                if (savedFiled!=null){
                    savedFiled.close();
                }
                if (isCanceled && file!=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return TYPE_FAILED;
        }
    }
    @Override
    protected void onProgressUpdate(Integer... values){
        int progress=values[0];
        if (progress>lastProgress){
            listener.onProgress(progress);
            lastProgress=progress;
        }
    }
    @Override
    protected void onPostExecute(Integer status){
        switch (status){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFaild();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
                break;
            default:break;
        }
    }
    public void pauseDownload(){
        isPaused=true;
    }
    public void cancelDownload(){
        isCanceled=true;
    }
    private long getContentLength(String downloadUrl) throws IOException{
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(downloadUrl)
                .build();
        //TODO:建立连接
        System.out.println("要建立连接");
        Response response=client.newCall(request).execute();
        //TODO:返回
        System.out.println("我是返回码:"+response.code());
        if (response!=null && response.isSuccessful()){
            long contentLength=response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
