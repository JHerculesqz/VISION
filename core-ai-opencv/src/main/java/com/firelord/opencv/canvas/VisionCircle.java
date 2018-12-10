package com.firelord.opencv.canvas;

import com.firelord.opencv.mat.VisionMat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * VisionCircle
 */
public class VisionCircle {
    //#region drawCircle

    /**
     * drawCircle
     *
     * @param oSrc       src vision mat
     * @param iX         x
     * @param iY         y
     * @param iR         radius
     * @param oScalar    scalar
     * @param iThickness thickness
     * @param iLineType  line type
     * @param iShift     shift
     */
    public static void drawCircle(VisionMat oSrc, int iX, int iY, int iR, Scalar oScalar,
                                  int iThickness, int iLineType, int iShift) {
        Imgproc.circle(oSrc.getMat(), new Point(iX, iY), iR, oScalar,
                iThickness, iLineType, iShift);
    }

    //#endregion
}
