package com.firelord.opencv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CoreAIOpenCVTestApplication
 */
@SpringBootApplication
public class CoreAIOpenCVTestApplication {
    //#region Const

    /**
     * dll file path
     * Attention:change to your file path,same as core-ai-opencv/pom.xml
     */
    private static final String FILE_PATH_DLL = "C:/1.Monkey/1.Research/1.Tools/opencv3.4.4/build/java/x86/opencv_java344.dll";

    static {
        System.load(FILE_PATH_DLL);
    }

    //#endregion

    //#region main

    public static void main(String[] args) {
        SpringApplication.run(CoreAIOpenCVTestApplication.class, args);
    }

    //#endregion
}
