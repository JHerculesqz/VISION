package com.firelord.test.core.ai.opencv.vo.mat.dataOpBySub;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DataOpBySubInVo
 */
@ToString
public class DataOpBySubInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * row
     */
    @Setter
    @Getter
    private int row;

    /**
     * col
     */
    @Setter
    @Getter
    private int col;

    /**
     * length
     */
    @Setter
    @Getter
    private int length;

    /**
     * subData
     */
    @Setter
    @Getter
    private float[] subData;

    //#endregion
}
