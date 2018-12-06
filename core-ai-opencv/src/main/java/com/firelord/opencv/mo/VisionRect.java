package com.firelord.opencv.mo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Rect;

/**
 * VisionRect
 */
@ToString
@Builder
public class VisionRect {
    //#region Fields

    /**
     * rect obj
     */
    @Setter
    @Getter
    private Rect rect;

    //#endregion
}
