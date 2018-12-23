package com.firelord.test.core.ai.opencv.vo.imgop.blur_by_pyr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurByPyrInVo
 */
@ToString
public class BlurByPyrInVo {
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
