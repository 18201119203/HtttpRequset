package com.example.htttprequset.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SigUtils {

    /**
     * 生成签名
     * @param map
     * @return
     */
    public static String getSign(Map<String, String> map) {

        String result = "";
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            // 构造签名键值对的格式
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getKey() != null || item.getKey() != "") {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (!(val == "" || val == null)) {
                        sb.append("&" + key + "=" + val );
                    }
                }

            }
//			sb.append(PropertyManager.getProperty("SIGNKEY"));
            //result = sb.toString();
            result = sb.toString().replaceFirst("&","");
            //进行MD5加密
            result = MD5Utils.encode("297bf3d9-d9da-4295-bd63-0fc41053f005"+result).toUpperCase();
        } catch (Exception e) {
            return null;
        }
        return result;
    }


}
