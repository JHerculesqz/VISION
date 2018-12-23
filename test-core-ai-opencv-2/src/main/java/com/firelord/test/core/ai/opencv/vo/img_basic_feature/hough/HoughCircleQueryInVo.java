package com.firelord.test.core.ai.opencv.vo.img_basic_feature.hough;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HoughCircleQueryInVo
 */
@ToString
public class HoughCircleQueryInVo {
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
