package com.ncusoft.ncudocmsapp.utils;

import com.ncusoft.ncudocmsapp.ClientApplication;
import com.ncusoft.ncudocmsapp.repository.DatabaseHelper;

public class SharedVar {

    private static ClientApplication clientApplication;
    public static void setClientApplication(ClientApplication clientApplication){
        SharedVar.clientApplication=clientApplication;
    }
    public static ClientApplication getClientApplication(){
        return clientApplication;
    }
}
