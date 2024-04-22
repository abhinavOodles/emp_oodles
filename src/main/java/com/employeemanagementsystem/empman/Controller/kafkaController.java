package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Service.kafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class kafkaController {

    @Autowired
    kafkaService kService ;


    @PostMapping("/update")
    public ResponseEntity<?> updateLocation () {
        for (int i = 0 ; i<1000 ; i++) {
            kService.updateLocation("(" + Math.round(Math.random() * 100) + "," + Math.round(Math.random()) + ")");
        }
        return new ResponseEntity<>(Map.of("message" , "updated Successfully") , HttpStatus.OK) ;
    }
}
