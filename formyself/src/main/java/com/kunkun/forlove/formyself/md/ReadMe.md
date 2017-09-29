# 只是想好好做一个项目 #

目标:
   
1. 运动的背景和登录界面
2. 迅雷的倒计时(直接到验证阶段)
3. 权限的申请(在6.0上面能运行)
4. 沉浸式的处理
5. 黄油刀的使用直接和自己博客的书写 
6. 自定义控件知识点的总结和自定义属性的获取方式和区别,以及经常使用到的方法
7. view的绘制流程和view的刷新机制
8. retorfit的使用和博客学习笔记的记录
9. glide的使用和笔记的记载
10. 第三方的roundImageView的使用的里面核心查看
11. 集成这个app
12. 定位功能和正确地址的发送
13. javascript的学习和最近适合编程电脑配置的了解和提前打算
14. 看书看书看书(你买的起西装,可是你的气质撑的住吗)
15. 新手指引的操作的集成

AppUtils
DensityUtils
DialogFragmentHelper
FucUtil
HttpUtils
KeyBoardUtils
L
LogUtils
MusicUtil
NetUtils
PackageUtils
ScreenUtils
SDCardUtils
SPUtils
T


# 视频背景的实现 #

我们的视频是直接在apk中包含的! 
新建一个driectory 输入文件夹的名称raw;复制我们需要的视频:

获取这个视频是最主要的:

    //设置视频的播放地址
        kuncd_main_show.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

