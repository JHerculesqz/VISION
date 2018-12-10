package com.firelord.opencv.img.mo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * VisionMatOfPInfo
 */
@ToString
public class VisionMatOfPInfo {
    //#region Fields

    /**
     * visionMatRate
     */
    @Setter
    @Getter
    private double visionMatRate;

    /**
     * visionRotateMatRate
     */
    @Setter
    @Getter
    private double visionRotateMatRate;

    /**
     * contoursArea
     */
    @Setter
    @Getter
    private double contoursArea;

    /**
     * contourArcLength
     */
    @Setter
    @Getter
    private double contourArcLength;

    /**
     * contourCenterX
     */
    @Setter
    @Getter
    private double contourCenterX;

    /**
     * contourCenterY
     */
    @Setter
    @Getter
    private double contourCenterY;

    /**
     * index
     */
    @Setter
    @Getter
    private int index;

    //#endregion
}
