package com.mycompany.myapp.service.dto.request;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhongBanDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String tenPhongBan;

    private String logoPhong;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getLogoPhong() {
        return logoPhong;
    }

    public void setLogoPhong(String logoPhong) {
        this.logoPhong = logoPhong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhongBanDTO)) {
            return false;
        }

        PhongBanDTO phongBanDTO = (PhongBanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, phongBanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhongBanDTO{" +
            "id=" + getId() +
            ", tenPhongBan='" + getTenPhongBan() + "'" +
            ", logoPhong='" + getLogoPhong() + "'" +
            "}";
    }
}
