package com.mycompany.myapp.service.dto.request;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

public class ChucDanhDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 125)
    private String tenChucDanh;

    private String nhiemVuChinh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenChucDanh() {
        return tenChucDanh;
    }

    public void setTenChucDanh(String tenChucDanh) {
        this.tenChucDanh = tenChucDanh;
    }

    public String getNhiemVuChinh() {
        return nhiemVuChinh;
    }

    public void setNhiemVuChinh(String nhiemVuChinh) {
        this.nhiemVuChinh = nhiemVuChinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChucDanhDTO)) {
            return false;
        }

        ChucDanhDTO chucDanhDTO = (ChucDanhDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chucDanhDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChucDanhDTO{" +
            "id=" + getId() +
            ", tenChucDanh='" + getTenChucDanh() + "'" +
            ", nhiemVuChinh='" + getNhiemVuChinh() + "'" +
            "}";
    }
}
