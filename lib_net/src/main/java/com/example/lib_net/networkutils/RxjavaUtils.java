package com.example.lib_net.networkutils;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxjavaUtils {

    public static <T> ObservableTransformer<T,T> schdulers(){

        return new ObservableTransformer<T,T>(){
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        };

    }

}
