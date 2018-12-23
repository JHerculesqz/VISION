package com.firelord.test.core.ai.opencv.vo.mat.dataOpByOr;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByOrInVo
 */
@ToString
public class DataOpByOrInVo {
    //#region Fields

    /**
     * src1 file path
     */
    @Setter
    @Getter
    private String filePathSrc1;

    /**
     * src2 file path
     */
    @Setter
    @Getter
    private String filePathSrc2;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}
