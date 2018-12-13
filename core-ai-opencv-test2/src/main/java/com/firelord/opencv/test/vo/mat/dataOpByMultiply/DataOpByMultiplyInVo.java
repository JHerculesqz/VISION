package com.firelord.opencv.test.vo.mat.dataOpByMultiply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByMultiplyInVo
 */
@ToString
public class DataOpByMultiplyInVo {
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
