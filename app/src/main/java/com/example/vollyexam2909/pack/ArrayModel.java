package com.example.vollyexam2909.pack;

public class ArrayModel {

    private String couponCode;
    private String validTo;
    private String tripMode;
    private String discount;
    private String minAmount;
    private String maxdiscount;


    public ArrayModel(String couponCode, String validTo, String tripMode, String discount, String minAmount, String maxdiscount) {
        this.couponCode = couponCode;
        this.validTo = validTo;
        this.tripMode = tripMode;
        this.discount = discount;
        this.minAmount = minAmount;
        this.maxdiscount = maxdiscount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getTripMode() {
        return tripMode;
    }

    public void setTripMode(String tripMode) {
        this.tripMode = tripMode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxdiscount() {
        return maxdiscount;
    }

    public void setMaxdiscount(String maxdiscount) {
        this.maxdiscount = maxdiscount;
    }
}
