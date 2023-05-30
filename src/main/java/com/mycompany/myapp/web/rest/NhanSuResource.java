package com.mycompany.myapp.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.domain.ChucDanh;
import com.mycompany.myapp.domain.PhongBan;
import com.mycompany.myapp.repository.NhanSuRepository;
import com.mycompany.myapp.service.ChucDanhService;
import com.mycompany.myapp.service.PhongBanService;
import com.mycompany.myapp.service.dto.request.ChucDanhGroup;
import com.mycompany.myapp.service.dto.request.NhanSuDTO;
import com.mycompany.myapp.service.dto.request.PhongBanGroup;
import com.mycompany.myapp.service.dto.response.ApiResponse;
import com.mycompany.myapp.service.impl.NhanSuServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NhanSuResource {

    @Autowired
    private NhanSuServiceImpl nhanSuService;

    @Autowired
    private NhanSuRepository nhanSuRepository;

    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private ChucDanhService chucDanhService;

    @PostMapping("/nhan-sus/{chucDanhId}/{phongBanId}")
    public ResponseEntity<?> createNhanSu(
        @Valid @RequestBody NhanSuDTO nhanSuDTO,
        @PathVariable("chucDanhId") Long chucDanhId,
        @PathVariable("phongBanId") Long phongBanId
    ) {
        try {
            NhanSuDTO dto = this.nhanSuService.save(nhanSuDTO, chucDanhId, phongBanId);
            return new ResponseEntity<>(new ApiResponse(true, "Create Nhan Su successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/nhan-sus/{chucDanhId}/{phongBanId}/{id}")
    public ResponseEntity<?> updateNhanSu(
        @PathVariable(value = "id", required = false) Long id,
        @PathVariable("chucDanhId") Long chucDanhId,
        @PathVariable("phongBanId") Long phongBanId,
        @Valid @RequestBody NhanSuDTO nhanSuDTO
    ) {
        try {
            NhanSuDTO dto = nhanSuService.update(nhanSuDTO, chucDanhId, phongBanId, id);
            return new ResponseEntity<>(new ApiResponse(true, "Update Nhan Su successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nhan-sus")
    public ResponseEntity<?> getAllNhanSus() {
        try {
            List<NhanSuDTO> list = this.nhanSuService.findAll();

            return new ResponseEntity<>(new ApiResponse(true, "Get All Nhan Su successfully!", list), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nhan-sus/{id}")
    public ResponseEntity<?> getNhanSu(@PathVariable(value = "id", required = false) Long id) {
        try {
            NhanSuDTO dto = this.nhanSuService.findDetail(id);

            return new ResponseEntity<>(new ApiResponse(true, "Get Nhan Su Detail id: " + id + " successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/nhan-sus/{id}")
    public ResponseEntity<?> deleteNhanSu(@PathVariable Long id) {
        try {
            this.nhanSuService.delete(id);
            return new ResponseEntity<>(new ApiResponse(true, "Delete Nhan Su id: " + id + " successfully!"), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chuc-danhs/{chucDanhId}/nhan-sus")
    public ResponseEntity<?> getAllNhanSuByChucDanh(@PathVariable("chucDanhId") Long chucDanhId) {
        try {
            List<NhanSuDTO> dtos = this.nhanSuService.findAllByChucDanh(chucDanhId);
            return new ResponseEntity<>(new ApiResponse(true, "Get All NhanSu By ChucDanh id " + chucDanhId, dtos), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phong-bans/{phongBanId}/nhan-sus")
    public ResponseEntity<?> getAllNhanSuByPhongBan(@PathVariable("phongBanId") Long phongBanId) {
        try {
            List<NhanSuDTO> dtos = this.nhanSuService.findAllByPhongBan(phongBanId);
            return new ResponseEntity<>(new ApiResponse(true, "Get All NhanSu By PhongBan id " + phongBanId, dtos), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/thongke/phong-bans")
    public ResponseEntity<?> thongKePhongBans() throws JsonProcessingException {
        List<Object[]> phongBanGroups = this.nhanSuRepository.groupByPhongBan();

        List<PhongBanGroup> banGroups = new ArrayList<>();

        for (int i = 0; i < phongBanGroups.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            PhongBan phongBan = this.phongBanService.findById(Long.valueOf(phongBanGroups.get(i)[0].toString()));
            Long soLuong = Long.valueOf(phongBanGroups.get(i)[1].toString());
            String tenPhongBan = phongBan.getTenPhongBan();

            PhongBanGroup phongBanGroup = new PhongBanGroup();
            phongBanGroup.setTenPhongBan(tenPhongBan);
            phongBanGroup.setSoLuongNhanSu(soLuong);

            banGroups.add(phongBanGroup);
        }

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(banGroups);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/thongke/chuc-danhs")
    public ResponseEntity<?> thongKeChucDanhs() throws JsonProcessingException {
        List<Object[]> chucdanhGroups = this.nhanSuRepository.groupByChucDanh();

        List<ChucDanhGroup> banGroups = new ArrayList<>();

        for (int i = 0; i < chucdanhGroups.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            ChucDanh chucDanh = this.chucDanhService.findById(Long.valueOf(chucdanhGroups.get(i)[0].toString()));
            Long soLuong = Long.valueOf(chucdanhGroups.get(i)[1].toString());
            String tenChucDanh = chucDanh.getTenChucDanh();

            ChucDanhGroup phongBanGroup = new ChucDanhGroup();
            phongBanGroup.setTenChucDanh(tenChucDanh);
            phongBanGroup.setSoLuongNhanSu(soLuong);

            banGroups.add(phongBanGroup);
        }

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(banGroups);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
