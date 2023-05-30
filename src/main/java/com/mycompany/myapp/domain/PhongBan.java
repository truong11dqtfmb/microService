package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PhongBan.
 */
@Entity
@Table(name = "phong_ban")
public class PhongBan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "ten_phong_ban", length = 100, nullable = false)
    private String tenPhongBan;

    @Column(name = "logo_phong")
    private String logoPhong;

    @OneToMany(mappedBy = "phongBan")
    private Set<NhanSu> nhanSus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PhongBan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenPhongBan() {
        return this.tenPhongBan;
    }

    public PhongBan tenPhongBan(String tenPhongBan) {
        this.setTenPhongBan(tenPhongBan);
        return this;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getLogoPhong() {
        return this.logoPhong;
    }

    public PhongBan logoPhong(String logoPhong) {
        this.setLogoPhong(logoPhong);
        return this;
    }

    public void setLogoPhong(String logoPhong) {
        this.logoPhong = logoPhong;
    }

    public Set<NhanSu> getNhanSus() {
        return this.nhanSus;
    }

    public void setNhanSus(Set<NhanSu> nhanSus) {
        if (this.nhanSus != null) {
            this.nhanSus.forEach(i -> i.setPhongBan(null));
        }
        if (nhanSus != null) {
            nhanSus.forEach(i -> i.setPhongBan(this));
        }
        this.nhanSus = nhanSus;
    }

    public PhongBan nhanSus(Set<NhanSu> nhanSus) {
        this.setNhanSus(nhanSus);
        return this;
    }

    public PhongBan addNhanSu(NhanSu nhanSu) {
        this.nhanSus.add(nhanSu);
        nhanSu.setPhongBan(this);
        return this;
    }

    public PhongBan removeNhanSu(NhanSu nhanSu) {
        this.nhanSus.remove(nhanSu);
        nhanSu.setPhongBan(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhongBan)) {
            return false;
        }
        return id != null && id.equals(((PhongBan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhongBan{" +
            "id=" + getId() +
            ", tenPhongBan='" + getTenPhongBan() + "'" +
            ", logoPhong='" + getLogoPhong() + "'" +
            "}";
    }
}
