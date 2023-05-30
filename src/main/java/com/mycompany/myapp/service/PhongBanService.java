package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.PhongBan;
import com.mycompany.myapp.service.dto.request.PhongBanDTO;
import java.util.List;

public interface PhongBanService {
    PhongBanDTO save(PhongBanDTO phongBanDTO);

    PhongBanDTO update(PhongBanDTO phongBanDTO, Long id);

    List<PhongBanDTO> findAll();

    PhongBan findById(Long id);

    PhongBanDTO findDetail(Long id);

    Boolean delete(Long id);
}
