package com.firelord.opencv;

import com.firelord.component.os.env.EnvUtilsEx;

/**
 * VisionInit
 */
public class VisionInit {
    //#region init

    /**
     * init
     */
    public static void init() {
        String strOS = System.getProperty("os.name").toLowerCase();
        if (strOS.startsWith("windows")) {
            System.load(EnvUtilsEx.getEnvValue(EnvUtilsEx.WINROOT) +
                    "/opencv-344/x86/opencv_java344.dll");
        } else {
            System.load("/opt/opencv-344/x86/opencv_java344.dll");
        }
    }

    //#endregion
}
