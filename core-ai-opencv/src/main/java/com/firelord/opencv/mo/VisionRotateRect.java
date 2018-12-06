package com.firelord.opencv.mo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.RotatedRect;

/**
 * VisionRotateRect
 */
@ToString
@Builder
public class VisionRotateRect {
    //#region Fields

    /**
     * rect object
     */
    @Setter
    @Getter
    private RotatedRect rect;

    //#endregion
}
