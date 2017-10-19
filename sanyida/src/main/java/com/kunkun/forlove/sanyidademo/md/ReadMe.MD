# 首先是我们在AIUI平台的设置 #

我们在一前就设置过,我们需要的就是设置和下载对应的sdk

AIIUI的地址: http://aiui.xfyun.cn/myApp/index/58ed7c97/
 之后就是sdk的下载:和文件的导入


 首先是对jar 的导入设置,我们首先设置的就是 切换到project目录下面的,我们可以看到系统已经给我们创建好的libs文件

 我们直接将jar包复制在这里面,之后在打开 project  structure  选择对应的module 之后依赖相对应的jar包进去.


之后就是对so库的处理: 我们首先的处理就是在我们切换的Project目录先,
第一种方法: 我们需要在 project文件夹的下面 右击新建一个新的文件夹 之后就是我们需要的设置的文件夹名称就是 jniLibs
我们的操作就是将第三方的sdk中的sdk的so文件复制进去,之后AS会自己加载第三方的so库进去


之后的操作就是对assets的处理: 我们在处理的时候我们需要将我们下载的sdk中的文件直接复制进去:
 我们的处理就是我们切换到Android目录下面,我们直接new 我们看到最后就会看到  Folder 最右边就是显示的需要 assets Folder
 选中它,直接点击finish就可以了! 此时是在Android目录下,我们需要的操作就是我们切换的project目录下面,在我们的project目录
 下面就可以找到我们需要的文件.

 复制下载的sdk的res文件下的vad文件.



之后就是关于AIUI 的开发文档,具体的地址就是: http://aiui.xfyun.cn/help/startGuid#1-1

之后就是权限的申请和设置:

<!--需要的权限-->

    <!--连接网络权限，用于执行云端语音能力 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <!--获取手机录音机使用权限，听写、识别、语义理解需要用到此权限 -->
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <!--读取网络信息状态 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <!--获取当前wifi状态 -->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <!--允许程序改变网络连接状态 -->
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
    <!--读取手机信息权限 -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <!--读取联系人权限，上传联系人需要用到此权限 -->
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <!--外存储写权限，构建语法需要用到此权限 -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <!--外存储读权限，构建语法需要用到此权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <!--配置权限，用来记录应用配置信息 -->
    <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
    <!--手机定位信息，用来为语义等功能提供定位，提供更精准的服务-->
    <!--定位信息是敏感信息，可通过Setting.setLocationEnable(false)关闭定位请求 -->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

 之后就是混淆的设置代码: proguard.cfg
 -keep class com.iflytek.**{*;}
 -keepattributes Signature




# 初始化的操作 #

初始化直接在我们自己的application中的设置.  创建完毕记得在清单文件中去申明


4.AIUI接口文档详见：http://doc.xfyun.cn/aiui_mobile/
5.通用msc 文档请详见：http://doc.xfyun.cn/msc_android/










