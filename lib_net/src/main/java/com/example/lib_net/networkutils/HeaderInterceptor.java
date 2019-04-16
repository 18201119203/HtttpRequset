package com.example.lib_net.networkutils;

import com.example.lib_net.sputils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder build = request.newBuilder();
        Request build1 = build

                     .build();

        Response proceed = chain.proceed(build1);

        return proceed;
    }

}
