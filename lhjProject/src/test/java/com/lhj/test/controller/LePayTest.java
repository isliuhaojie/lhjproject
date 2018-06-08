package com.lhj.test.controller;

import com.lhj.project.utils.LePayProperry;
import com.lhj.project.utils.LePaySubmit;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * describe
 * author liuhj18
 * Date 2018/6/8
 * version 1.00
 */
public class LePayTest {

    private static String notify_url = LePayProperry.getInstance().getProperty("notify_url");
    private static String return_url = LePayProperry.getInstance().getProperty("return_url");
    private static String partner = LePayProperry.getInstance().getProperty("partner");
    private static String input_charset = LePayProperry.getInstance().getProperty("input_charset");


    // @Test
    public void wechatWep() {// 封装的微信公众号接口
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "wechat_wap_pay_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", "TEST15111769539112233");
        sParaTemp.put("subject", "TEST");
        // sParaTemp.put("openid", "oK7vQwzN8DnB_sEdRqgbDAXao0JE");
        sParaTemp.put("total_fee", "0.01");
        // sParaTemp.put("accrued_cost", "0.01");
        sParaTemp.put("body", "TEST");
        sParaTemp.put("price", "0.01");
        sParaTemp.put("it_b_pay", "20171121201000");
        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void aliWepPay() {// 封装的微信公众号接口
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", "TEST151117695393321");
        sParaTemp.put("subject", "TEST");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("body", "TEST");
        sParaTemp.put("price", "0.01");
        sParaTemp.put("it_b_pay", "20171121201000");
        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void wechatwep() {
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "wechat_jsapi_pay_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", getTradeNo());
        sParaTemp.put("subject", "TEST");
        sParaTemp.put("openid", "oLHCTjlszdkVOzx7u9HZoh4aAbeI");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("accrued_cost", "0.01");
        sParaTemp.put("body", "TEST");
        sParaTemp.put("price", "0.01");
        // sParaTemp.put("it_b_pay", "20170620201000");
        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @Test
    public void wechatH5() {
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "wechat_mwep_h5_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", "TEST1509523592679811");
        sParaTemp.put("subject", "TEST");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("spbill_create_ip", "10.116.44.242");
        sParaTemp.put("accrued_cost", "0.01");
        sParaTemp.put("body", "TEST");
        sParaTemp.put("price", "0.01");
        sParaTemp.put("wap_url", "http://pcsdshop.lenovo.com.cn");
        sParaTemp.put("wap_name", "联想商城");
        sParaTemp.put("show_url", return_url);
        // sParaTemp.put("it_b_pay", "20170620201000");
        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void qrcode() {
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "create_oncodepay_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", getTradeNo());
        sParaTemp.put("subject", "TEST+test");
        sParaTemp.put("total_fee", "1");
        sParaTemp.put("qr_pay_mode", "14");
        sParaTemp.put("payment_item", "10006");
        sParaTemp.put("body", "TEST");
        // sParaTemp.put("price", "0.01");
        // sParaTemp.put("it_b_pay", "20171121182000");
        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //@Test
    public void weChatH5PayTest() {
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "wechat_h5_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", getTradeNo());
        sParaTemp.put("subject", "TEST+test");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("qr_pay_mode", "14");
        sParaTemp.put("accrued_cost", "0.01");
        sParaTemp.put("body", "TEST");
        sParaTemp.put("show_url", return_url);

        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void qrPayTest() {
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        sParaTemp.put("_input_charset", input_charset);
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", "102323238");
        sParaTemp.put("out_trade_no", getTradeNo());
        sParaTemp.put("subject", "lipaknight inapp");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("qr_pay_mode", "4");
        sParaTemp.put("accrued_cost", "0.01");
        sParaTemp.put("body", "Unlock the full game and complete the knight’s adventure!");
        sParaTemp.put("show_url", return_url);

        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);

            /*
             * JSONObject obj = JSONObject.fromObject(Code); Iterator keys = obj.keys();
             * String key = keys.next().toString(); String string =obj.getString(key);
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getTradeNo() {
        long time = System.currentTimeMillis();
        return "TEST" + time;
    }

}
