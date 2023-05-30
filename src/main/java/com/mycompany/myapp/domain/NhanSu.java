package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A NhanSu.
 */
@Entity
@Table(name = "nhan_su")
public class NhanSu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "ten_nhan_su", length = 100, nullable = false)
    private String tenNhanSu;

    @NotNull
    @Size(min = 10)
    @Column(name = "email_nhan_su", nullable = false)
    private String emailNhanSu;

    @NotNull
    @Size(max = 20)
    @Column(name = "phone_nhan_su", length = 20, nullable = false)
    private String phoneNhanSu;

    @Column(name = "anh_nhan_su")
    private String anhNhanSu;

    @Column(name = "year")
    private LocalDate year;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "nhanSus" }, allowSetters = true)
    private ChucDanh chucDanh;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "nhanSus" }, allowSetters = true)
    private PhongBan phongBan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NhanSu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenNhanSu() {
        return this.tenNhanSu;
    }

    public NhanSu tenNhanSu(String tenNhanSu) {
        this.setTenNhanSu(tenNhanSu);
        return this;
    }

    public void setTenNhanSu(String tenNhanSu) {
        this.tenNhanSu = tenNhanSu;
    }

    public String getEmailNhanSu() {
        return this.emailNhanSu;
    }

    public NhanSu emailNhanSu(String emailNhanSu) {
        this.setEmailNhanSu(emailNhanSu);
        return this;
    }

    public void setEmailNhanSu(String emailNhanSu) {
        this.emailNhanSu = emailNhanSu;
    }

    public String getPhoneNhanSu() {
        return this.phoneNhanSu;
    }

    public NhanSu phoneNhanSu(String phoneNhanSu) {
        this.setPhoneNhanSu(phoneNhanSu);
        return this;
    }

    public void setPhoneNhanSu(String phoneNhanSu) {
        this.phoneNhanSu = phoneNhanSu;
    }

    public String getAnhNhanSu() {
        return this.anhNhanSu;
    }

    public NhanSu anhNhanSu(String anhNhanSu) {
        this.setAnhNhanSu(anhNhanSu);
        return this;
    }

    public void setAnhNhanSu(String anhNhanSu) {
        this.anhNhanSu = anhNhanSu;
    }

    public LocalDate getYear() {
        return this.year;
    }

    public NhanSu year(LocalDate year) {
        this.setYear(year);
        return this;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public ChucDanh getChucDanh() {
        return this.chucDanh;
    }

    public void setChucDanh(ChucDanh chucDanh) {
        this.chucDanh = chucDanh;
    }

    public NhanSu chucDanh(ChucDanh chucDanh) {
        this.setChucDanh(chucDanh);
        return this;
    }

    public PhongBan getPhongBan() {
        return this.phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public NhanSu phongBan(PhongBan phongBan) {
        this.setPhongBan(phongBan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhanSu)) {
            return false;
        }
        return id != null && id.equals(((NhanSu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhanSu{" +
            "id=" + getId() +
            ", tenNhanSu='" + getTenNhanSu() + "'" +
            ", emailNhanSu='" + getEmailNhanSu() + "'" +
            ", phoneNhanSu='" + getPhoneNhanSu() + "'" +
            ", anhNhanSu='" + getAnhNhanSu() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }
}
