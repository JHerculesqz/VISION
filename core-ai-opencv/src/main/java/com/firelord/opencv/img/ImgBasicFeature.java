package com.firelord.opencv.img;

import com.firelord.opencv.image.mo.VisionMatOfP;
import com.firelord.opencv.image.mo.VisionMatOfPSet;
import com.firelord.opencv.matrix.VisionMat;
import com.firelord.opencv.mo.VisionRect;
import com.firelord.opencv.mo.VisionRotateRect;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic image feature
 */
public class ImgBasicFeature {
    //#region contoursCalc

    /**
     * measure contours
     *
     * @param oSrc src matrix
     * @return dst matrix
     */
    public VisionMat contoursCalc(VisionMat oSrc) {
        VisionMat oDst = VisionMat.initByEye(oSrc);

        //generate oGray and oBinary
        VisionMat oGray = new VisionMat();
        VisionMat oBinary = new VisionMat();

        //threshold
        Imgproc.cvtColor(oSrc.getMat(), oGray.getMat(), Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(oGray.getMat(), oBinary.getMat(),
                0, 255,
                Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);

        //find contours
        VisionMatOfPSet oVisionMatOfPSet = this.contoursQuery(oBinary);

        //measure contours
        for (int iIndex = 0; iIndex < oVisionMatOfPSet.getVisionMatOfPList().size(); iIndex++) {
            VisionMatOfP oVisionMatOfP = oVisionMatOfPSet.getVisionMatOfPList().get(iIndex);

            VisionRect oVisionRect = oVisionMatOfP.rectOuter();
//            System.out.println(oVisionRect);

            VisionRotateRect oVisionRotateRect = oVisionMatOfP.rectInner();
//            System.out.println(oVisionRotateRect);

            double iArea = oVisionMatOfP.area();
//            System.out.println(iArea);

            double iLength = oVisionMatOfP.arcLength();
//            System.out.println(iLength);

            if (iArea >= 4000 && iArea <= 8000) {
                Moments oMoments = Imgproc.moments(oVisionMatOfP.getMatOfPoint(), false);
                int iX = (int) (oMoments.m10 / oMoments.m00);
                int iY = (int) (oMoments.m01 / oMoments.m00);
                System.out.println(iX);
                System.out.println(iY);

                this.contoursDraw(oDst, oVisionMatOfPSet, iIndex);
                Imgcodecs.imwrite("C:/test/contoursCalcRes" + iIndex + ".bmp", oDst.getMat());
            }
        }

        //destroyBatch
        oGray.destroy();
        oBinary.destroy();

        return oDst;
    }

    private VisionMatOfPSet contoursQuery(VisionMat oBinary) {
        VisionMat oHierarchy = new VisionMat();
        List<MatOfPoint> lstMatOfPoint = new ArrayList<>();
        Imgproc.findContours(oBinary.getMat(), lstMatOfPoint, oHierarchy.getMat(),
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));

        VisionMatOfPSet oVisionMatOfPSet = new VisionMatOfPSet(lstMatOfPoint);
        return oVisionMatOfPSet;
    }

    private void contoursDraw(VisionMat oDst, VisionMatOfPSet VisionMatOfPSet, int iIndex) {
        Imgproc.drawContours(oDst.getMat(),
                VisionMatOfPSet.getVisionMatOfPListOrig(), iIndex,
                new Scalar(0, 0, 255), 1);
    }

    //#endregion

    //#region histogramDisplay

    public VisionMat histogramDisplay(VisionMat oSrc) {
        //gray
        Mat oGray = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oGray, Imgproc.COLOR_BGR2GRAY);

        // calc histogram
        List<Mat> lstImage = Arrays.asList(oGray);
        Mat oMask = Mat.ones(oSrc.getMat().size(), CvType.CV_8UC1);
        Mat oHistogram = new Mat();
        Imgproc.calcHist(lstImage, new MatOfInt(0), oMask, oHistogram,
                new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram, oHistogram, 0, 255, Core.NORM_MINMAX);

        //gen oDst
        VisionMat oDst = new VisionMat();
        oDst.getMat().create(400, 400, oSrc.getMat().type());
        oDst.getMat().setTo(new Scalar(200, 200, 200));
        float[] arrHistogramData = new float[256];
        oHistogram.get(0, 0, arrHistogramData);

        //draw histogram
        int iOffsetX = 50;
        int iOffsetY = 350;
        Imgproc.line(oDst.getMat(), new Point(iOffsetX, 0), new Point(iOffsetX, iOffsetY),
                new Scalar(0, 0, 0));
        Imgproc.line(oDst.getMat(), new Point(iOffsetX, iOffsetY), new Point(400, iOffsetY),
                new Scalar(0, 0, 0));
        for (int i = 0; i < oHistogram.rows() - 1; i++) {
            int y1 = (int) arrHistogramData[i];
//            int y2 = (int) arrHistogramData[i + 1];
            Rect oRect = new Rect();
            oRect.x = iOffsetX + i;
            oRect.y = iOffsetY - y1;
            oRect.width = 1;
            oRect.height = y1;
            Imgproc.rectangle(oDst.getMat(), oRect.tl(), oRect.br(), new Scalar(15, 15, 15));
        }

        //release
        oGray.release();

