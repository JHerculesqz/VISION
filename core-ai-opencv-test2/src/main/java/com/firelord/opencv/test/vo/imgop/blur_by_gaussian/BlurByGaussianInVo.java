package com.firelord.opencv.test.vo.imgop.blur_by_gaussian;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurInVo
 */
@ToString
public class BlurByGaussianInVo {
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
