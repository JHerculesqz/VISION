package com.firelord.opencv.test.vo.img_basic_feature.contours;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ContoursCalcInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * if debug,debug file path
     */
    @Setter
    @Getter
    private String filePath4Debug;

    /**
     * min area
     */
    @Setter
    @Getter
    private double areaMin;

    /**
     * max area
     */
    @Setter
    @Getter
    private double areaMax;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}
