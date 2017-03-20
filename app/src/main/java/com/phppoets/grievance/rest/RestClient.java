package com.phppoets.grievance.rest;

import com.phppoets.grievance.application.MyApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ashiq Uz Zoha on 9/13/15.
 * Dhrubok Infotech Services Ltd.
 * ashiq.ayon@gmail.com
 */
public class RestClient
{

    public static ApiInterface apiInterface;
    // public static String baseUrl = "http://shantismelting.com" ;
    //    public static String baseUrl= "http://api.androidhive.info";
    public static String baseUrl = "http://www.jeelwaterpark.com";

    public static ApiInterface getClient()
    {
        if(apiInterface == null)
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // Can be Level.BASIC, Level.HEADERS, or Level.BODY
            // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor).
                    addInterceptor(provideOfflineInterceptor()).addNetworkInterceptor(provideCacheInterceptor()).cache(provideCache());

            builder.connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES);
            builder.build();
            OkHttpClient okHttpClient = builder.build();

            Retrofit client =
                    new Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
            apiInterface = client.create(ApiInterface.class);
        }
        return apiInterface;
    }

    private static Cache provideCache()
    {
        Cache cache = null;
        try
        {
            cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "http-cache"), 10 * 1024 * 1024);
        }
        catch(Exception e)
        {

        }
        return cache;
    }

    public static Interceptor provideCacheInterceptor()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder().maxAge(2, TimeUnit.MINUTES).build();
                return response.newBuilder()
                               .removeHeader("Pragma")
                               .removeHeader("Cache-Control")
                               .header("Cache-Control", cacheControl.toString())
                               .build();
            }
        };
    }

    public static Interceptor provideOfflineInterceptor()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();

//                if(!InternetStatus.getInstance(MyApplication.getContext()).isOnline())
//                {
                    CacheControl cacheControl = new CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build();
                    request = request.newBuilder().cacheControl(cacheControl).build();
                //}

                return chain.proceed(request);
            }
        };
    }
}