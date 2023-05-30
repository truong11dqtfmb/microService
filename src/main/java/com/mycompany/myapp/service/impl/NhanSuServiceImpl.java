package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.NhanSu;
import com.mycompany.myapp.domain.PhongBan;
import com.mycompany.myapp.exceptions.ResourceNotFoundException;
import com.mycompany.myapp.repository.NhanSuRepository;
import com.mycompany.myapp.service.ChucDanhService;
import com.mycompany.myapp.service.NhanSuService;
import com.mycompany.myapp.service.PhongBanService;
import com.mycompany.myapp.service.dto.request.ChucDanhDTO;
import com.mycompany.myapp.service.dto.request.NhanSuDTO;
import com.mycompany.myapp.service.dto.request.PhongBanDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NhanSuServiceImpl implements NhanSuService {

    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Autowired
    private ChucDanhService chucDanhService;

    @Autowired
    private PhongBanService phongBanService;

    @Override
    public NhanSuDTO save(NhanSuDTO nhanSuDTO, Long chudanhId, Long phongBanId) {
        ChucDanhDTO chucDanhDTO = this.chucDanhService.findDetail(chudanhId);

        PhongBanDTO phongBanDTO = this.phongBanService.findDetail(phongBanId);

        nhanSuDTO.setChucDanhDTO(chucDanhDTO);
        nhanSuDTO.setPhongBanDTO(phongBanDTO);

        NhanSu su = this.nhansuConvertToEntity(nhanSuDTO);

        NhanSu saved = this.nhanSuRepository.save(su);

        NhanSuDTO dto = this.nhansuConvertToDto(saved);

        return dto;
    }

    @Override
    public NhanSuDTO update(NhanSuDTO nhanSuDTO, Long chudanhId, Long phongBanId, Long id) {
        NhanSu nhanSu = this.nhanSuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NhanSu", "id", id + ""));

        ChucDanh chucDanh = this.chucDanhService.findById(chudanhId);

        PhongBan phongBan = this.phongBanService.findById(phongBanId);

        nhanSu.setAnhNhanSu(nhanSuDTO.getAnhNhanSu());
        nhanSu.setEmailNhanSu(nhanSuDTO.getEmailNhanSu());
        nhanSu.setPhoneNhanSu(nhanSuDTO.getPhoneNhanSu());
        nhanSu.setYear(nhanSuDTO.getYear());
        nhanSu.setTenNhanSu(nhanSuDTO.getTenNhanSu());
        nhanSu.setPhongBan(phongBan);
        nhanSu.setChucDanh(chucDanh);

        NhanSu updated = this.nhanSuRepository.save(nhanSu);

        NhanSuDTO dto = this.nhansuConvertToDto(updated);

        return dto;
    }

    @Override
    public List<NhanSuDTO> findAll() {
        List<NhanSu> list = this.nhanSuRepository.findAll();

        List<NhanSuDTO> dtos = list.stream().map((nhanSu -> this.nhansuConvertToDto(nhanSu))).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public NhanSu findById(Long id) {
        return this.nhanSuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NhanSu", "id", id + ""));
    }

    @Override
    public NhanSuDTO findDetail(Long id) {
        NhanSu nhanSu = this.nhanSuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NhanSu", "id", id + ""));

        NhanSuDTO dto = this.nhansuConvertToDto(nhanSu);

        return dto;
    }

    @Override
    public void delete(Long id) {
        NhanSu nhanSu = this.nhanSuRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NhanSu", "id", id + ""));

        this.nhanSuRepository.delete(nhanSu);
    }

    @Override
    public List<NhanSuDTO> findAllByChucDanh(Long chucDanhId) {
        ChucDanh chucDanh = this.chucDanhService.findById(chucDanhId);

        List<NhanSu> list = this.nhanSuRepository.findAllByChucDanh(chucDanh);

        List<NhanSuDTO> dtos = list.stream().map((nhanSu -> this.nhansuConvertToDto(nhanSu))).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<NhanSuDTO> findAllByPhongBan(Long phongBanId) {
        PhongBan phongBan = this.phongBanService.findById(phongBanId);

        List<NhanSu> list = this.nhanSuRepository.findAllByPhongBan(phongBan);

        List<NhanSuDTO> dtos = list.stream().map((nhanSu -> this.nhansuConvertToDto(nhanSu))).collect(Collectors.toList());

        return dtos;
    }

    public ChucDanhDTO chucDanhConvertToDto(ChucDanh chucDanh) {
        ChucDanhDTO dto = new ChucDanhDTO();
        dto.setId(chucDanh.getId());
        dto.setTenChucDanh(chucDanh.getTenChucDanh());
        dto.setNhiemVuChinh(chucDanh.getNhiemVuChinh());

        return dto;
    }

    public ChucDanh chucDanhConvertToEntity(ChucDanhDTO chucDanhDTO) {
        ChucDanh chucDanh = new ChucDanh();
        chucDanh.setId(chucDanhDTO.getId());
        chucDanh.setTenChucDanh(chucDanhDTO.getTenChucDanh());
        chucDanh.setNhiemVuChinh(chucDanhDTO.getNhiemVuChinh());

        return chucDanh;
    }

    public PhongBanDTO phongBanConvertToDto(PhongBan phongBan) {
        PhongBanDTO dto = new PhongBanDTO();
        dto.setId(phongBan.getId());
        dto.setTenPhongBan(phongBan.getTenPhongBan());
        dto.setLogoPhong(phongBan.getLogoPhong());

        return dto;
    }

    public PhongBan phongBanConvertToEntity(PhongBanDTO phongBanDTO) {
        PhongBan phongBan = new PhongBan();
        phongBan.setId(phongBanDTO.getId());
        phongBan.setTenPhongBan(phongBanDTO.getTenPhongBan());
        phongBan.setLogoPhong(phongBanDTO.getLogoPhong());

        return phongBan;
    }

    public NhanSu nhansuConvertToEntity(NhanSuDTO nhanSuDTO) {
        NhanSu nhanSu = new NhanSu();

        nhanSu.setId(nhanSuDTO.getId());
        nhanSu.setAnhNhanSu(nhanSuDTO.getAnhNhanSu());
        nhanSu.setEmailNhanSu(nhanSuDTO.getEmailNhanSu());
        nhanSu.setPhoneNhanSu(nhanSuDTO.getPhoneNhanSu());
        nhanSu.setYear(nhanSuDTO.getYear());
        nhanSu.setTenNhanSu(nhanSuDTO.getTenNhanSu());

        PhongBanDTO phongBanDTO = nhanSuDTO.getPhongBanDTO();
        nhanSu.setPhongBan(this.phongBanConvertToEntity(phongBanDTO));

        ChucDanhDTO chucDanhDTO = nhanSuDTO.getChucDanhDTO();
        nhanSu.setChucDanh(this.chucDanhConvertToEntity(chucDanhDTO));

        return nhanSu;
    }

    public NhanSuDTO nhansuConvertToDto(NhanSu nhanSu) {
        NhanSuDTO nhanSuDTO = new NhanSuDTO();

        nhanSuDTO.setId(nhanSu.getId());
        nhanSuDTO.setAnhNhanSu(nhanSu.getAnhNhanSu());
        nhanSuDTO.setEmailNhanSu(nhanSu.getEmailNhanSu());
        nhanSuDTO.setPhoneNhanSu(nhanSu.getPhoneNhanSu());
        nhanSuDTO.setYear(nhanSu.getYear());
        nhanSuDTO.setTenNhanSu(nhanSu.getTenNhanSu());

        PhongBan phongBan = nhanSu.getPhongBan();
        nhanSuDTO.setPhongBanDTO(this.phongBanConvertToDto(phongBan));

        ChucDanh chucDanh = nhanSu.getChucDanh();
        nhanSuDTO.setChucDanhDTO(this.chucDanhConvertToDto(chucDanh));

        return nhanSuDTO;
    }
}
