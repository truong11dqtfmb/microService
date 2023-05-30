package com.mycompany.myapp.service.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;

public class NhanSuDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String tenNhanSu;

    @NotNull
    @Size(min = 10)
    private String emailNhanSu;

    @NotNull
    @Size(max = 20)
    private String phoneNhanSu;

    private String anhNhanSu;

    private LocalDate year;

    private ChucDanhDTO chucDanhDTO;

    private PhongBanDTO phongBanDTO;

    public NhanSuDTO(
        Long id,
        String tenNhanSu,
        String emailNhanSu,
        String phoneNhanSu,
        String anhNhanSu,
        LocalDate year,
        ChucDanhDTO chucDanhDTO,
        PhongBanDTO phongBanDTO
    ) {
        this.id = id;
        this.tenNhanSu = tenNhanSu;
        this.emailNhanSu = emailNhanSu;
        this.phoneNhanSu = phoneNhanSu;
        this.anhNhanSu = anhNhanSu;
        this.year = year;
        this.chucDanhDTO = chucDanhDTO;
        this.phongBanDTO = phongBanDTO;
    }

    public NhanSuDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhanSu() {
        return tenNhanSu;
    }

    public void setTenNhanSu(String tenNhanSu) {
        this.tenNhanSu = tenNhanSu;
    }

    public String getEmailNhanSu() {
        return emailNhanSu;
    }

    public void setEmailNhanSu(String emailNhanSu) {
        this.emailNhanSu = emailNhanSu;
    }

    public String getPhoneNhanSu() {
        return phoneNhanSu;
    }

    public void setPhoneNhanSu(String phoneNhanSu) {
        this.phoneNhanSu = phoneNhanSu;
    }

    public String getAnhNhanSu() {
        return anhNhanSu;
    }

    public void setAnhNhanSu(String anhNhanSu) {
        this.anhNhanSu = anhNhanSu;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public ChucDanhDTO getChucDanhDTO() {
        return chucDanhDTO;
    }

    public void setChucDanhDTO(ChucDanhDTO chucDanhDTO) {
        this.chucDanhDTO = chucDanhDTO;
    }

    public PhongBanDTO getPhongBanDTO() {
        return phongBanDTO;
    }

    public void setPhongBanDTO(PhongBanDTO phongBanDTO) {
        this.phongBanDTO = phongBanDTO;
    }

    @Override
    public String toString() {
        return (
            "NhanSuDTO{" +
            "id=" +
            id +
            ", tenNhanSu='" +
            tenNhanSu +
            '\'' +
            ", emailNhanSu='" +
            emailNhanSu +
            '\'' +
            ", phoneNhanSu='" +
            phoneNhanSu +
            '\'' +
            ", anhNhanSu='" +
            anhNhanSu +
            '\'' +
            ", year=" +
            year +
            ", chucDanhDTO=" +
            chucDanhDTO +
            ", phongBanDTO=" +
            phongBanDTO +
            '}'
        );
    }
}
