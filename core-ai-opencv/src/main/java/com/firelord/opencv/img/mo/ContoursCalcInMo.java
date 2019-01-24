package com.firelord.opencv.img.mo;

import com.firelord.opencv.mat.VisionMat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ContoursCalcInMo
 */
@ToString
@Builder
public class ContoursCalcInMo {
    //#region Fields

    //#region Common

    /**
     * src vision mat
     */
    @Setter
    @Getter
    private VisionMat src;

    /**
     * min area
     */
    @Setter
    @Getter
    private double areaMin;

    /**
     * max area
     */
    @Setter
    @Getter
    private double areaMax;

    /**
     * true:debug
     */
    @Setter
    @Getter
    private boolean debug;

    /**
     * if debug,debug dir path
     */
    @Setter
    @Getter
    private String dirPath4Debug;

    //#endregion

    //#region a1

    //#endregion

    //#region a2

    @Setter
    @Getter
    private int a2R1;

    @Setter
    @Getter
    private int a2G1;

    @Setter
    @Getter
    private int a2B1;

    @Setter
    @Getter
    private int a2R2;

    @Setter
    @Getter
    private int a2G2;

    @Setter
    @Getter
    private int a2B2;

    //#endregion

    //#region a3

    /**
     * morphology option:0~6
     */
    @Setter
    @Getter
    private int a3Morph;

    //#endregion

    //#endregion
}
