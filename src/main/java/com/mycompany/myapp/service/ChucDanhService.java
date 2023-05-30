package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.service.dto.request.ChucDanhDTO;
import java.util.List;

public interface ChucDanhService {
    ChucDanhDTO save(ChucDanhDTO chucDanhDTO);

    ChucDanhDTO update(ChucDanhDTO chucDanhDTO, Long id);

    List<ChucDanhDTO> findAll();

    ChucDanh findById(Long id);

    ChucDanhDTO findDetail(Long id);

    Boolean delete(Long id);
}
