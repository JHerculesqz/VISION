package com.firelord.opencv.img.mo;

import com.firelord.opencv.mat.VisionMat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ContoursCalcInMo
 */
@ToString
public class ContoursCalcInMo {
    //#region Fields

    //#region Common

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * src vision mat
     */
    @Setter
    @Getter
    private VisionMat src;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

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
    private double[] a2RGBStart;

    @Setter
    @Getter
    private double[] a2RGBEnd;

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

    //#region calc

    public void calc() {
        this.setSrc(VisionMat.initByFilePath(this.getFilePathSrc()));
    }

    //#endregion
}
