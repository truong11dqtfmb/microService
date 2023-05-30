package com.mycompany.myapp.service.dto.request;

import java.io.Serializable;

public class PhongBanGroup implements Serializable {

    String tenPhongBan;
    Long soLuongNhanSu;

    public PhongBanGroup(String tenPhongBan, Long soLuongNhanSu) {
        this.tenPhongBan = tenPhongBan;
        this.soLuongNhanSu = soLuongNhanSu;
    }

    public PhongBanGroup() {}

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public Long getSoLuongNhanSu() {
        return soLuongNhanSu;
    }

    public void setSoLuongNhanSu(Long soLuongNhanSu) {
        this.soLuongNhanSu = soLuongNhanSu;
    }

    @Override
    public String toString() {
        return "PhongBanGroup{" + "tenPhongBan='" + tenPhongBan + '\'' + ", soLuongNhanSu=" + soLuongNhanSu + '}';
    }
}
