package com.kun.latesttechnologystudy.manager;

import com.kun.latesttechnologystudy.Constant.UrlConstant;
import com.kun.latesttechnologystudy.server.ServerApi;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/12/12.
 *
 * 网络请求框架的管理类
 * 第一步是创建retrofit的实例化,并提供单例
 */

public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private static Retrofit mRetrofit;
    private static Retrofit mDRetrofit;


    // 构造方法的私有

    private RetrofitManager() {

    }

    /**
     * 创建网络请求
     *
     * @param reqServer 网络接口
     * @param <T>       泛型
     * @return 返回网络接口对应的请求类
     */
    private  <T> T createReq(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }
    /**
     * 获取服务器网络请求接口对应的类
     *
     * @return 服务器网络请求接口对应的类
     */
    public ServerApi getServer() {
        return createReq(ServerApi.class);
    }

    /**
     * 返回线程安全的单例
     *
     * @return RetrofitManager单例
     */
    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    //在自定义Application中初始化
    public static void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(50, TimeUnit.SECONDS);
        builder.readTimeout(50, TimeUnit.SECONDS);
        builder.writeTimeout(50, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
