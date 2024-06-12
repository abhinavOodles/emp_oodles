package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Repository.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepo employeeRepo  ;


    @InjectMocks
    EmployeeService employeeService ;

    // use either Inject-mock or [Before-each void setUp()] both create new object
//    @BeforeEach
//    void setUp(){
//        this.employeeService = new EmployeeService(this.employeeRepo) ;
//    }

    @Test
    void getAllEmployee(){


       employeeService.getAllEmployee();

        verify(employeeRepo).findAll();
    }

}