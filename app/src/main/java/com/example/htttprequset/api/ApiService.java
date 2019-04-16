package com.example.htttprequset.api;

import java.util.HashMap;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {

    @POST
    @FormUrlEncoded
    Observable<Response<String>> postVideo(@HeaderMap HashMap<String,String> headers, @Url String url, @FieldMap HashMap<String, String> params);

}
