package com.lhj.project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhj.project.model.NotifyLog;
import com.lhj.project.service.INotifyLogService;
import com.lhj.project.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * describe 用于对接lepay支付接口
 * author liuhj18
 * Date 2018/6/8
 * version 1.00
 */
@Controller
@RequestMapping("/lepay")
public class LePayController {

    @Resource
    private INotifyLogService notifyLogService;

    private static String notify_url = LePayProperry.getInstance().getProperty("notify_url");
    private static String return_url = LePayProperry.getInstance().getProperty("return_url");
    private static String lepay_url = LePayProperry.getInstance().getProperty("lepay_url");
    private static String partner = LePayProperry.getInstance().getProperty("partner");
    private static String key = LePayProperry.getInstance().getProperty("key");
    private static String sign_type = LePayProperry.getInstance().getProperty("sign_type");
    private static String service = LePayProperry.getInstance().getProperty("service");
    private static String input_charset = LePayProperry.getInstance().getProperty("input_charset");


    @RequestMapping("/showUser.do")
    @ResponseBody
    public String lepayqr(HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 订单名称，必填
        String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"), "UTF-8");

        // 付款金额，必填
        String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");

        // 商品描述，可空
        String body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");

        // lenovoId，可空
        String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8");
        // 支付方式
        String qr_pay_mode = new String(request.getParameter("qr_pay_mode").getBytes("ISO-8859-1"), "UTF-8");
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("partner", partner);
        /* sParaTemp.put("_input_charset", _input_charset); */
        sParaTemp.put("service", service);
        sParaTemp.put("qr_pay_mode", qr_pay_mode);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("buyer_id", buyer_id);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        /* sParaTemp.put("accrued_cost", "0.01"); */
        sParaTemp.put("body", body);

        try {
            String Code = LePaySubmit.buildRequest(sParaTemp);
            System.out.println(Code);
            JSONObject jsonObject = JSON.parseObject(Code);
            String qrCodeUrlAli = jsonObject.getString("qrCodeUrlAli");
            response.sendRedirect(qrCodeUrlAli);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);

        }


        return null;

    }

    @RequestMapping("/lepayReturn.do")
    public String lepayReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("同步通知  ");
        // 获取POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        System.out.println("同步通知：" + params.toString());
        // 网站唯一订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 商品名称
        String subject = new String(URLEncoder.encode(request.getParameter("subject"), "utf-8"));

        // 支付宝、微信交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        // 交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        System.out.println("subject" + request.getParameter("subject"));
        System.out.println("body" + request.getParameter("body"));

        System.out.println(out_trade_no + "\t" + subject + "\t" + trade_no + "\t" + trade_status);
        // 计算得出通知验证结果
        boolean verify_result = LePayNotify.getSignVeryfy(params);
        if (LePayNotify.getSignVeryfy(params)) {// 验证成功
            System.out.println(" 同步通知 验签成功");
            //////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                // 如果有做过处理，不执行商户的业务程序

            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                // 如果有做过处理，不执行商户的业务程序

                //System.out.println("同步通知，支付成功！！！");
            }

            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        }

        return null;
    }


    @RequestMapping("/lepayNotifytest.do")
    @ResponseBody
    public void lepayNotifytest(HttpServletRequest request, HttpServletResponse response) {

        //获取POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        System.out.println("异步通知：" + params.toString());

        NotifyLog notifyLog = (NotifyLog) ToBeanUtil.map2Object(params, NotifyLog.class);


        System.out.println(notifyLog.toString());

    }

    @RequestMapping("/lepayNotify.do")
    @ResponseBody
    public String lepayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获取POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        System.out.println("异步通知：" + params.toString());
        //网站唯一订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //商品名称
        String subject = URLEncoder.encode(request.getParameter("subject"), "utf-8");

        System.out.println("subject" + request.getParameter("subject"));
        System.out.println("body" + request.getParameter("body"));
        //支付宝、微信交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        System.out.println(out_trade_no + "\t" + subject + "\t" + trade_no + "\t" + trade_status);
        //计算得出通知验证结果
        boolean verify_result = LePayNotify.getSignVeryfy(params);
        if (LePayNotify.getSignVeryfy(params)) {//验证成功
            System.out.println("异步通知 验签成功");
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

            } else if (trade_status.equals("TRADE_SUCCESS")) {
                NotifyLog notifyLog = new NotifyLog(params.get("out_trade_no"), params.get("subject"), new BigDecimal(params.get("total_fee")), params.get("notify_id"), params.get("payment_type"), params.get("trade_status"),
                        params.get("trade_no"), params.get("extra_common_param"), params.get("gateway_channel"));

                System.out.println("------->>>>>>>>"+notifyLog.toString());


                int i = notifyLogService.insertSelective(notifyLog);
                System.out.println(i);
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——


            return "success";
            //////////////////////////////////////////////////////////////////////////////////////////
        } else {//验证失败
            System.out.println("验证失败");
            return "fail";
        }

    }

}
