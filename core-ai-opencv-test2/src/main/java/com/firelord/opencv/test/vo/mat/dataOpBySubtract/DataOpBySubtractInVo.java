package com.firelord.opencv.test.vo.mat.dataOpBySubtract;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpBySubtractInVo
 */
@ToString
public class DataOpBySubtractInVo {
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
