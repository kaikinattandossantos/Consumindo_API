// src/main/java/com/base/demo/DemoApplication.java
package com.base.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // <-- A anotação mais importante!
public class DemoApplication {

    public static void main(String[] args) {
        // Apenas esta linha é necessária para iniciar toda a aplicação
        SpringApplication.run(DemoApplication.class, args);
    }
}