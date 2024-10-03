package be.pxl.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Employee Service Main application
 */

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
}
