[![](https://jitpack.io/v/cdck/SingleClick.svg)](https://jitpack.io/#cdck/SingleClick)
#### 依赖配置：
##### 1. Project下build.gradle配置
```
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.3'

        //Android开发平台的AOP框架:https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
    }
    
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
##### 2. 在需要的module下配置build.gradle

```
... 
//Android开发平台的AOP框架:https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
apply plugin: 'android-aspectjx'

android{
    ...
}
dependencies {
    implementation 'com.github.cdck:SingleClick:1.0'
}
```
#### 如何使用
**修改全局默认时间间隔（单位毫秒，默认是500）**
```
SingleClickManager.setClickInterval(1500);
```
**设置是否启用全局(默认`false`)，设置为`true`则没有添加`@SingleClick`注解的方法也会有`SingleClickManager.clickInterval`点击时长的限制**
```
SingleClickManager.setGlobal(true);
```
**注解参数说明**
```
public @interface SingleClick {
    //限制时长（单位毫秒）
    int value() default 1000;

    //方法中需要限制的按钮
    int[] specify() default {};

    //方法中需要取消限制的按钮
    int[] except() default {};
}
```
#### 使用方式：
```
/**
 * 或者将 except = {R.id.btn2} 替换成 specify = {R.id.btn1}
 * 按钮1每1.5秒只可点击1次 且按钮2可以随意点击
 */
@SingleClick(value = 1500, except = {R.id.btn2})
@Override
public void onClick(View v) {
    case R.id.btn1:
        Log.d("singleClick", "按钮1被点击");
        break;
    case R.id.btn2:
        Log.i("singleClick", "按钮2被点击");
        break;
}
```
或者
```
test_btn3.setOnClickListener(new View.OnClickListener() {
    //默认
    @SingleClick
    @Override
    public void onClick(View v) {
        Log.d("singleClick", "按钮3被点击");
    }
});
```
[该库是根据jarryleo/SingleClick库进行自定义修改](https://github.com/jarryleo/SingleClick)  
[AndroidAOP框架：gradle_plugin_android_aspectjx](https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx)

