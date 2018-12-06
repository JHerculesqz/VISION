package com.firelord.opencv.img;

import com.firelord.opencv.canvas.VisionLine;
import com.firelord.opencv.canvas.VisionRect;
import com.firelord.opencv.image.mo.VisionMatOfP;
import com.firelord.opencv.image.mo.VisionMatOfPSet;
import com.firelord.opencv.matrix.VisionMat;
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
        VisionMat oBinary = new VisionMat();

        //threshold
        VisionMat oGray = oSrc.imgOpGray(Imgproc.COLOR_BGR2GRAY);
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

    //#region histogram

    /**
     * display histogram
     *
     * @param oSrc VisionMat
     * @return VisionMat histogram
     */
    public VisionMat histogramDisplay(VisionMat oSrc) {
        //gray
        VisionMat oGray = oSrc.imgOpGray(Imgproc.COLOR_BGR2GRAY);

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oSrc.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oGray.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram.getMat(), oHistogram.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //get histogram data
        float[] arrHistogramData = oHistogram.dataOpBySub(0, 0, 256);

        VisionMat oDst = VisionMat.initByCreate(400, 400, oSrc.getMat().type(),
                new Scalar(200, 200, 200));
        int iOffsetX = 50;
        int iOffsetY = 350;
        int iHeight = oHistogram.getMat().rows();
        VisionLine.drawLine(oDst, iOffsetX, 0, iOffsetX, iOffsetY, new Scalar(0, 0, 0));
        VisionLine.drawLine(oDst, iOffsetX, iOffsetY, 400, iOffsetY, new Scalar(0, 0, 0));
        for (int i = 0; i < iHeight - 1; i++) {
            int y1 = (int) arrHistogramData[i];
            VisionRect oRect = VisionRect.init(iOffsetX + i, iOffsetY - y1,
                    1, y1);
            oRect.drawRect(oDst, new Scalar(15, 15, 15));
        }

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray, oMask, oHistogram));

        return oDst;
    }

    /**
     * equalize histogram
     *
     * @param oSrc VisionMat
     * @return equalize histogram
     */
    public VisionMat histogramEqualize(VisionMat oSrc) {
        //gray
        VisionMat oGray = oSrc.imgOpGray(Imgproc.COLOR_BGR2GRAY);

        //equalize histogram
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray.getMat(), oDst.getMat());

        //destroy
        oGray.destroy();
        return oDst;
    }

    /**
     * TODO:not implement
     *
     * @param oSrc VisionMat
     * @return compare histogram
     */
    public double[] histogramCompare(VisionMat oSrc) {
        //gray
        VisionMat oGray = oSrc.imgOpGray(Imgproc.COLOR_BGR2GRAY);

        //equalize histogram
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray.getMat(), oDst.getMat());

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oSrc.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oGray.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram.getMat(), oHistogram.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //calc histogram
        VisionMat oHistogram2 = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oDst.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram2.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram2.getMat(), oHistogram2.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //compare
        double[] arrDistance = new double[7];
        arrDistance[0] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CORREL);
        arrDistance[1] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CHISQR);
        arrDistance[2] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_INTERSECT);
        arrDistance[3] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_BHATTACHARYYA);
        arrDistance[4] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_HELLINGER);
        arrDistance[5] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CHISQR_ALT);
        arrDistance[6] = Imgproc.compareHist(oHistogram.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_KL_DIV);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray, oMask, oDst, oHistogram, oHistogram2));

        return arrDistance;
    }

    /**
     * back project histogram
     *
     * @param oSrc      src visionMat
     * @param oTemplate template visionMat
     * @return back project VisionMat
     */
    public VisionMat histogramBackProject(VisionMat oSrc, VisionMat oTemplate) {
        //oHSVTemplate
        VisionMat oHSVTemplate = oTemplate.imgOpHSV();

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oTemplate.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oHSVTemplate.getMat()),
                new MatOfInt(0, 1), oMask.getMat(), oHistogram.getMat(),
                new MatOfInt(30, 32), new MatOfFloat(0, 179, 0, 255));

        //oHSVSrc
        VisionMat oHSVSrc = oSrc.imgOpHSV();

        //back project
        VisionMat oDst = new VisionMat();
        Imgproc.calcBackProject(Arrays.asList(oHSVSrc.getMat()),
                new MatOfInt(0, 1), oHistogram.getMat(), oDst.getMat(),
                new MatOfFloat(0, 179, 0, 255), 1);
        Core.normalize(oDst.getMat(), oDst.getMat(), 0, 255, Core.NORM_MINMAX);
        Imgproc.cvtColor(oDst.getMat(), oDst.getMat(), Imgproc.COLOR_GRAY2BGR);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oHSVTemplate, oMask, oHistogram, oHSVSrc));

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
        VisionRect.drawRect(oDst, oMatchPoint,
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
