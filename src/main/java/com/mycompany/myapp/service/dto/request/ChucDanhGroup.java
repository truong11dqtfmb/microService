package com.mycompany.myapp.service.dto.request;

public class ChucDanhGroup {

    String tenChucDanh;
    Long soLuongNhanSu;

    public ChucDanhGroup(String tenChucDanh, Long soLuongNhanSu) {
        this.tenChucDanh = tenChucDanh;
        this.soLuongNhanSu = soLuongNhanSu;
    }

    public String getTenChucDanh() {
        return tenChucDanh;
    }

    public void setTenChucDanh(String tenChucDanh) {
        this.tenChucDanh = tenChucDanh;
    }

    public Long getSoLuongNhanSu() {
        return soLuongNhanSu;
    }

    public void setSoLuongNhanSu(Long soLuongNhanSu) {
        this.soLuongNhanSu = soLuongNhanSu;
    }

    @Override
    public String toString() {
        return "ChucDanhGroup{" + "tenChucDanh='" + tenChucDanh + '\'' + ", soLuongNhanSu=" + soLuongNhanSu + '}';
    }

    public ChucDanhGroup() {}
}
