package com.firelord.opencv.test.vo.imgop.blur_by_median;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurByMedianInVo
 */
@ToString
public class BlurByMedianInVo {
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
     * type
     */
    @Setter
    @Getter
    private int type;

    //#endregion
}
