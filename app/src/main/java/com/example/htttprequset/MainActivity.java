package com.example.htttprequset;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.htttprequset.api.ApiService;
import com.example.htttprequset.utils.SigUtils;
import com.example.lib_net.networkutils.RetrfitUtils;
import com.example.lib_net.networkutils.RxjavaUtils;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String KEY = "297bf3d9-d9da-4295-bd63-0fc41053f005";

    //请求体参数
    private String user_uuid = "0964685a-e5d7-4b2d-9b46-726213998edf";
    private String token = "3429b884-7d9e-441e-b376-8a765030094d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //网络请求
        NetRequest();

    }

    @SuppressLint("CheckResult")
    private void NetRequest() {

        String random = (Math.random() * 100)+"";
        String time = System.currentTimeMillis()+"";
        //获取签名文件
        String signature = getSignatureToString(random,time);
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("user_uuid",user_uuid);
        params.put("token",token);
        HashMap<String,String> heads = new HashMap<String,String>();
        heads.put("nonce", random);
        heads.put("timestamp",time);
        heads.put("signature",signature);

        //网络请求
        RetrfitUtils.initRetrfitUtils().createService(ApiService.class)
                .postVideo(heads,"v1/micvod/userVideoVote",params)
                .compose(RxjavaUtils.<Response<String>>schdulers())
                .subscribe(new Consumer<Response<String>>() {
                    @Override
                    public void accept(Response<String> stringResponse) throws Exception {
                        Toast.makeText(MainActivity.this,stringResponse.body(),Toast.LENGTH_LONG).show();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }


    private String getSignatureToString(String random,String time) {

        Map<String,String> map = new HashMap<>();
        map.put("nonce",random);
        map.put("timestamp",time);
        map.put("token",token);
        map.put("user_uuid",user_uuid);
        //获取签名
        String sign = SigUtils.getSign(map);
        System.out.print(sign);
        return sign;
    }


}
