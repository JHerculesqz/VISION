package com.firelord.opencv.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HistogramEqualizeInVo
 */
@ToString
public class HistogramEqualizeInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}