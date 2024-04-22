
package com.employeemanagementsystem.empman.Controller;
import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/no-of-emp-in-a-department")
    private int noOfEmpInADepartment (@RequestParam int departmentId){
        int numberOfEmployeeInATeam = departmentService.getNumberOfEmployee(departmentId) ;
        return departmentId ;
    }

    @PutMapping("/change-the-name-of department")
    private ResponseEntity<String> changeTheNameOfDepartment (@RequestParam int departmentId , @RequestParam String newName)   {
        try {
        String changedName = departmentService.changeTheNameOfDepartment(departmentId , newName) ;
        return new ResponseEntity<>("Department Name Change Successfully" , HttpStatus.CREATED) ;
    }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST) ;
        }

}}
