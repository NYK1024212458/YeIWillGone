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

 关于Glide的快速上手的记录:

 最简单的使用就是:  Glide.with(mContext).load(ImageUrl).into(ivShowlove);

 第一个with设置的是上下文, 第二个设置的是需要加载的地址,into设置的是需要加载在什么控件上面.

 我们首先看看第一部分那就是我们需要看的with部分: 我们知道我们在with的部分设置的是一个上下文context;

 with设置的上下文的部分有六种,分别是Activity和Context和Fragment和FragmentActivity和View和一个Android.APP.fragment
 区别在于,我们设置with之后返回的是一个RequestManager,是用来管理请求的,而是会对当前 Context 的生命周期做监听，
 来管理 Glide 自身的图片加载的请求。如果我们设置的上下文是当前的Activity,图片加载比较多,我们在加载的时候关闭了这个
 Activiry,此时的glide会监听到这个页面关闭,不会再去加载这个页面的图片加载,会取消掉!

  推荐的with的对象是 : Fragment > Activity > Context

  看看第二大块,关于load()加载资源,可以是本地资源,也可以是网络资源和Drawable


  第三块就是into() 加载下来的图片是设置给谁使用的!

## glide的详细使用细节 ##

>最长使用的是3.8.0版本的glide;

glide常见的三种展位图:

- placeholder ：指定加载前显示的图片资源。
- error：指定加载失败显示的图片资源。
- fallback：指定传递加载资源为 null 的时候，显示的图片资源。

Glide.with(PictureActivity.this)
                .load(ImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.err)
                .fallback(R.mipmap.fallback)
                .into(ivShowlove);

Glide中关于图片缩放的控制:

- centerCrop()  
- fitCenter()

结果如何: 直接上图:

对应的代码: 

	 Glide.with(mContext)
	                .load(BigImageUrl)
	                .placeholder(R.mipmap.ic_launcher)
	                .error(R.mipmap.err)
	                .fallback(R.mipmap.fallback)
	                .centerCrop()
	                .into(ivShowlove);


