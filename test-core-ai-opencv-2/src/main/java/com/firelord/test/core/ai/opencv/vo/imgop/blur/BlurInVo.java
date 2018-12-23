package com.firelord.test.core.ai.opencv.vo.imgop.blur;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BlurInVo
 */
@ToString
public class BlurInVo {
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
