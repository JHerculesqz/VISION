package com.firelord.test.core.ai.opencv.vo.imgop.blur_by_bilateral;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurByBilateralInVo
 */
@ToString
public class BlurByBilateralInVo {
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
