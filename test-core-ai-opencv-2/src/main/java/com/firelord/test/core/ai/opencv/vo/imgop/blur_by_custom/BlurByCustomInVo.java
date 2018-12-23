package com.firelord.test.core.ai.opencv.vo.imgop.blur_by_custom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurByCustomInVo
 */
@ToString
public class BlurByCustomInVo {
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
