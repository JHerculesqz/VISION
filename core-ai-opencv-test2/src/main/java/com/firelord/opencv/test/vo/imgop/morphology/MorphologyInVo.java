package com.firelord.opencv.test.vo.imgop.morphology;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * MorphologyInVo
 */
@ToString
public class MorphologyInVo {
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

    /**
     * option
     */
    @Setter
    @Getter
    private int option;

    //#endregion
}
