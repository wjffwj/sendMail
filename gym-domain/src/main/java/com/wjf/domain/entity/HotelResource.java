package com.wjf.domain.entity;

import java.util.Date;

/**
 * 酒店的静态信息
 */
public class HotelResource {

    private Long id;//主键id
    private String cityCode;//城市编号
    private String hotelCode;//酒店code
    private String hotelNameCn;//酒店中文名称
    private String latitude;//酒店纬度
    private String longitude;//酒店经度
    private String tel;//酒店电话
    private String fax;//酒店传真
    private String address;//酒店地址
    private String hotelNameEn;//酒店英文名称
    private Date created;//记录创建时间
    private Date lastModifyTime;//记录更新时间
    private Integer status;//推送状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelNameCn() {
        return hotelNameCn;
    }

    public void setHotelNameCn(String hotelNameCn) {
        this.hotelNameCn = hotelNameCn;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelNameEn() {
        return hotelNameEn;
    }

    public void setHotelNameEn(String hotelNameEn) {
        this.hotelNameEn = hotelNameEn;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
