package com.firelord.opencv.test.vo.mat.dataOpByBinSegment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByBinSegmentOutVo
 */
@ToString
public class DataOpByBinSegmentOutVo {
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

    //#endregion
}
