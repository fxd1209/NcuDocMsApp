package com.ncusoft.ncudocmsapp.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.R;

import javax.xml.datatype.Duration;

//成功弹出层
public class ToastUtil {
    public enum ToastType{
        SUCCESS, //成功
        FAIL,    //失败
        WARNING, //警告
        DOUBT    //疑问
    }

    public static Toast initToast(Activity activity,ToastType toastType,String msg,int duration, Point pos){
        LayoutInflater inflater =activity.getLayoutInflater();
        View layout=inflater.inflate(R.layout.toast_msg,null);
        ImageView icon=(ImageView)layout.findViewById(R.id.toast_msg_icon);
        TextView text=(TextView)layout.findViewById(R.id.toast_msg_text);
        switch (toastType){
            case SUCCESS:
                icon.setImageResource(R.drawable.icon_success);
                break;
            case FAIL:
                icon.setImageResource(R.drawable.icon_fail);
                break;
            case WARNING:
                icon.setImageResource(R.drawable.icon_warning);
                break;
            case DOUBT:
                icon.setImageResource(R.drawable.icon_doubt);
                break;
            default:
                icon.setImageResource(R.drawable.icon_success);
                break;
        }
        text.setText(msg);
        Toast toast=new Toast(activity);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER,pos.x,pos.y);
        return toast;
    }
}
