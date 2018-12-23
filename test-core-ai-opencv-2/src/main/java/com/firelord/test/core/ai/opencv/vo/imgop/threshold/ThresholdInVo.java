package com.firelord.test.core.ai.opencv.vo.imgop.threshold;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ThresholdInVo
 */
@ToString
public class ThresholdInVo {
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
