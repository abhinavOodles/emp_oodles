package com.employeemanagementsystem.empman.Controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController implements HealthIndicator {

    public static final String DB_SERVICE = "Database Service" ;
    @Override
    public Health health() {
        if (isHealthGood()){
            return Health.up().withDetail(DB_SERVICE , "Database service is running").build();
        }
        return Health.down().withDetail(DB_SERVICE , "Database is running").build() ;
    }

    public boolean isHealthGood(){
        return true ;
    }
}
