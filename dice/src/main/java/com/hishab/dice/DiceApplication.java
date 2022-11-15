package com.hishab.dice;

import com.hishab.dice.config.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiceApplication.class, args);
        System.out.println(AppConstant.APPLICATION_TITLE + " Starts...");
    }

}
