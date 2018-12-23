package com.firelord.test.core.ai.opencv.vo.mat.dataOpByAddWeight;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByAddWeightInVo
 */
@ToString
public class DataOpByAddWeightInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * alpha
     */
    @Setter
    @Getter
    private double alpha;

    /**
     * gamma
     */
    @Setter
    @Getter
    private double gamma;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}
