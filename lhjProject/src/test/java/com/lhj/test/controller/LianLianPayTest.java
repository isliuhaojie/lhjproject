package com.lhj.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhj.project.utils.HttpClientUtil;
import com.lhj.project.utils.Md5Algorithm;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * describe
 * author liuhj18
 * Date 2018/6/5
 * version 1.00
 */
public class LianLianPayTest {
    static String PAY_URL = "http://cashier.lianlianpay.com/payment/bankgateway.htm"; // 连连支付WEB收银台支付服务地址
    static String MD5_KEY = "201408071000001545test_20140812";

    @Test
    public void payTest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String data = format.format(c.getTime());
        JSONObject riskItemObj = new JSONObject();
        riskItemObj.put("user_info_full_name", "你好");
        riskItemObj.put("frms_ware_category", "1999");

        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("version", "2.5");
        sParaTemp.put("oid_partner", "201408071000001545");
        sParaTemp.put("user_id", "123456789");
        sParaTemp.put("sign_type", "MD5");
        sParaTemp.put("busi_partner", "101001");
        sParaTemp.put("no_order", getTradeNo());
        sParaTemp.put("dt_order", data);
        sParaTemp.put("name_goods", "连连测试");
        sParaTemp.put("info_order", "连连测试描述");
        sParaTemp.put("money_order", "0.01");
        sParaTemp.put("notify_url", "http://http://1630o2x420.iok.la//");
        sParaTemp.put("url_return", "http://http://1630o2x420.iok.la//");
        sParaTemp.put("timestamp", data);
        sParaTemp.put("risk_item", riskItemObj.toString());
        sParaTemp.put("req_url", PAY_URL);

        String sign = addSignMD5(JSON.parseObject(JSON.toJSONString(sParaTemp)), MD5_KEY);
        sParaTemp.put("sign", sign);

        System.out.println(sParaTemp.toString());

        String s = HttpClientUtil.doPost(PAY_URL, sParaTemp);
        System.out.println(s);
    }


    /**
     * MD5加签名
     *
     * @param reqObj
     * @param md5_key
     * @return
     * @author guoyx
     */
    private static String addSignMD5(JSONObject reqObj, String md5_key) {
        System.out.println("进入商户[" + reqObj.getString("oid_partner")
                + "]MD5加签名");
        if (reqObj == null) {
            return "";
        }
        // 生成待签名串
        String sign_src = genSignData(reqObj);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]加签原串"
                + sign_src);
        sign_src += "&key=" + md5_key;
        try {
            return Md5Algorithm.getInstance().md5Digest(
                    sign_src.getBytes("utf-8"));
        } catch (Exception e) {
            System.out.println("商户[" + reqObj.getString("oid_partner")
                    + "]MD5加签名异常" + e.getMessage());
            return "";
        }
    }


    /**
     * 生成待签名串
     *
     * @param jsonObject
     * @return
     * @author guoyx
     */
    public static String genSignData(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if ("sign".equals(key)) {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (isnull(value)) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * str空判断
     *
     * @param str
     * @return
     * @author guoyx
     */
    public static boolean isnull(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
            return true;
        } else
            return false;
    }

    public static String getTradeNo() {
        long time = System.currentTimeMillis();
        return "TEST" + time;
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        String insert_date = format.format(c.getTime());
        System.out.println(insert_date);
    }
}
