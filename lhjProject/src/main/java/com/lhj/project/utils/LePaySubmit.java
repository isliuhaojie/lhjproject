package com.lhj.project.utils;


import java.util.Map;


public class LePaySubmit {

    private static String lepay_url = LePayProperry.getInstance().getProperty("lepay_url");



    /**
     * 生成GET请求
     */
    public static String buildRequest(Map<String, String> sParaTemp) throws Exception {
        //待请求参数数组
        Map<String, String> sPara = LePayCore.buildRequestPara(sParaTemp);
        System.out.println(sPara.toString());
        String prestr = LePayCore.createLinkString(sPara,"utf-8"); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        StringBuffer sbHtml = new StringBuffer();
        sbHtml.append(lepay_url + "?");
        sbHtml.append(prestr);
        String CodeUrlAli = HttpClientUtil.doGet(sbHtml.toString());
        System.out.println("get  " + sbHtml.toString());
        return CodeUrlAli;
    }
    public static void main(String[] args) {
        String CodeUrlAli = HttpClientUtil.doGet("http://mlegou.lenovo.com.cn/api/shop/lePayNotify/lePayAsyncNotify.do?is_success=T&out_trade_no=151003487041&total_fee=0.02&notify_id=null&payment_type=null&body=null&subject=%E5%BF%AB%E9%80%92%E8%A1%A5%E6%8B%8D%EF%BC%88%E8%AF%B7%E5%8B%BF%E6%8B%8D%EF%BC%89&trade_status=SUCCESS&trade_no=4200000020201711073047678582&extra_common_param=null&sign_type=MD5&gateway_channel=2&sign=604d75c2cdd48c2305f36c4d1d51636d");
        System.out.println(CodeUrlAli);
	}
}
