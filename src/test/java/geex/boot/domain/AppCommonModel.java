package geex.boot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongzhiwen on 16-4-20.
 */
public class AppCommonModel implements Serializable {
    private String appId;
    private String name;
    private String idNo;
    private String merchant;
    private String deviceType;
    private String mobile;
    private String loanMoney;
    private String tenor;
    private String status;
    private Double salary;
    private String reservedMobile;
    private String hfAccountNo;
    private String nyAccountSign;
    private String nyAccountNo;
    private String bindCardNo;
    private Date drawDownDate;
    private String nyBindCard;
    private String hasSetPassword;
    private String cityCode;
    private String city;
    private String cpnyCity;
    private String cpnyCityCode;
    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getReservedMobile() {
        return reservedMobile;
    }

    public void setReservedMobile(String reservedMobile) {
        this.reservedMobile = reservedMobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCpnyCity() {
        return cpnyCity;
    }

    public void setCpnyCity(String cpnyCity) {
        this.cpnyCity = cpnyCity;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCpnyCityCode() {
        return cpnyCityCode;
    }

    public void setCpnyCityCode(String cpnyCityCode) {
        this.cpnyCityCode = cpnyCityCode;
    }

    public String getHfAccountNo() {
        return hfAccountNo;
    }

    public void setHfAccountNo(String hfAccountNo) {
        this.hfAccountNo = hfAccountNo;
    }

    public String getNyAccountSign() {
        return nyAccountSign;
    }

    public void setNyAccountSign(String nyAccountSign) {
        this.nyAccountSign = nyAccountSign;
    }

    public String getNyAccountNo() {
        return nyAccountNo;
    }

    public void setNyAccountNo(String nyAccountNo) {
        this.nyAccountNo = nyAccountNo;
    }

    public String getBindCardNo() {
        return bindCardNo;
    }

    public void setBindCardNo(String bindCardNo) {
        this.bindCardNo = bindCardNo;
    }

    public Date getDrawDownDate() {
        return drawDownDate;
    }

    public void setDrawDownDate(Date drawDownDate) {
        this.drawDownDate = drawDownDate;
    }

    public String getNyBindCard() {
        return nyBindCard;
    }

    public void setNyBindCard(String nyBindCard) {
        this.nyBindCard = nyBindCard;
    }

    public String getHasSetPassword() {
        return hasSetPassword;
    }

    public void setHasSetPassword(String hasSetPassword) {
        this.hasSetPassword = hasSetPassword;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }
}
