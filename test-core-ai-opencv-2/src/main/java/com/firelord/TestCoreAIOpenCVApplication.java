package com.firelord;

import com.firelord.opencv.VisionInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TestCoreAIOpenCVApplication
 */
@SpringBootApplication
public class TestCoreAIOpenCVApplication {
    //#region main

    public static void main(String[] args) {
        VisionInit.init();
        SpringApplication.run(TestCoreAIOpenCVApplication.class, args);
    }

    //#endregion
}
