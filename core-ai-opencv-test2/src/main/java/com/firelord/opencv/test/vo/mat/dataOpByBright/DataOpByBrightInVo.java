package com.firelord.opencv.test.vo.mat.dataOpByBright;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByBrightInVo
 */
@ToString
public class DataOpByBrightInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * scalar v
     */
    @Setter
    @Getter
    private double v;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}
