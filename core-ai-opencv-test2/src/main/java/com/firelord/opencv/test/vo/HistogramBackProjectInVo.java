package com.firelord.opencv.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HistogramEqualizeInVo
 */
@ToString
public class HistogramBackProjectInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * template file path
     */
    @Setter
    @Getter
    private String filePathTemplate;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}