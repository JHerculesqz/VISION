package com.firelord.test.core.ai.opencv.vo.img_basic_feature.sobel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SobelInVo
 */
@ToString
public class SobelInVo {
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