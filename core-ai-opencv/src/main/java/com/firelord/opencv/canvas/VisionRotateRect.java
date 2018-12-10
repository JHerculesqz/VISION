package com.firelord.opencv.canvas;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.RotatedRect;

/**
 * VisionRotateRect
 */
@ToString
public class VisionRotateRect {
    //#region Fields

    /**
     * rect object
     */
    @Setter
    @Getter
    private RotatedRect rect;

    //#endregion

    //#region rate

    /**
     * w / h
     *
     * @return w/h
     */
    public double getRate() {
        double w = this.rect.size.width;
        double h = this.rect.size.height;
        double iRate = Math.min(w, h) / Math.max(w, h);
        return iRate;
    }

    //#endregion
}