直接上布局:
ps 设置button的background为Android系统提供的透明颜色就可以去掉button自带的颜色:

    ![](https://i.imgur.com/UK4JjLz.png)

# butterknife的使用 #

[https://github.com/JakeWharton/butterknife](https://github.com/JakeWharton/butterknife "直接是github上面的连接地址")


AS上butterknife的插件的下载和使用:

![](https://i.imgur.com/62Sqgwq.png)

AS上面的依赖:我们翻译一下子官方的文档:

Field and method binding for Android views which uses annotation processing to generate boilerplate code for you.
(Android视图的字段和方法绑定，它使用注释处理为您生成样板代码。)


 1.    Eliminate findViewById calls by using @BindView on fields.(Eliminate就是消除的意思---大体就是消除了FindViewByID 使用注解@bingview来替代)
1.     Group multiple views in a list or array. Operate on all of them at once with actions, setters, or properties.(在列表或数组中组合多个视图。使用action、setter或properties对它们进行操作。)
1.     Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others.(通过@ onclick和其他的注解方法消除匿名的内部类。)
1.     Eliminate resource lookups by using resource annotations on fields.(在字段上使用资源注释消除资源查找。)


下面的值butterknife的官方介绍:

    [http://jakewharton.github.io/butterknife/](http://jakewharton.github.io/butterknife/)

Resource Binding(绑定资源)

Bind pre-defined resources with @BindBool, @BindColor, @BindDimen, @BindDrawable, @BindInt, @BindString, which binds an R.bool ID (or your specified type) to its corresponding field.(绑定预先定义的资源与@ bindbool，@ bindcolor，@ binddimen，@ binddrawable，@ bindint，@ bindstring，它绑定了一个R.bool ID(或您指定的类型)到它对应的字段。)

    class ExampleActivity extends Activity {
	  @BindString(R.string.title) String title;
	  @BindDrawable(R.drawable.graphic) Drawable graphic;
	  @BindColor(R.color.red) int red; // int or ColorStateList field
	  @BindDimen(R.dimen.spacer) Float spacer; // int (for pixel size) or float (for exact value) field
	  // ...
	}

Non-Activity Binding

You can also perform binding on arbitrary objects by supplying your own view root.(您还可以通过提供自己的根视图来对任意对象执行绑定。)

    public class FancyFragment extends Fragment {
	  @BindView(R.id.button1) Button button1;
	  @BindView(R.id.button2) Button button2;
	
	  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fancy_fragment, container, false);
	    ButterKnife.bind(this, view);
	    // TODO Use fields...
	    return view;
	  }
	}

Another use is simplifying the view holder pattern inside of a list adapter.(另一个用法是将view holder模式简化为一个列表适配器。)

    public class MyAdapter extends BaseAdapter {
	  @Override public View getView(int position, View view, ViewGroup parent) {
	    ViewHolder holder;
	    if (view != null) {
	      holder = (ViewHolder) view.getTag();
	    } else {
	      view = inflater.inflate(R.layout.whatever, parent, false);
	      holder = new ViewHolder(view); // 构造方法里面实现了绑定
	      view.setTag(holder);
	    }
	
	    holder.name.setText("John Doe");
	    // etc...
	
	    return view;
	  }
	
	  static class ViewHolder {
	    @BindView(R.id.title) TextView name;
	    @BindView(R.id.job_title) TextView jobTitle;
	
	    public ViewHolder(View view) {
	      ButterKnife.bind(this, view);
	    }
	  }
	}

You can see this implementation in action in the provided sample.

Calls to ButterKnife.bind can be made anywhere you would otherwise(否则) put findViewById calls.

Other provided binding APIs:

- 坚持学习!
- 坚持看书!
- 坚持多吃饭!
- 坚持早点休息!
- 坚持在上班的时候不想你!
- 坚持着我所坚持的执着!


1. Bind arbitrary objects using an activity as the view root. If you use a pattern like MVC you can bind the controller using its activity with ButterKnife.bind(this, activity).(使用活动作为根视图绑定任意对象。如果你使用像MVC这样的模式，你可以用它的活动来绑定控制器。绑定(活动)。)
1.     Bind a view's children into fields using ButterKnife.bind(this). If you use <merge> tags in a layout and inflate in a custom view constructor you can call this immediately after. Alternatively, custom view types inflated from XML can use it in the onFinishInflate() callback.(使用ButterKnife.bind将视图的孩子绑定到字段。如果您在布局中使用< merge >标记，并在自定义视图构造函数中打气，您可以立即调用它。或者，自定义视图类型可以在onfinishinflate()回调中使用它。)


# 具体的使用 #

是自己module的gradle的依赖:

    compile 'com.jakewharton:butterknife:8.8.1'
  	annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'

>To use Butter Knife in a library, add the plugin to your buildscript:
>在我们project的gradle的添加

	     buildscript {
	  repositories {
	    mavenCentral()
	   }
	  dependencies {
	    classpath 'com.jakewharton:butterknife-gradle-plugin:8.8.1'
	  }
	}
> 之后在我们的module中添加apply plugin: 'com.android.library'
> 
> apply plugin: 'com.jakewharton.butterknife'


总结一下子:第一步: 是在自己的moudle中依赖

    compile 'com.jakewharton:butterknife:8.8.1'
     annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'

之后就是在project的gradle中配置:

    classpath 'com.jakewharton:butterknife-gradle-plugin:8.8.1'

moudle中插入:

    apply plugin: 'com.jakewharton.butterknife'


## 具体的使用 ##

 选中布局使用alt + ins 快捷键 需要设置的点击事件进行点击选择就可以了!

导入我们使用的工具类:


https://meedamian.com/post/deuglifying-android-studio/  这是一个主题连接,还是很好使用的!


// 之后就是展示的你的一些信息,我们直接访问我们需要的网址使用retrofit和glide的使用!

http://116.196.91.100/wordpress/wp-content/uploads/2017/09/微信图片_20170929111751.jpg  //  图片的地址
http://kunkun5love.com/wordpress/wp-content/uploads/2017/09/321171.jpg

#  关于   Retrofit的使用我们直接查看github

使用的依赖

compile 'com.squareup.retrofit2:retrofit:2.3.0'

github的地址: https://github.com/square/retrofit

>注意一点就是不要忘记去权限的请求!

我们先处理6.0以上权限的请求问题;

我们需要的访问的地址就是上面的地址,关于Retrofit的优缺点我们使用一段时间在写相应blog



## 6.0的权限的请求问题

我们需要联网,6.0的权限不同的一点就是我们需要在清单文件中什么这个权限之后我们在检测是不
是真实的获取到我们的权限!
 我们直接使用第三方的框架来解决6.0权限问题!


我们来使用:
首先是清单文件中需要权限的声明:
之后是依赖:
  project的依赖:
    buildscript {
           // ...
         }

         allprojects {
           repositories {
             // 请添加如下一行
             maven { url 'https://jitpack.io' }
           }
         }

之后我们需要使用的module的依赖:

    dependencies {
      compile 'com.github.jokermonn:permissions4m:2.1.2-lib'
      annotationProcessor 'com.github.jokermonn:permissions4m:2.1.2-processor'
    }

>必须添加的二次权限的申请回调  在Activity或者是Fragment中设置的回调方法

      @Override
      public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
              grantResults) {
          Permissions4M.onRequestPermissionsResult(MainFragment.this, requestCode, grantResults);
          super.onRequestPermissionsResult(requestCode, permissions, grantResults);


      }

      记录一下子修改的快捷键: 关于自动补全的代码

      我们首先是在keymap 中搜索  basic\

![](https://i.imgur.com/z41RB8Z.png)

上面的图片是我们已经修改过的展示图片,AS默认的是 ctrl + 空格 习惯自己使用alt +/
来代替,再次记录一下子.

#glide加载图片框架

依赖吧! project的依赖!

        repositories {
          mavenCentral()
          maven { url 'https://maven.google.com' }
        }










      




