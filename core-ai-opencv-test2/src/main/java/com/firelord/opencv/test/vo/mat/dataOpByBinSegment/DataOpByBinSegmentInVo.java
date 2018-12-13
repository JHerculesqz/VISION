package com.firelord.opencv.test.vo.mat.dataOpByBinSegment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByBinSegmentInVo
 */
@ToString
public class DataOpByBinSegmentInVo {
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
