package com.lhj.project.utils;


import java.util.Map;

public class LePayNotify {
	private static String key = LePayProperry.getInstance().getProperty("key");
	private static String sign_type = LePayProperry.getInstance().getProperty("sign_type");
	private static String input_charset = LePayProperry.getInstance().getProperty("input_charset");

	/**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @return 生成的签名结果
     */
	public static boolean getSignVeryfy(Map<String, String> Params) {
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = LePayCore.paraFilter(Params);
        //获取待签名字符串
        String preSignStr = LePayCore.createLinkString(sParaNew);
        //获得签名验证结果
        String sign = "";
	    if(Params.get("sign") != null) {sign = Params.get("sign");}
        boolean isSign = false;
        if(sign_type.equals("MD5") ) {
        	isSign = MD5.verify(preSignStr, sign, key, input_charset);
        }
        return isSign;
    }
	
	
	public static void main(String[] args) {
		String preSignStr = "body=联想智能音箱-PCSD&extra_common_param=null&is_success=T&notify_id=null&out_trade_no=4095046471385116&payment_type=null&subject=联想智能音箱&total_fee=0.01&trade_no=4000882001201704106542053739&trade_status=SUCCESS";
		String sign = MD5.sign(preSignStr, "OobEJSIV86jo8NYpJ38N", "utf-8");
		System.out.println(sign);
	}
	
	
}
