package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.empDto;
import com.employeemanagementsystem.empman.Enums.Designation;
import com.employeemanagementsystem.empman.Enums.Gender;
import com.employeemanagementsystem.empman.Exception.CompanyNotFound;
import com.employeemanagementsystem.empman.Exception.DepartmentAlreadyPresent;
import com.employeemanagementsystem.empman.Exception.EmployeeDoesNotExist;
import com.employeemanagementsystem.empman.Exception.WorkInASameDepartment;
import com.employeemanagementsystem.empman.Models.Company;
import com.employeemanagementsystem.empman.Models.Department;
import com.employeemanagementsystem.empman.Models.Employee;
import com.employeemanagementsystem.empman.Repository.CompanyRepo;
import com.employeemanagementsystem.empman.Repository.DepartmentRepo;
import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import com.employeemanagementsystem.empman.Transformers.EmployeeTransformer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private CompanyRepo companyRepo ;

    @Autowired
    private DepartmentRepo departmentRepo ;



    public String addEmp(empDto empDto) {
      Employee emp = EmployeeTransformer.covertDtoToEntity(empDto) ;
      employeeRepo.save(emp) ;
      return "Employee added successfully" ;
    }

    public List<Employee> getListOfEmployeeInaSameDepartment(int departmentId) {

        List<Employee> employeeList = employeeRepo.findAll() ;
        List<Employee> employeeList1 = new ArrayList<>() ;

        for (Employee emp :employeeList){

            if (emp.getDepartmentId() == departmentId){
                employeeList1.add(emp) ;
            }

        }
        return employeeList1 ;
    }


    public String changeName(int employeeId , String name) throws EmployeeDoesNotExist {
        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId) ;

        if (optionalEmployee.isEmpty()){
            throw new EmployeeDoesNotExist("The given Id of Employee is not correct") ;
        }

         else {
            Employee employee = optionalEmployee.get();
            employee.setEmployeeName(name);

            employeeRepo.save(employee);

            return "Name of the employee with employee Id" + employeeId +"successfully changed" ;
        }
    }

    public String deleteEmployee(int empId) throws EmployeeDoesNotExist{

        Optional<Employee> employee = employeeRepo.findById(empId) ;
        if (employee.isEmpty()){
            throw new EmployeeDoesNotExist("Employee with empID "+empId+" does not exist") ;
        }
        else{
            employeeRepo.deleteById(empId);
            return "Employee with empID " + empId + "removed successfully" ;
        }

    }

    public String deleteAllEmployee() {
         employeeRepo.deleteAll();
         return "All Employee Deleted Successfully" ;
    }

    public List<Employee> getListOfEmpAccToGender(Gender gender) {

        List<Employee> employeeList = employeeRepo.findAll();
        List<Employee> list = new ArrayList<>();

        for (Employee employee : employeeList){
            if(employee.getGender()==gender){
                list.add(employee);
            }
        }
        return list ;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    public List<Employee> getListOfEmployeeInACompany(int regsId) throws CompanyNotFound {
        List<Employee> employeeList = employeeRepo.findAll();
        Optional<Company> company = companyRepo.findById(regsId);
        List<Employee> result = new ArrayList<>() ;

        if (company.isEmpty()) {
            throw new CompanyNotFound("CompanyId Is Not Correct");
        } else {
            for (Employee emp : employeeList) {
                if (emp.getCompanyName().equals(company.get().getCompanyName())){
                    result.add(emp) ;
                }
            }
        }
        return result ;
    }

    public String changeTheCompany(int id , String name , Designation designation) throws EmployeeDoesNotExist{
        Optional<Employee> employeeOptional = employeeRepo.findById(id) ;
        if (employeeOptional.isEmpty()){
            throw new EmployeeDoesNotExist("Entered Employee-Id Is Not Correct");
        }
        else{
            Employee employee = employeeOptional.get() ;
            employee.setCompanyName(name);
            employee.setDesignation(designation);
            employeeRepo.save(employee);

            return "Employee Added TO New Company" ;
        }
    }

    public String fillTheLeftOutDetails(int id, String companyName, Designation designation, int departmentId, String departmentName, Gender gender) throws EmployeeDoesNotExist {
        Optional<Employee> employeeOptional = employeeRepo.findById(id) ;

        if (employeeOptional.isEmpty()){
            throw new EmployeeDoesNotExist("Entered Employee-Id Is Not Correct");
        }
        else {
            Employee employee = employeeOptional.get() ;
            employee.setCompanyName(companyName);
            employee.setDesignation(designation);
            employee.setDepartmentId(departmentId);
            employee.setDepartmentName(departmentName);
            employee.setGender(gender);
            employeeRepo.save(employee);
            return "The Data Of Employee With Id: " + id +" Is Successfully Updated" ;
        }
    }

    public String changeTheDepartment(int id, String departmentName) throws DepartmentAlreadyPresent , EmployeeDoesNotExist , WorkInASameDepartment {
        Optional<Department> optionalDepartment = departmentRepo.findByName(departmentName);

        if (optionalDepartment.isEmpty()) {
            throw new DepartmentAlreadyPresent("Department Name Is Not Correct");
        }
            Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isEmpty()){
            throw new EmployeeDoesNotExist("Employee Does Not Exist") ;
        }
        else {
            Employee employee = optionalEmployee.get();

            if(employee.getDepartmentName().equals(departmentName)){
                throw new WorkInASameDepartment("Employee Already Working In  Same Department");
            }

            employee.setDepartmentName(departmentName);
            employeeRepo.save(employee);

            return "The Department Of Employee With Employee-ID "+id+" Is Changed Successfully" ;
        }
    }
}