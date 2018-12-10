package com.firelord.opencv.test.vo.sobel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
