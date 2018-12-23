package com.firelord.test.core.ai.opencv.vo.mat.dataOpBySplit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpBySplitInVo
 */
@ToString
public class DataOpBySplitInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * dst file path prefix
     */
    @Setter
    @Getter
    private String filePathDstPrefix;


    //#endregion
}
