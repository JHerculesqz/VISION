package com.firelord.opencv.canvas;

import com.firelord.opencv.matrix.VisionMat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * VisionRect
 */
@ToString
public class VisionRect {
    //#region Fields

    /**
     * rect obj
     */
    @Setter
    @Getter
    private Rect rect;

    //#endregion

    //#region init

    public static VisionRect init(int iX, int iY, int iWidth, int iHeight) {
        VisionRect oDst = new VisionRect();

        Rect oRect = new Rect();
        oRect.x = iX;
        oRect.y = iY;
        oRect.width = iWidth;
        oRect.height = iHeight;
        oDst.setRect(oRect);

        return oDst;
    }

    //#endregion

    //#region drawRect

    public void drawRect(VisionMat oDst, Scalar oScalar) {
        Imgproc.rectangle(oDst.getMat(), this.rect.tl(), this.rect.br(), oScalar);
    }

    public static void drawRect(VisionMat oDst, Point oPointStart, Point oPointEnd,
                                Scalar oScalar, int iThickness, int iLineType, int iShift) {
        Imgproc.rectangle(oDst.getMat(), oPointStart, oPointEnd, oScalar,
                iThickness, iLineType, iShift);
    }

    //#endregion
}
