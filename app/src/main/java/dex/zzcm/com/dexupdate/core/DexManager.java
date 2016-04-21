package dex.zzcm.com.dexupdate.core;

import android.content.Context;
import android.util.Log;

import java.io.File;

import dodola.hotfixlib.HotFix;

/**
 * Created by zhu on 2016/4/1.
 */
public class DexManager
{

    public static final String DEX_PATH = "tempDex.jar";
    public static final String TEMP_COM = "dex.zzcm.com.codefix.CombomBox";//通过此类避免被打上 CLASS_ISPREVERIFIED
    public static final String USER = "dex.zzcm.com.pathmodule.User";



    public void getName(){
        Log.i("H",DexManager.class.getSimpleName()+"--->getName()" );
    }

    /**
     * 加载dex file 文件
     */
    public static void  loadDex(Context context){
        File dexfile = new File(context.getDir("dir",Context.MODE_PRIVATE),DEX_PATH);
        Utils.prepareDex(context,dexfile,null,true);
        HotFix.patch(context,dexfile.getAbsolutePath(),TEMP_COM);

        try {
            context.getClassLoader().loadClass(TEMP_COM);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void loadDex(Context context,File fromFile,String classFullName){

        File dexfile = new File(context.getDir("dir",Context.MODE_PRIVATE),fromFile.getName());
        Utils.prepareDex(context,dexfile,fromFile,false);
        HotFix.patch(context,dexfile.getAbsolutePath(),classFullName);

    }


}
