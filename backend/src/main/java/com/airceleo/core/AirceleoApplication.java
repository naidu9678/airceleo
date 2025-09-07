package com.airceleo.app;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.airceleo")
public class AirceleoApplication {
  public static void main(String[] args){ SpringApplication.run(AirceleoApplication.class, args); }
}
