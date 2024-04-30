package com.employeemanagementsystem.empman.Controller;

import com.employeemanagementsystem.empman.Dtos.ServiceDto;
import com.employeemanagementsystem.empman.Models.Service;
import com.employeemanagementsystem.empman.Service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    service ser ;

    @PostMapping("/add-service")
    public ResponseEntity<?> addService (@RequestBody ServiceDto service){
        try {
            String res = ser.addService(service);
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Some Error Occurred" , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list-of-services-with-attach-company")
    public ResponseEntity<?> getListOfServiceAttachWithCompany(@RequestParam int serviceId){
        try {
            List<String> res = ser.getListOfServices(serviceId);
            return new ResponseEntity<>(res , HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-the-service-list")
    public ResponseEntity<?> changeThePayRateOfAService (@RequestParam int serviceId , @RequestParam int payRate){
        try {
            String res  = ser.changeThePayRate(serviceId , payRate);
            return new ResponseEntity<>(res , HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST) ;
        }
    }

    @GetMapping("/get-list-of-all-Services")
    public ResponseEntity<?> getListOfAllService(){
        try {
          List<Service>  serviceList =  ser.getListOfAllService() ;
            return new ResponseEntity<>(serviceList , HttpStatus.FOUND) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-discontinued-service")
    public ResponseEntity<?> deleteDiscontinuedServices (@RequestParam int serviceId){
        try {
            String res = ser.deleteService(serviceId);
            return new ResponseEntity<>(res , HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