        return oDst;
    }

    //#endregion

    //#region histogramEqualize

    public VisionMat histogramEqualize(VisionMat oSrc) {
        //gray
        Mat oGray = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oGray, Imgproc.COLOR_BGR2GRAY);

        //equalize histogram
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray, oDst.getMat());
        oGray.release();
        return oDst;
    }

    //#endregion

    //#region histogramCompare

    public double[] histogramCompare(VisionMat oSrc) {
        //gray
        Mat oGray = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oGray, Imgproc.COLOR_BGR2GRAY);
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray, oDst.getMat());

        //oHistogram1
        List<Mat> arrImage1 = Arrays.asList(oGray);
        Mat oMask = Mat.ones(oSrc.getMat().size(), CvType.CV_8UC1);
        Mat oHistogram1 = new Mat();
        Imgproc.calcHist(arrImage1, new MatOfInt(0), oMask, oHistogram1,
                new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram1, oHistogram1, 0, 255, Core.NORM_MINMAX);

        //oHistogram2
        List<Mat> arrImage2 = Arrays.asList(oDst.getMat());
        Mat oHistogram2 = new Mat();
        Imgproc.calcHist(arrImage2, new MatOfInt(0), oMask, oHistogram2,
                new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram2, oHistogram2, 0, 255, Core.NORM_MINMAX);

        //compare
        double[] arrDistance = new double[7];
        arrDistance[0] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_CORREL);
        arrDistance[1] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_CHISQR);
        arrDistance[2] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_INTERSECT);
        arrDistance[3] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_BHATTACHARYYA);
        arrDistance[4] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_HELLINGER);
        arrDistance[5] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_CHISQR_ALT);
        arrDistance[6] = Imgproc.compareHist(oHistogram1, oHistogram2, Imgproc.HISTCMP_KL_DIV);

        //destroyBatch
        oGray.release();
        oDst.destroy();
        oHistogram1.release();
        oHistogram2.release();

        return arrDistance;
    }

    //#endregion

    //#region histogramBackProject

    public VisionMat histogramBackProject(VisionMat oSrc, VisionMat oTemp) {
        //hsv
        Mat oHSV = new Mat();
        Imgproc.cvtColor(oTemp.getMat(), oHSV, Imgproc.COLOR_BGR2HSV);
        Mat oMask = Mat.ones(oTemp.getMat().size(), CvType.CV_8UC1);
        Mat oHistogram = new Mat();
        Imgproc.calcHist(Arrays.asList(oHSV), new MatOfInt(0, 1), oMask, oHistogram,
                new MatOfInt(30, 32), new MatOfFloat(0, 179, 0, 255));

        //oHSVSrc
        Mat oHSVSrc = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oHSVSrc, Imgproc.COLOR_BGR2HSV);

        //oDst
        VisionMat oDst = new VisionMat();
        Imgproc.calcBackProject(Arrays.asList(oHSVSrc), new MatOfInt(0, 1),
                oHistogram, oDst.getMat(), new MatOfFloat(0, 179, 0, 255), 1);
        Core.normalize(oDst.getMat(), oDst.getMat(), 0, 255, Core.NORM_MINMAX);
        Imgproc.cvtColor(oDst.getMat(), oDst.getMat(), Imgproc.COLOR_GRAY2BGR);

        return oDst;
    }

    //#endregion

    //#region templateQuery

    /**
     * match template
     *
     * @param oTemplate    VisionMat template
     * @param oMatSrc      VisionMat src
     * @param iMatchMethod 可选值:0~5(Imgproc.TM_*)
     * @return VisionMat dst
     */
    public VisionMat templateQuery(VisionMat oTemplate, VisionMat oMatSrc, int iMatchMethod) {
        //match template
        int iHeight = oMatSrc.getMat().rows() - oTemplate.getMat().rows() + 1;
        int iWidth = oMatSrc.getMat().cols() - oTemplate.getMat().cols() + 1;
        VisionMat oMatchRes = VisionMat.initByWH(iWidth, iHeight, CvType.CV_32FC1);
        Imgproc.matchTemplate(oMatSrc.getMat(), oTemplate.getMat(), oMatchRes.getMat(),
                iMatchMethod);

        //analyse oMatchRes
        Core.MinMaxLocResult oMinMaxLocResult = Core.minMaxLoc(oMatchRes.getMat());
        Point oMaxPoint = oMinMaxLocResult.maxLoc;
        Point oMinPoint = oMinMaxLocResult.minLoc;
        Point oMatchPoint;
        if (iMatchMethod == Imgproc.TM_SQDIFF || iMatchMethod == Imgproc.TM_SQDIFF_NORMED) {
            oMatchPoint = oMinPoint;
        } else {
            oMatchPoint = oMaxPoint;
        }

        //gen oDst
        VisionMat oDst = VisionMat.initByCopy(oMatSrc);
        Imgproc.rectangle(oDst.getMat(), oMatchPoint,
                new Point(oMatchPoint.x + oTemplate.getMat().cols(),
                        oMatchPoint.y + oTemplate.getMat().rows()),
                new Scalar(0, 0, 255), 2, 8, 0);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oTemplate, oMatchRes));

        //return
        return oDst;
    }

    //#endregion
}
