package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.dto.request.ChucDanhDTO;
import com.mycompany.myapp.service.dto.response.ApiResponse;
import com.mycompany.myapp.service.impl.ChucDanhServiceImpl;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChucDanhResource {

    @Autowired
    private ChucDanhServiceImpl chucDanhService;

    @PostMapping("/chuc-danhs")
    public ResponseEntity<?> createChucDanh(@Valid @RequestBody ChucDanhDTO chucDanhDTO) {
        try {
            ChucDanhDTO dto = this.chucDanhService.save(chucDanhDTO);
            return new ResponseEntity<>(new ApiResponse(true, "Create Chuc Danh successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/chuc-danhs/{id}")
    public ResponseEntity<?> updateChucDanh(
        @PathVariable(value = "id", required = false) Long id,
        @Valid @RequestBody ChucDanhDTO chucDanhDTO
    ) {
        try {
            ChucDanhDTO dto = chucDanhService.update(chucDanhDTO, id);
            return new ResponseEntity<>(new ApiResponse(true, "Update Chuc Danh successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chuc-danhs")
    public ResponseEntity<?> getAllChucDanhs() {
        try {
            List<ChucDanhDTO> list = this.chucDanhService.findAll();

            return new ResponseEntity<>(new ApiResponse(true, "Get All Chuc Danh successfully!", list), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/chuc-danhs/{id}")
    public ResponseEntity<?> getChucDanh(@PathVariable(value = "id", required = false) Long id) {
        try {
            ChucDanhDTO dto = this.chucDanhService.findDetail(id);

            return new ResponseEntity<>(new ApiResponse(true, "Get Chuc Danh Detail id: " + id + " successfully!", dto), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/chuc-danhs/{id}")
    public ResponseEntity<?> deleteChucDanh(@PathVariable Long id) {
        try {
            Boolean flag = chucDanhService.delete(id);
            if (flag) {
                return new ResponseEntity<>(new ApiResponse(true, "Delete Chuc Danh id: " + id + " successfully!"), HttpStatus.OK);
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
