package com.firelord.opencv.canvas;

import com.firelord.opencv.matrix.VisionMat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class VisionLine {
    //#region drawLine

    public static void drawLine(VisionMat oSrc, int iXStart, int iYStart,
                                int iXEnd, int iYEnd, Scalar oScalar) {
        Imgproc.line(oSrc.getMat(), new Point(iXStart, iYStart), new Point(iXEnd, iYEnd),
                oScalar);
    }

    //#endregion
}
