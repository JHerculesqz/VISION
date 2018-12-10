package com.firelord.opencv.test.vo.img_basic_feature.hough;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HoughLineQueryInVo
 */
@ToString
public class HoughLineQueryInVo {
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

    /**
     * threshold
     */
    @Setter
    @Getter
    private int threshold;

    //#endregion
}