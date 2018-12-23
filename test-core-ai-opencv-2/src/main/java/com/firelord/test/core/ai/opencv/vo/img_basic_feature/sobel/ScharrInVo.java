package com.firelord.test.core.ai.opencv.vo.img_basic_feature.sobel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ScharrInVo
 */
@ToString
public class ScharrInVo {
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
