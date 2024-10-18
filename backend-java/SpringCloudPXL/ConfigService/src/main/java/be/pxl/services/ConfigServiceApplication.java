package be.pxl.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
    Config server
 */

@EnableConfigServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ConfigServiceApplication
{
    public static void main( String[] args )
    {
        new SpringApplicationBuilder(ConfigServiceApplication.class).run(args);
    }
}
