package com.employeemanagementsystem.empman.Service;

import com.employeemanagementsystem.empman.Dtos.ServiceDto;
import com.employeemanagementsystem.empman.Exception.CompanyNotFound;
import com.employeemanagementsystem.empman.Exception.ServiceIsNotPresent;
import com.employeemanagementsystem.empman.Exception.ServicesAlreadyAdded;
import com.employeemanagementsystem.empman.Models.Company;
import com.employeemanagementsystem.empman.Repository.CompanyRepo;
import com.employeemanagementsystem.empman.Repository.ServiceRepo;
import com.employeemanagementsystem.empman.Transformers.ServiceTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class service {

    @Autowired
    ServiceRepo serviceRepo ;


    public String addService(ServiceDto service) {
        com.employeemanagementsystem.empman.Models.Service service1 = ServiceTransformer.convertDtoTOEntity(service);

        serviceRepo.save(service1) ;
        return "Service Added Successfully" ;
    }

    public  List<String> getListOfServices(int serviceId) throws ServiceIsNotPresent {
//         Optional<Company> optionalCompany = companyRepo.findById(companyId) ;
//
          List<String> res = new ArrayList<>() ;
//
//         if (optionalCompany.isEmpty()) {
//           throw  new CompanyNotFound("Entered Company-Id Is Not Correct") ;
//         }
//         else {
//             Company company =optionalCompany.get() ;
//             List<com.employeemanagementsystem.empman.Models.Service> serviceList = company.getServiceList() ;
//
//             for (com.employeemanagementsystem.empman.Models.Service service : serviceList){
//                 res.add(service.getService());
//             }
//         }
//         return res ;

        Optional<com.employeemanagementsystem.empman.Models.Service> serviceOptional = serviceRepo.findById(serviceId);

        if (serviceOptional.isEmpty()){
            throw new ServiceIsNotPresent("Wrong Service Id") ;
        }
        else {
            com.employeemanagementsystem.empman.Models.Service service = serviceOptional.get() ;
             List<Company> companyList = service.getCompany();

             for (Company company : companyList){
                 res.add(company.getCompanyName());
             }
            return res ;
        }
    }


    public String changeThePayRate(int serviceId , int payRate) throws ServiceIsNotPresent{
        Optional<com.employeemanagementsystem.empman.Models.Service> optionalService = serviceRepo.findById(serviceId);

        if (optionalService.isEmpty()){
            throw new ServiceIsNotPresent("Entered Wrong Service-Id: "+serviceId);
        }
        else{
            com.employeemanagementsystem.empman.Models.Service service = optionalService.get() ;
            service.setPayRateAsPerServices(payRate);
            serviceRepo.save(service);

            return "PayRate Has Been Updated Successfully" ;
        }
    }

    public List<com.employeemanagementsystem.empman.Models.Service> getListOfAllService() {
        return serviceRepo.findAll() ;
    }

    public String deleteService(int serviceId) throws ServiceIsNotPresent {
        Optional<com.employeemanagementsystem.empman.Models.Service> serviceOptional = serviceRepo.findById(serviceId);
        if (serviceOptional.isEmpty()) {
            throw new ServiceIsNotPresent("Wrong Service-Id");
        } else {
            serviceRepo.deleteById(serviceId);
            return "Service with serviceId: "+serviceId+"deleted successfully" ;
        }
    }
}
