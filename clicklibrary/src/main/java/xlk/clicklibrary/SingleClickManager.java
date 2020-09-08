package xlk.clicklibrary;

/**
 * @author by xlk
 * @date 2020/8/31
 * @desc 可在Application的onCreate中调用方法进行全局配置
 */
public class SingleClickManager {
    static int clickInterval = 500;
    static boolean isGlobalClick = false;

    private SingleClickManager() {
    }

    /**
     * 设置全局点击事件防重间隔
     *
     * @param clickIntervalMillis 点击间隔毫秒值
     */
    public static void setClickInterval(int clickIntervalMillis) {
        clickInterval = clickIntervalMillis;
    }

    /**
     * 设置是否启用全局
     * @param globalClick true=不需要添加注解也有效
     */
    public static void setGlobal(boolean globalClick) {
        isGlobalClick = globalClick;
    }
}
