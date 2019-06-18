package com.ncusoft.ncudocmsapp.activitys;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.ncusoft.ncudocmsapp.R;

/**
 * 自定义对话框类
 */
public class DetailDialog extends Dialog implements View.OnClickListener {
    private TextView tvMsg;
    private Button btnOk;
    private String msg;

    public DetailDialog(Context context, String message) {
        super(context);
        this.msg=message;
    }

    /**
     *    该函数是在Dialog里面的
     *    和Activity差不多
     */

    @Override
    protected void onCreate(Bundle savedInstamceState){
        super.onCreate(savedInstamceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail);
        btnOk=(Button)findViewById(R.id.btn_ok);
        tvMsg=(TextView)findViewById(R.id.tv_msg);
        btnOk.setOnClickListener(this);
        tvMsg.setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_ok:
                dismiss();
                break;
            default:
                break;
        }
    }
}
