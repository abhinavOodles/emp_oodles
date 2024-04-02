package com.employeemanagementsystem.empman.Controller;
import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService ;

    @PostMapping("/add-department")
    public ResponseEntity<String> addDepartment (@RequestBody DepartmentEntryDto departmentDto){
        try {
           String result =  departmentService.addDepartment(departmentDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}