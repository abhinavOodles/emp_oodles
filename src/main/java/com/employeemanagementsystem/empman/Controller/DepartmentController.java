
package com.employeemanagementsystem.empman.Controller;
import com.employeemanagementsystem.empman.Dtos.DepartmentEntryDto;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService ;

    // add all details of department in the database
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

    //get number of all employee working in a same department
    @GetMapping("/no-of-emp-in-a-department")
    private int noOfEmpInADepartment (@RequestParam int departmentId){
        return departmentService.getNumberOfEmployee(departmentId);
    }

    //change the name of department in the database
    @PutMapping("/change-the-name-of department")
    private ResponseEntity<String> changeTheNameOfDepartment (@RequestParam int departmentId , @RequestParam String newName)   {
        try {
        String changedName = departmentService.changeTheNameOfDepartment(departmentId , newName) ;
        return new ResponseEntity<>("Department Name Change Successfully" , HttpStatus.CREATED) ;
    }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST) ;
        }

    }

    //get list of all employee working in a same department
    @GetMapping("/list-of-employee-in-department")
    public ResponseEntity<?> listOfEmployee (@RequestParam int departmentId)  {
        try {
            List<Employee> res = departmentService.listOfEmployeeInADepartment(departmentId);
            return new ResponseEntity<>(res , HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all the department present in the database
    @GetMapping("/list-of-departments")
    public List<Department> listOfDepartments () {
        return departmentService.listOfDepartments()  ;
    }

    // delete the single department by departmentId
    @DeleteMapping("/delete-departmentById")
    public ResponseEntity<String> deleteDepartments (@RequestParam int departmentId) {
        try {
            departmentService.deleteDepartments(departmentId);
            return new ResponseEntity<>("Department with department-Id " + departmentId + " deleted successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }

    //delete all the database present in the database
    @DeleteMapping("delete-all-departments")
    public ResponseEntity<String> deleteAllDepartments(){
        departmentService.deleteAllDepartments();
        return new ResponseEntity<>("All Departments Deleted Successfully" ,  HttpStatus.OK) ;
    }

    //to change the department of a employee
    @PutMapping("/change-the-department-of-a-employee")
    private ResponseEntity<?> changeTheDepartment(@RequestParam int employeeId ,  @RequestParam int departmentId , @RequestParam String departmentName){
        try {
          String res =   departmentService.changeTheDepartment(employeeId,departmentId,departmentName);
            return new ResponseEntity<>(res,HttpStatus.FOUND)   ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // set the salary of emp acc to department
    @PutMapping("/put-the-salary-of-employee")
    private ResponseEntity<String>SetTheSalaryOfEmployee(@RequestParam int employeeId , @RequestParam int salary){
        try {
            String res = departmentService.setTheSalaryOfEmployee(employeeId,salary);
            return new ResponseEntity<>(res , HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
