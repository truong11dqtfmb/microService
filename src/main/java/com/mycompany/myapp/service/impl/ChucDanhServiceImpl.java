package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.NhanSu;
import com.mycompany.myapp.exceptions.ResourceNotFoundException;
import com.mycompany.myapp.repository.ChucDanhRepository;
import com.mycompany.myapp.repository.NhanSuRepository;
import com.mycompany.myapp.service.ChucDanhService;
import com.mycompany.myapp.service.dto.request.ChucDanhDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChucDanhServiceImpl implements ChucDanhService {

    @Autowired
    private ChucDanhRepository chucDanhRepository;

    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Override
    public ChucDanhDTO save(ChucDanhDTO chucDanhDTO) {
        ChucDanh chucDanh = this.convertToEntity(chucDanhDTO);
        ChucDanh saved = this.chucDanhRepository.save(chucDanh);
        ChucDanhDTO dto = this.convertToDto(saved);

        return dto;
    }

    @Override
    public ChucDanhDTO update(ChucDanhDTO chucDanhDTO, Long id) {
        ChucDanh chucDanh =
            this.chucDanhRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChucDanh", "id", id + ""));

        chucDanh.setTenChucDanh(chucDanhDTO.getTenChucDanh());
        chucDanh.setNhiemVuChinh(chucDanhDTO.getNhiemVuChinh());

        ChucDanh updated = this.chucDanhRepository.save(chucDanh);

        ChucDanhDTO dto = this.convertToDto(updated);

        return dto;
    }

    @Override
    public List<ChucDanhDTO> findAll() {
        List<ChucDanh> list = this.chucDanhRepository.findAll();

        List<ChucDanhDTO> dtos = list.stream().map(chucDanh -> this.convertToDto(chucDanh)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public ChucDanh findById(Long id) {
        return this.chucDanhRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChucDanh", "id", id + ""));
    }

    @Override
    public ChucDanhDTO findDetail(Long id) {
        ChucDanh chucDanh =
            this.chucDanhRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChucDanh", "id", id + ""));

        ChucDanhDTO dto = this.convertToDto(chucDanh);

        return dto;
    }

    //    @Override
    //    public void delete(Long id) {
    //
    //        ChucDanh chucDanh = this.findById(id);
    //
    //        this.chucDanhRepository.delete(chucDanh);
    //
    //    }

    public Boolean delete(Long id) {
        ChucDanh chucDanh =
            this.chucDanhRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ChucDanh", "id", id + ""));

        List<NhanSu> list = this.nhanSuRepository.findByChucDanh(chucDanh);

        if (list.size() == 0) {
            chucDanhRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public ChucDanhDTO convertToDto(ChucDanh chucDanh) {
        ChucDanhDTO dto = new ChucDanhDTO();
        dto.setId(chucDanh.getId());
        dto.setTenChucDanh(chucDanh.getTenChucDanh());
        dto.setNhiemVuChinh(chucDanh.getNhiemVuChinh());

        return dto;
    }

    public ChucDanh convertToEntity(ChucDanhDTO chucDanhDTO) {
        ChucDanh chucDanh = new ChucDanh();
        chucDanh.setId(chucDanhDTO.getId());
        chucDanh.setTenChucDanh(chucDanhDTO.getTenChucDanh());
        chucDanh.setNhiemVuChinh(chucDanhDTO.getNhiemVuChinh());

        return chucDanh;
    }
    //    public ChucDanh convertFromDtoToEntity(ChucDanhDTO chucDanhDTO, ChucDanh chucDanh) {
    //        chucDanh.setId(chucDanhDTO.getId());
    //        chucDanh.setTenChucDanh(chucDanhDTO.getTenChucDanh());
    //        chucDanh.setNhiemVuChinh(chucDanhDTO.getNhiemVuChinh());
    //        return chucDanh;
    //    }
    //
    //    public ChucDanhDTO convertFromEntityToDto(ChucDanh chucDanh, ChucDanhDTO dto) {
    //        dto.setId(chucDanh.getId());
    //        dto.setTenChucDanh(chucDanh.getTenChucDanh());
    //        dto.setNhiemVuChinh(chucDanh.getNhiemVuChinh());
    //
    //        return dto;
    //    }

}
