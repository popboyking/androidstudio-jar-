package dex.zzcm.com.dexupdate;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import dex.zzcm.com.dexupdate.core.DexManager;
import dex.zzcm.com.dexupdate.core.Utils;
import dex.zzcm.com.dexupdate.net.DownFile;
import dodola.hotfixlib.HotFix;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btn_fix,btn_getFix;
    TextView txt_source;
//    SourceSystemManager ssm ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        ssm = new SourceSystemManager();
        this.setContentView(R.layout.activity_main);

        txt_source = (TextView) findViewById(R.id.txt_source);
//        txt_source.setText("修复前:"+ssm.getSource());
        btn_fix = (Button) findViewById(R.id.fix);

        btn_fix.setOnClickListener(this);

        btn_getFix = (Button) findViewById(R.id.getfixJar);
        btn_getFix.setOnClickListener(this);


//        Toast.makeText(this,SourceSystemManager.printName(),Toast.LENGTH_LONG).show();

//        UnCatch.getInstance().start(this);


    }


    @Override
    public void onClick(View view) {


        if(view == btn_fix){

            File f = new File ("/storage/emulated/0/updateDex/path.jar");
            if(f.exists()){
                DexManager.loadDex(this,f,DexManager.USER);
            }

            SourceSystemManager ssm = new SourceSystemManager();
            txt_source.setText("修复后:"+ssm.getSource());
            Toast.makeText(this,SourceSystemManager.printName(),Toast.LENGTH_LONG).show();
        }

        if(view == btn_getFix){
            String url = "http://192.168.0.48:8080/examples/pathDex.jar";//自己随便搭两个tomcat将就用下
            DownFile downFile = new DownFile();
            downFile.downFile(new DownFile.IFileOK() {
                @Override
                public void success(File file) {

                    Log.e("down","下载成功:"+file.getAbsolutePath());
                    //加载修复文件

                }
            },url);
        }
    }

}
