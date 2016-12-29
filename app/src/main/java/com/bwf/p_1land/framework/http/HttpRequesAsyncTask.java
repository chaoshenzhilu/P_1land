package com.bwf.p_1land.framework.http;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.bwf.p_1land.framework.base.Basebean;
import com.bwf.p_1land.framework.util.LogUtils;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/28.
 */

public class HttpRequesAsyncTask extends AsyncTask<Request,Integer,String>{
    private Activity activity;
    private NetCallBack callBack;
    public HttpRequesAsyncTask(Activity activity,NetCallBack callBack){
        this.activity=activity;
        this.callBack=callBack;
    }
    private HttpURLConnection getHttpURLConnection(String urlstr,int timeOut){
        URL url=null;
        HttpURLConnection connection=null;
        try{
            url=new URL(urlstr);
            connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(timeOut);
            connection.setDoOutput(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    protected String doInBackground(Request... requests) {
        Request request=requests[0];
        StringBuffer sb=new StringBuffer();
        try{
            HttpURLConnection connection= getHttpURLConnection(request.getUrl(),request.getRequestTimeOut());
            OutputStream out=connection.getOutputStream();
            if(request.getParams()!=null){
                StringBuffer param=new StringBuffer();
                for(Map.Entry<String,String> entry:request.getParams().entrySet()){
                    param.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                param.substring(1,param.length());
                byte[] bytes=param.toString().getBytes();
                out.write(bytes);
            }
            if(connection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                return null;
            }
            InputStream in=connection.getInputStream();
            String temp=null;
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            while((temp=bs.readLine())!=null){
                sb.append(temp);
            }
            connection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if(activity!=null&&!activity.isFinishing()){
            if(!TextUtils.isEmpty(result)){
                if(result.equals("网络不可用")){
                    if(callBack!=null){
                        callBack.onFail(result);
                    }
                    return;
                }
                if(callBack!=null){
                    Basebean basebean= new Gson().fromJson(result,Basebean.class);
                    LogUtils.e(basebean.toString());
                    LogUtils.e(basebean.resultStatus);
                    if(!basebean.resultStatus.equals("200")){
                        if(callBack!=null){
                            callBack.onFail("数据解析失败");
                        }
                        return;
                    }
                    if(callBack!=null){
                        callBack.onSuccess(result);
                    }
                }
            }
        }

        super.onPostExecute(result);
    }
     public interface NetCallBack{
        void onSuccess(String result);
        void onFail(String errMessage);
    }
}
