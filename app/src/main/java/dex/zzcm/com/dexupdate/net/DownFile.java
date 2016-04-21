package dex.zzcm.com.dexupdate.net;

import android.os.Environment;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/4/20.
 */
public class DownFile {

    String downPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"updateDex";

    public void downFile(final IFileOK fileOK, String url){

        File filePath = new File(downPath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }


        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(downPath, "path.jar")//
                {
                    @Override
                    public void inProgress(float progress, long total) {


                    }
                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e("down",e.getMessage());

                    }
                    @Override
                    public void onResponse(File file)
                    {
                        fileOK.success(file);
                    }
                });


    }

    public interface  IFileOK{

        void success(File file);

    }
}
