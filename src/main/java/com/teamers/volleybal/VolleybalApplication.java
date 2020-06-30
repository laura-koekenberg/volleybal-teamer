package com.teamers.volleybal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class VolleybalApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SpringApplication.run(VolleybalApplication.class, args);
    }

}
