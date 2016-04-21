package dex.zzcm.com.dexupdate;

import android.app.Application;

import dex.zzcm.com.dexupdate.core.DexManager;

/**
 * Created by zhu on 2016/4/19.
 */
public class DexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DexManager.loadDex(this);

    }
}
