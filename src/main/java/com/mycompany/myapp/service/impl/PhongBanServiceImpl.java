package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.NhanSu;
import com.mycompany.myapp.domain.PhongBan;
import com.mycompany.myapp.exceptions.ResourceNotFoundException;
import com.mycompany.myapp.repository.NhanSuRepository;
import com.mycompany.myapp.repository.PhongBanRepository;
import com.mycompany.myapp.service.PhongBanService;
import com.mycompany.myapp.service.dto.request.PhongBanDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PhongBanServiceImpl implements PhongBanService {

    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Autowired
    private PhongBanRepository phongBanRepository;

    @Override
    public PhongBanDTO save(PhongBanDTO phongBanDTO) {
        PhongBan phongBan = this.convertToEntity(phongBanDTO);
        PhongBan saved = this.phongBanRepository.save(phongBan);
        PhongBanDTO dto = this.convertToDto(saved);

        return dto;
    }

    @Override
    public PhongBanDTO update(PhongBanDTO phongBanDTO, Long id) {
        PhongBan phongBan =
            this.phongBanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhongBan", "id", id + ""));

        phongBan.setTenPhongBan(phongBanDTO.getTenPhongBan());
        phongBan.setLogoPhong(phongBanDTO.getLogoPhong());

        PhongBan updated = this.phongBanRepository.save(phongBan);

        PhongBanDTO dto = this.convertToDto(updated);

        return dto;
    }

    @Override
    public List<PhongBanDTO> findAll() {
        List<PhongBan> list = this.phongBanRepository.findAll();

        List<PhongBanDTO> dtos = list.stream().map(phongBan -> this.convertToDto(phongBan)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public PhongBan findById(Long id) {
        return this.phongBanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhongBan", "id", id + ""));
    }

    @Override
    public PhongBanDTO findDetail(Long id) {
        PhongBan phongBan =
            this.phongBanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhongBan", "id", id + ""));

        PhongBanDTO dto = this.convertToDto(phongBan);

        return dto;
    }

    //    @Override
    //    public void delete(Long id) {
    //
    //        PhongBan phongBan = this.phongBanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhongBan", "id", id + ""));
    //
    //        this.phongBanRepository.delete(phongBan);
    //
    //    }

    public Boolean delete(Long id) {
        PhongBan phongBan =
            this.phongBanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PhongBan", "id", id + ""));

        List<NhanSu> list = this.nhanSuRepository.findByPhongBan(phongBan);

        if (list.size() == 0) {
            phongBanRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public PhongBanDTO convertToDto(PhongBan phongBan) {
        PhongBanDTO dto = new PhongBanDTO();
        dto.setId(phongBan.getId());
        dto.setTenPhongBan(phongBan.getTenPhongBan());
        dto.setLogoPhong(phongBan.getLogoPhong());

        return dto;
    }

    public PhongBan convertToEntity(PhongBanDTO phongBanDTO) {
        PhongBan phongBan = new PhongBan();
        phongBan.setId(phongBanDTO.getId());
        phongBan.setTenPhongBan(phongBanDTO.getTenPhongBan());
        phongBan.setLogoPhong(phongBanDTO.getLogoPhong());

        return phongBan;
    }
    //    public PhongBan convertFromDtoToEntity(PhongBanDTO phongBanDTO, PhongBan phongBan) {
    //        phongBan.setId(phongBanDTO.getId());
    //        phongBan.setTenPhongBan(phongBanDTO.getTenPhongBan());
    //        phongBan.setLogoPhong(phongBanDTO.getLogoPhong());
    //        return phongBan;
    //    }
    //
    //    public PhongBanDTO convertFromEntityToDto(PhongBan phongBan, PhongBanDTO dto) {
    //        dto.setId(phongBan.getId());
    //        dto.setTenPhongBan(phongBan.getTenPhongBan());
    //        dto.setLogoPhong(phongBan.getLogoPhong());
    //
    //        return dto;
    //    }

}
