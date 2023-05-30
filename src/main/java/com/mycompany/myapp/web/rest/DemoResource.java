package com.mycompany.myapp.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoResource {

    @GetMapping("/demo")
    public ResponseEntity<?> demo() {
        return new ResponseEntity<>("demo dao quoc truong alo alo 500 anh em!", HttpStatus.OK);
    }
}
