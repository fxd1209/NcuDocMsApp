package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.widget.Toast;

import com.ncusoft.ncudocmsapp.utils.ToastUtil;

public class LoginReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.initToast(context,ToastUtil.ToastType.WARNING,"网络状态发生改变",
                Toast.LENGTH_LONG,new Point(0,0)).show();
    }
}
