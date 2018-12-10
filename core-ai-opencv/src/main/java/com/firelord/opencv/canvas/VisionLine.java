package com.firelord.opencv.canvas;

import com.firelord.opencv.matrix.VisionMat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * VisionLine
 */
public class VisionLine {
    //#region drawLine

    /**
     * drawLine
     *
     * @param oSrc    src vision mat
     * @param iXStart start x
     * @param iYStart start y
     * @param iXEnd   end x
     * @param iYEnd   end y
     * @param oScalar scalar
     */
    public static void drawLine(VisionMat oSrc, int iXStart, int iYStart,
                                int iXEnd, int iYEnd, Scalar oScalar) {
        Imgproc.line(oSrc.getMat(), new Point(iXStart, iYStart), new Point(iXEnd, iYEnd),
                oScalar);
    }

    /**
     * draw line
     *
     * @param oSrc       src vision mat
     * @param oPoint1    start point
     * @param oPoint2    end point
     * @param oScalar    scalar
     * @param iThickness thickness
     * @param iLineType  lineType
     * @param iShift     shift
     */
    public static void drawLine(VisionMat oSrc, Point oPoint1, Point oPoint2, Scalar oScalar,
                                int iThickness, int iLineType, int iShift) {
        Imgproc.line(oSrc.getMat(), oPoint1, oPoint2, oScalar, iThickness,
                iLineType, iShift);
    }

    //#endregion
}
