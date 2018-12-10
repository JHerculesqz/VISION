package com.firelord.opencv.test.vo.img_basic_feature.canny;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CannyEdgeQueryInVo
 */
@ToString
public class CannyEdgeQuery2InVo {
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
