package com.firelord.opencv.test.vo.canny;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CannyEdgeQueryInVo
 */
@ToString
public class CannyEdgeQueryInVo {
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
