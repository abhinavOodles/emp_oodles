package com.employeemanagementsystem.empman.Transformers;

import com.employeemanagementsystem.empman.Dtos.ServiceDto;
import com.employeemanagementsystem.empman.Models.Service;

public class ServiceTransformer {

    public static Service convertDtoTOEntity (ServiceDto serviceDto){
        Service service = Service.builder()
                .companyId(serviceDto.getCompanyId())
                .service(serviceDto.getService())
                .payRateAsPerServices(serviceDto.getPayRateAsPerServices())
                .build()  ;

        return service ;
    }
}
