package com.firelord.test.core.ai.opencv.vo.imgop.blur_by_erode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurByErodeInVo
 */
@ToString
public class BlurByErodeInVo {
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
