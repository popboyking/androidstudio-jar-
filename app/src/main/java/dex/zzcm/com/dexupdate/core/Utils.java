/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package dex.zzcm.com.dexupdate.core;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Utils {
    private static final int BUF_SIZE = 2048;

    public static boolean prepareDex(Context context, File dexInternalStoragePath, File  from,boolean isCoreTemp) {
        BufferedInputStream bis = null;
        OutputStream dexWriter = null;

        try {

            File jarTemp;
            if(isCoreTemp){
                bis = new BufferedInputStream(context.getAssets().open(DexManager.DEX_PATH));
            }else{
                jarTemp = new File(from.getAbsolutePath());
                bis =new BufferedInputStream(new FileInputStream(jarTemp));
            }

            bis = new BufferedInputStream(bis);
            dexWriter = new BufferedOutputStream(new FileOutputStream(dexInternalStoragePath));
            byte[] buf = new byte[BUF_SIZE];
            int len;
            while ((len = bis.read(buf, 0, BUF_SIZE)) > 0) {
                dexWriter.write(buf, 0, len);
            }
            dexWriter.close();
            bis.close();
            return true;
        } catch (IOException e) {
            if (dexWriter != null) {
                try {
                    dexWriter.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            return false;
        }
    }

}
