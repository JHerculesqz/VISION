package com.firelord.opencv.img.mo;

import com.firelord.opencv.canvas.VisionRect;
import com.firelord.opencv.canvas.VisionRotateRect;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

/**
 * VisionMatOfP
 */
@ToString
@Builder
public class VisionMatOfP {
    //#region Fields

    /**
     * MatOfPoint
     */
    @Setter
    @Getter
    private MatOfPoint matOfPoint;

    //#endregion

    //#region rectOuter

    /**
     * get outer visionRect
     *
     * @return outer visionRect
     */
    public VisionRect rectOuter() {
        VisionRect oVisionRect = new VisionRect();

        Rect oRect = Imgproc.boundingRect(this.matOfPoint);
        oVisionRect.setRect(oRect);

        return oVisionRect;
    }

    //#endregion

    //#region rectInner

    /**
     * get min getArea rect
     *
     * @return min getArea rect
     */
    public VisionRotateRect rectInner() {
        VisionRotateRect oVisionRotateRect = new VisionRotateRect();

        RotatedRect oRotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(this.matOfPoint.toArray()));
        oVisionRotateRect.setRect(oRotatedRect);

        return oVisionRotateRect;
    }

    //#endregion

    //#region getArea

    /**
     * get contour getArea
     *
     * @return contour getArea
     */
    public double getArea() {
        double iArea = Imgproc.contourArea(this.matOfPoint, false);
        return iArea;
    }

    //#endregion

    //#region getArcLength

    /**
     * contour arc length
     *
     * @return contour arc length
     */
    public double getArcLength() {
        double iArcLength = Imgproc.arcLength(new MatOfPoint2f(this.matOfPoint.toArray()),
                true);
        return iArcLength;
    }

    //#endregion

    //#region getCenterPoint

    /**
     * get contours center point
     *
     * @return contours center point
     */
    public Point getCenterPoint() {
        Point oPoint = new Point();

        Moments oMoments = Imgproc.moments(this.matOfPoint, false);
        oPoint.x = (int) (oMoments.m10 / oMoments.m00);
        oPoint.y = (int) (oMoments.m01 / oMoments.m00);

        return oPoint;
    }

    //#endregion

    //#region getInfo

    /**
     * get info
     *
     * @param oVisionRect       VisionRect
     * @param oVisionRotateRect VisionRotateRect
     * @param oVisionMatOfP     VisionMatOfP
     * @return VisionMatOfPInfo
     */
    public VisionMatOfPInfo getInfo(VisionRect oVisionRect, VisionRotateRect oVisionRotateRect,
                                    VisionMatOfP oVisionMatOfP, int iIndex) {
        VisionMatOfPInfo oInfo = new VisionMatOfPInfo();

        oInfo.setVisionMatRate(oVisionRect.getRate());
        oInfo.setVisionRotateMatRate(oVisionRotateRect.getRate());
        oInfo.setContoursArea(oVisionMatOfP.getArea());
        oInfo.setContourArcLength(oVisionMatOfP.getArcLength());
        Point oCenterPoint = oVisionMatOfP.getCenterPoint();
        oInfo.setContourCenterX(oCenterPoint.x);
        oInfo.setContourCenterY(oCenterPoint.y);
        oInfo.setIndex(iIndex);

        return oInfo;
    }

    //#endregion
}
