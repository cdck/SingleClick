# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#指定保留所有注释
#-keepattributes *Annotation*
#关闭优化
-dontoptimize
#关闭混淆：混淆是默认开启的。混淆使类和类成员名称变成短的随机名
-dontobfuscate
#保持xlk.clicklibrary下面所有类文件，包括其子包里面类文件
#-keep class xlk.clicklibrary.
#-keep interface xlk.clicklibrary.