![](https://i.imgur.com/7fNoVqX.png)

下面的对应的是: fitcenter的展示图片


	 Glide.with(mContext)
		                .load(BigImageUrl)
		                .placeholder(R.mipmap.ic_launcher)
		                .error(R.mipmap.err)
		                .fallback(R.mipmap.fallback)
		                .fitCenter()
		                .into(ivShowlove);

![](https://i.imgur.com/0jtn9Ny.png)


Glide对缓冲的控制:

三级缓冲: 硬盘缓冲,内存缓冲 ,网络缓冲 

首先是对于内存缓冲的话:

对于内存缓存而言，只有有或者没有的情况，所以 Glide 只提供了一个 skipMemoryCache() 方法，它可以传递一个 Boolean 的值，用于指定是否跳过内存，默认情况下是 false ，表示需要内存缓存。

    .skipMemoryCache(true)// 设置是否跳过内存缓冲  默认是false 是不跳过内存缓冲的

关于磁盘缓冲:

    .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置的硬盘缓冲的设置  此时是禁止使用硬盘缓冲的!

    public enum DiskCacheStrategy {
    /** Caches with both {@link #SOURCE} and {@link #RESULT}. */
    ALL(true, true),
    /** Saves no data to cache. */
    NONE(false, false),
    /** Saves just the original data to cache. */
    SOURCE(true, false),
    /** Saves the media item after all transformations to cache. */
    RESULT(false, true);

    private final boolean cacheSource;
    private final boolean cacheResult;

    DiskCacheStrategy(boolean cacheSource, boolean cacheResult) {
        this.cacheSource = cacheSource;
        this.cacheResult = cacheResult;
    }

// 具体的解释

	ALL：缓存所有类型的图片（默认行为）。
	NONE ：禁用磁盘缓存。
	SOURCE ： 只缓存全尺寸的原图。
	RESULT ：只缓存压缩后的图片。

Glide中加载优先级

>对于同一个页面，如果需要在多个地方都加载线上图片，必然会存在一个优先级的问题。例如：正常来说，背景图是比其他图片优先级更高的图片。

>Glide 是可以在加载中，对当前加载的图片，调整加载的优先级的。需要使用 priority() 方法，它可以接受一个 Priority 的枚举类型，包含四种值：LOW（低）、HIGH（高）、NORMAL（普通）、IMMEDIATE（立即）。

>**可以在我们需要的时候，对其进行配置，但是它并不影响用 Glide 加载的图片的显示顺序，只是用于 Glide 在加载图片的时候一个优化请求的参数而已，并不影响最终显示的顺序。**


     Glide.with(mContext)
                .load(BigImageUrl)    // 加载的地址
                .placeholder(R.mipmap.ic_launcher)  //  正在加载的时候展示的图片
                .error(R.mipmap.err)  // 加载是被的时候展示的图片
                .fallback(R.mipmap.fallback) // 设置的是在loadURL为空的时候展示的图片
                .centerCrop() // 设置图片的伸缩
                .skipMemoryCache(true)// 设置是否跳过内存缓冲  默认是false 是不跳过内存缓冲的
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置的硬盘缓冲的设置  此时是禁止使用硬盘缓冲的!
                .priority(Priority.HIGH) // 设置glide的请求的优先级,不会影响最后的显示的顺序
                .into(ivShowlove);

Glide的加载动画:

它可以使用 crossFade() 方法进行配置，如果不特殊处理，默认它是开启的，并且本身默认动画的时长是 300ms。

本身就会重载很多的方法,crossFade修改的一般是指定的是加载的动画和加载的时长,
要是不满意动画,我们可以animate方法来加载我们的自定义的动画.

        //   .crossFade(600) // 设置在加载动画的时候动画的而设置,有多个方法的重载.
        // 默认是开启的,实质是对动画的一个加载.  我们在此的设置设置的是动画的时长是600ms
        // .dontAnimate()  // 这都是来禁止使用动画的方法
        //.dontTransform() // 禁止使用动画的方法
        Glide.with(mContext)
                .load(BigImageUrl)    // 加载的地址
                .placeholder(R.mipmap.ic_launcher)  //  正在加载的时候展示的图片
                .error(R.mipmap.err)  // 加载是被的时候展示的图片
                .fallback(R.mipmap.fallback) // 设置的是在loadURL为空的时候展示的图片
                .centerCrop() // 设置图片的伸缩
                .skipMemoryCache(true)// 设置是否跳过内存缓冲  默认是false 是不跳过内存缓冲的
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置的硬盘缓冲的设置  此时是禁止使用硬盘缓冲的!
                .priority(Priority.HIGH) // 设置glide的请求的优先级,不会影响最后的显示的顺序
                .animate(animator)
                .into(ivShowlove);

                // 这是自定义的动画
              ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
                        @Override
                        public void animate(View view) {
                            view.setAlpha(0f);
                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                            objectAnimator.setDuration(2500);
                            objectAnimator.start();
                        }
                    };

Glide支持加载gif和视频

//  gif的地址是:
http://kunkun5love.com/wordpress/wp-content/uploads/2017/10/loadgif.gif


我们直接来加载gif图片,也可以判断加载的是不是gif图片
  加载的使用和加载网上的图片是一个原理,只不过我们可以使用asGif()来判断加载的是不是gif图片.

  有的时候地址是一个gif地址,我们不需要他它自己播放,需要的是直接展示头一帧,我们直接使用asBitmap()来使用

    Glide.with(mContext)
                  .load(LoadGif)    // 加载的地址
                  // .asGif()   // 判断加载的是不是gif图片
                  .asBitmap()  // 这个方法就是在加载gif图片的时候
                  .placeholder(R.mipmap.ic_launcher)  //  正在加载的时候展示的图片
                  .error(R.mipmap.err)  // 加载是被的时候展示的图片
                  .fallback(R.mipmap.fallback) // 设置的是在loadURL为空的时候展示的图片
                  .centerCrop() // 设置图片的伸缩
                  .skipMemoryCache(true)// 设置是否跳过内存缓冲  默认是false 是不跳过内存缓冲的
                  .diskCacheStrategy(DiskCacheStrategy.NONE) // 设置的硬盘缓冲的设置  此时是禁止使用硬盘缓冲的!
                  .priority(Priority.HIGH) // 设置glide的请求的优先级,不会影响最后的显示的顺序
                  .animate(animator)
                  .into(ivShowlove);


glide加载视频并不是太好,注意的一点就是glide只是支持本地的视频.


Glide对加载图片的监听:

一般而言，如果我们需要监听图片加载错误的原因，可以在 onException() 中做处理。

需要注意的是，这两个方法的返回值，最好都是 false，因为如果返回 true ，
将表示你已经处理了这次的事件，而 Glide 将不会再做额外的处理。例如，如果 onException() 返回了 true 的话，在图片加载失败之后，error() 中设置的图片，
并不会被显示，因为 Glide 认为开发者已经在外部对这个错误进行了处理。



























      




