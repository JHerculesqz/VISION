package com.firelord.opencv.mat.mo;

import com.firelord.opencv.mat.VisionMat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByBinSegmentOutMo
 */
@ToString
public class DataOpByBinSegmentOutMo {
    //#region Fields

    /**
     * mean
     */
    @Setter
    @Getter
    private double[] mean;

    /**
     * standardDeviation
     */
    @Setter
    @Getter
    private double[] standardDeviation;

    @Setter
    @Getter
    private VisionMat dst;

    //#endregion
}
