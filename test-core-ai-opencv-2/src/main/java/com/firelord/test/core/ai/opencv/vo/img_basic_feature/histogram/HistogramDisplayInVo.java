package com.firelord.test.core.ai.opencv.vo.img_basic_feature.histogram;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HistogramDisplayInVo
 */
@ToString
public class HistogramDisplayInVo {
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
