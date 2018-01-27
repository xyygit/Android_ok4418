package yann.uppermonitor.application;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.IOException;

import yann.uppermonitor.common.AppCrashHandler;
import yann.uppermonitor.utils.ExAppUtil;
import yann.uppermonitor.utils.ExCommonUtil;
import yann.uppermonitor.utils.ExDeviceUtil;
import yann.uppermonitor.utils.ExFileUtil;

/**
 * Created by yayun.xia on 2018/1/26.
 */

public class MyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCrashHandler.create();

        String processName = ExDeviceUtil.getInstance().getCurrentProcessPackageName(this);
        String packageName = getPackageName();
        if (!packageName.equals(processName)) return;

        mContext = getApplicationContext();
        resetApplicationContext();

        initApp();
    }

    private void initApp() {
        configAppPath();
    }

    protected void configAppPath() {
        File cacheFile, imageCacheFile, logFile;
        File cacheDir = getExternalCacheDir();
        if(!ExCommonUtil.isEmpty(cacheDir)) {
            ExAppConfig.checkFile = cacheDir.getAbsolutePath()+ "/check.tmp";
            try {
                new File(ExAppConfig.checkFile).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!ExCommonUtil.isEmpty(cacheDir) && ExFileUtil.getInstance().existSDcard(ExAppConfig.checkFile)) {
            cacheFile = new File(cacheDir.getAbsolutePath());
            imageCacheFile = new File(getExternalFilesDir("img").getAbsolutePath());
            logFile = new File(getExternalFilesDir("log").getAbsolutePath());
        } else {
            cacheFile = new File(ExAppUtil.getApplicationContext().getCacheDir().getAbsolutePath());
            imageCacheFile = new File(ExAppUtil.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "img");
            logFile = new File(ExAppUtil.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + "log");
        }

        ExAppConfig.appCachePath = cacheFile.getAbsolutePath();
        ExAppConfig.appImageCachePath = imageCacheFile.getAbsolutePath();
        ExAppConfig.appLogPath = logFile.getAbsolutePath();

        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }

        if (!imageCacheFile.exists()) {
            imageCacheFile.mkdirs();
        }


        if (!logFile.exists()) {
            logFile.mkdirs();
        }

    }

    public static void resetApplicationContext() {
        ExAppUtil.getInstance().setContext(mContext);
    }
}
