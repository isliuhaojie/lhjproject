package com.lhj.project.model;

import com.lhj.project.utils.UtilDate;

import java.math.BigDecimal;

public class NotifyLog {
    private Integer id;

    private String outTradeNo;

    private String subject;

    private BigDecimal totalFee;

    private String notifyId;

    private String paymentType;

    private String tradeStatus;

    private String tradeNo;

    private String extraCommonParam;

    private String gatewayChannel;

    private String notifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
    }

    public String getGatewayChannel() {
        return gatewayChannel;
    }

    public void setGatewayChannel(String gatewayChannel) {
        this.gatewayChannel = gatewayChannel == null ? null : gatewayChannel.trim();
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate == null ? null : notifyDate.trim();
    }

    public NotifyLog(Integer id, String outTradeNo, String subject, BigDecimal totalFee, String notifyId, String paymentType, String tradeStatus, String tradeNo, String extraCommonParam, String gatewayChannel, String notifyDate) {
        this.id = id;
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.totalFee = totalFee;
        this.notifyId = notifyId;
        this.paymentType = paymentType;
        this.tradeStatus = tradeStatus;
        this.tradeNo = tradeNo;
        this.extraCommonParam = extraCommonParam;
        this.gatewayChannel = gatewayChannel;
        this.notifyDate = notifyDate;
    }

    public NotifyLog( String outTradeNo, String subject, BigDecimal totalFee, String notifyId, String paymentType, String tradeStatus, String tradeNo, String extraCommonParam, String gatewayChannel) {
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.totalFee = totalFee;
        this.notifyId = notifyId;
        this.paymentType = paymentType;
        this.tradeStatus = tradeStatus;
        this.tradeNo = tradeNo;
        this.extraCommonParam = extraCommonParam;
        this.gatewayChannel = gatewayChannel;
        this.notifyDate = UtilDate.getDateFormatter();
    }

    public NotifyLog() {
    }

    @Override
    public String toString() {
        return "NotifyLog{" +
                "id=" + id +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", subject='" + subject + '\'' +
                ", totalFee=" + totalFee +
                ", notifyId=" + notifyId +
                ", paymentType='" + paymentType + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", extraCommonParam='" + extraCommonParam + '\'' +
                ", gatewayChannel='" + gatewayChannel + '\'' +
                ", notifyDate='" + notifyDate + '\'' +
                '}';
    }
}