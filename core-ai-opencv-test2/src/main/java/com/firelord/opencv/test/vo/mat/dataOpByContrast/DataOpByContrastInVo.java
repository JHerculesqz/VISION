package com.firelord.opencv.test.vo.mat.dataOpByContrast;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByContrastInVo
 */
@ToString
public class DataOpByContrastInVo {
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
