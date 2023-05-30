package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.dto.request.PhongBanDTO;
import com.mycompany.myapp.service.dto.response.ApiResponse;
import com.mycompany.myapp.service.impl.PhongBanServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PhongBanResource {

    @Autowired
    private PhongBanServiceImpl phongBanService;

    @PostMapping("/phong-bans")
    public ResponseEntity<?> createPhongBan(@Valid @RequestBody PhongBanDTO phongBanDTO) {
        try {
            PhongBanDTO dto = this.phongBanService.save(phongBanDTO);
            return new ResponseEntity<>(new ApiResponse(true, "Create Phong Ban successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/phong-bans/{id}")
    public ResponseEntity<?> updatePhongBan(
        @PathVariable(value = "id", required = false) Long id,
        @Valid @RequestBody PhongBanDTO phongBanDTO
    ) {
        try {
            PhongBanDTO dto = phongBanService.update(phongBanDTO, id);
            return new ResponseEntity<>(new ApiResponse(true, "Update Phong Ban successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phong-bans")
    public ResponseEntity<?> getAllPhongBans() {
        try {
            List<PhongBanDTO> list = this.phongBanService.findAll();

            return new ResponseEntity<>(new ApiResponse(true, "Get All Phong Ban successfully!", list), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/phong-bans/{id}")
    public ResponseEntity<?> getPhongBan(@PathVariable(value = "id", required = false) Long id) {
        try {
            PhongBanDTO dto = this.phongBanService.findDetail(id);

            return new ResponseEntity<>(new ApiResponse(true, "Get Phong Ban Detail id: " + id + " successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/phong-bans/{id}")
    public ResponseEntity<?> deletePhongBan(@PathVariable Long id) {
        try {
            Boolean flag = phongBanService.delete(id);
            if (flag) {
                return new ResponseEntity<>(new ApiResponse(true, "Delete Phong Ban id: " + id + " successfully!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(
                new ApiResponse(false, "Trong Nhân Sự có chứa khóa ngoại Chức Danh! Không thể xóa!!"),
                HttpStatus.BAD_REQUEST
            );
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
