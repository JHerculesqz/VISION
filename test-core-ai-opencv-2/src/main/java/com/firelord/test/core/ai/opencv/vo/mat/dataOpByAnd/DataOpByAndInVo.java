package com.firelord.test.core.ai.opencv.vo.mat.dataOpByAnd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByAndInVo
 */
@ToString
public class DataOpByAndInVo {
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
