package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "chuc_danh")
public class ChucDanh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 125)
    @Column(name = "ten_chuc_danh", length = 125, nullable = false)
    private String tenChucDanh;

    @Column(name = "nhiem_vu_chinh")
    private String nhiemVuChinh;

    @OneToMany(mappedBy = "chucDanh")
    private Set<NhanSu> nhanSus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChucDanh id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenChucDanh() {
        return this.tenChucDanh;
    }

    public ChucDanh tenChucDanh(String tenChucDanh) {
        this.setTenChucDanh(tenChucDanh);
        return this;
    }

    public void setTenChucDanh(String tenChucDanh) {
        this.tenChucDanh = tenChucDanh;
    }

    public String getNhiemVuChinh() {
        return this.nhiemVuChinh;
    }

    public ChucDanh nhiemVuChinh(String nhiemVuChinh) {
        this.setNhiemVuChinh(nhiemVuChinh);
        return this;
    }

    public void setNhiemVuChinh(String nhiemVuChinh) {
        this.nhiemVuChinh = nhiemVuChinh;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChucDanh)) {
            return false;
        }
        return id != null && id.equals(((ChucDanh) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChucDanh{" +
            "id=" + getId() +
            ", tenChucDanh='" + getTenChucDanh() + "'" +
            ", nhiemVuChinh='" + getNhiemVuChinh() + "'" +
            "}";
    }
}
