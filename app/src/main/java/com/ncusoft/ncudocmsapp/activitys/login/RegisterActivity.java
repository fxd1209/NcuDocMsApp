package com.ncusoft.ncudocmsapp.activitys.login;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.R;
import com.ncusoft.ncudocmsapp.repository.login.UserDao;
import com.ncusoft.ncudocmsapp.utils.SharedVar;

public class RegisterActivity extends AppCompatActivity {

    EditText register_id;
    EditText register_password;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_register);

        ClientApplication clientApplication=(ClientApplication)getApplication();
        SharedVar.setClientApplication(clientApplication);

        register_id=(EditText)findViewById(R.id.edit_register_id);
        register_password=(EditText)findViewById(R.id.edit_register_password);

        save=(Button)findViewById(R.id.btn_register);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=register_id.getText().toString();
                String pwd=register_password.getText().toString();

                UserDao userDao=UserDao.getInstance();
                ContentValues contentValues=new ContentValues();
                contentValues.put("id",id);
                contentValues.put("password",pwd);
                contentValues.put("authority","admin");
                userDao.insert(SharedVar.getClientApplication().getDatabaseHelper(),contentValues);
           }
        });
    }
}
