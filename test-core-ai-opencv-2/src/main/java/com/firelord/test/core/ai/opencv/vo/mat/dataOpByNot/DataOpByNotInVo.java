package com.firelord.test.core.ai.opencv.vo.mat.dataOpByNot;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpByNotInVo
 */
@ToString
public class DataOpByNotInVo {
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
