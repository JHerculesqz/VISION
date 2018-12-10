package com.firelord.opencv.test.vo.hough;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
