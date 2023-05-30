package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.NhanSu;
import com.mycompany.myapp.service.dto.request.ChucDanhDTO;
import com.mycompany.myapp.service.dto.request.NhanSuDTO;
import java.util.List;

public interface NhanSuService {
    NhanSuDTO save(NhanSuDTO nhanSuDTO, Long chudanhId, Long phongBanId);

    NhanSuDTO update(NhanSuDTO nhanSuDTO, Long chudanhId, Long phongBanId, Long id);

    List<NhanSuDTO> findAll();

    NhanSu findById(Long id);

    NhanSuDTO findDetail(Long id);

    void delete(Long id);

    List<NhanSuDTO> findAllByChucDanh(Long chucDanhId);

    List<NhanSuDTO> findAllByPhongBan(Long phongBanId);
}
