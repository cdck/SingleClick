package xlk.singleclick;

import android.app.Application;

import xlk.clicklibrary.SingleClickManager;

/**
 * @author xlk
 * @date 2020/8/31
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SingleClickManager.setGlobal(true);
        SingleClickManager.setClickInterval(600);
    }
}
