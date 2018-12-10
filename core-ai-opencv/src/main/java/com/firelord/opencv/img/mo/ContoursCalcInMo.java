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

    /**
     * src vision mat
     */
    @Setter
    @Getter
    private VisionMat src;

    /**
     * if debug,debug file path
     */
    @Setter
    @Getter
    private String filePath4Debug;

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

    //#endregion
}
