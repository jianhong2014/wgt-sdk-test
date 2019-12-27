package com.gvr.sdk.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gvr.datahub.sdk.wgt"})
public class TestWgtSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestWgtSdkApplication.class, args);
    }
}
