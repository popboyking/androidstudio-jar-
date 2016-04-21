package dex.zzcm.com.dexupdate;

import dex.zzcm.com.dexupdate.core.User;

/**
 * Created by zhu on 2016/4/19.
 */
public class SourceSystemManager {


    public String  getSource(){
        return "CGI_DEX_00000";
    }

    public static   String printName(){
        return new User().getName();
    }

}
