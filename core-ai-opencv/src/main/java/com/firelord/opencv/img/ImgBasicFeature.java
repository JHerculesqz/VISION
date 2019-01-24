package com.firelord.opencv.img;

import com.firelord.opencv.VisionTools;
import com.firelord.opencv.canvas.VisionCircle;
import com.firelord.opencv.canvas.VisionLine;
import com.firelord.opencv.canvas.VisionRect;
import com.firelord.opencv.canvas.VisionRotateRect;
import com.firelord.opencv.img.mo.*;
import com.firelord.opencv.mat.VisionMat;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic image feature
 */
public class ImgBasicFeature {
    //#region sobel

    /**
     * sobel
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat sobel(VisionMat oSrc) {
        //x grad
        VisionMat oGradX = new VisionMat();
        Imgproc.Sobel(oSrc.getMat(), oGradX.getMat(), CvType.CV_32F, 1, 0);
        Core.convertScaleAbs(oGradX.getMat(), oGradX.getMat());

        //y grad
        VisionMat oGradY = new VisionMat();
        Imgproc.Sobel(oSrc.getMat(), oGradY.getMat(), CvType.CV_32F, 0, 1);
        Core.convertScaleAbs(oGradY.getMat(), oGradY.getMat());

        //weight
        VisionMat oDst = new VisionMat();
        Core.addWeighted(oGradX.getMat(), 0.5, oGradY.getMat(), 0.5,
                0, oDst.getMat());

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGradX, oGradY));

        return oDst;
    }

    /**
     * scharr
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat scharr(VisionMat oSrc) {
        //x grad
        VisionMat oGradX = new VisionMat();
        Imgproc.Scharr(oSrc.getMat(), oGradX.getMat(), CvType.CV_32F, 1, 0);
        Core.convertScaleAbs(oGradX.getMat(), oGradX.getMat());

        //y grad
        VisionMat oGradY = new VisionMat();
        Imgproc.Scharr(oSrc.getMat(), oGradY.getMat(), CvType.CV_32F, 0, 1);
        Core.convertScaleAbs(oGradY.getMat(), oGradY.getMat());

        //weight
        VisionMat oDst = new VisionMat();
        Core.addWeighted(oGradX.getMat(), 0.5, oGradY.getMat(), 0.5,
                0, oDst.getMat());

        return oDst;
    }

    /**
     * laplian
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat laplian(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        Imgproc.Laplacian(oSrc.getMat(), oDst.getMat(), CvType.CV_32F, 3,
                1.0, 0);
        Core.convertScaleAbs(oDst.getMat(), oDst.getMat());

        return oDst;
    }

    //#endregion

    //#region canny

    /**
     * cannyEdgeQuery
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat cannyEdgeQuery(VisionMat oSrc) {
        //gaussian blur
        Imgproc.GaussianBlur(oSrc.getMat(), oSrc.getMat(), new Size(3, 3),
                0);

        //gray
        VisionMat oGray = VisionTools.imgOP.gray(oSrc);

        //canny
        VisionMat oEdges = new VisionMat();
        Imgproc.Canny(oSrc.getMat(), oEdges.getMat(), 50, 150,
                3, true);
        VisionMat oDst = new VisionMat();
        Core.bitwise_and(oSrc.getMat(), oSrc.getMat(), oDst.getMat(), oEdges.getMat());

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray, oGray));

        return oDst;
    }

    /**
     * cannyEdgeQuery2
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat cannyEdgeQuery2(VisionMat oSrc) {
        //x sobel
        VisionMat oGradX = new VisionMat();
        Imgproc.Sobel(oSrc.getMat(), oGradX.getMat(), CvType.CV_16S, 1, 0);

        //y sobel
        VisionMat oGradY = new VisionMat();
        Imgproc.Sobel(oSrc.getMat(), oGradY.getMat(), CvType.CV_16S, 0, 1);

        //canny
        VisionMat oDst = new VisionMat();
        VisionMat oEdges = new VisionMat();
        Imgproc.Canny(oGradX.getMat(), oGradY.getMat(), oEdges.getMat(),
                50, 150);
        Core.bitwise_and(oSrc.getMat(), oSrc.getMat(), oDst.getMat(), oEdges.getMat());

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGradX, oGradY, oEdges));

        return oDst;
    }

    //#endregion

    //#region hough

    /**
     * TODO:need refactor
     * hough line query
     *
     * @param oSrc       src VisionMat
     * @param iThreshold threshold
     * @return dst VisionMat
     */
    public VisionMat houghLineQuery(VisionMat oSrc, int iThreshold) {
        //canny
        VisionMat oEdges = new VisionMat();
        Imgproc.Canny(oSrc.getMat(), oEdges.getMat(), 50, 150,
                3, true);

        //houghLine
        VisionMat oLines = new VisionMat();
        Imgproc.HoughLines(oEdges.getMat(), oLines.getMat(), 1,
                Math.PI / 180.0, iThreshold);

        //draw line
        VisionMat oOut = VisionMat.initZeros(oSrc.getMat().size(), oSrc.getMat().type());
        float[] arrData = new float[2];
        for (int i = 0; i < oLines.getMat().rows(); i++) {
            oLines.getMat().get(i, 0, arrData);
            float iRHO = arrData[0];
            float iTheta = arrData[1];
            double iA = Math.cos(iTheta);
            double iB = Math.sin(iTheta);
            double iX0 = iA * iRHO;
            double iY0 = iB * iRHO;
            Point oPoint1 = new Point();
            Point oPoint2 = new Point();
            oPoint1.x = Math.round(iX0 + 1000 * (-iB));
            oPoint1.y = Math.round(iY0 + 1000 * (iA));
            oPoint2.x = Math.round(iX0 - 1000 * (-iB));
            oPoint2.y = Math.round(iY0 - 1000 * (iA));
            VisionLine.drawLine(oOut, oPoint1, oPoint2, new Scalar(0, 0, 255),
                    3, Imgproc.LINE_AA, 0);
        }
        VisionMat oDst = VisionMat.initByCopy(oOut);

        //destroyPatch
        VisionMat.destroyBatch(Arrays.asList(oEdges, oLines, oOut));

        return oDst;
    }

    /**
     * TODO:need refactor
     * hough line query
     *
     * @param oSrc src VisionMat
     * @return dst VisionMat
     */
    public VisionMat houghLinePQuery(VisionMat oSrc) {
        //canny
        VisionMat oEdges = new VisionMat();
        Imgproc.Canny(oSrc.getMat(), oEdges.getMat(), 50, 150,
                3, true);

        //houghLine
        VisionMat oLines = new VisionMat();
        Imgproc.HoughLinesP(oEdges.getMat(), oLines.getMat(), 1,
                Math.PI / 180.0, 100, 50, 10);

        //draw lines
        VisionMat oOut = VisionMat.initZeros(oSrc.getMat().size(), oSrc.getMat().type());
        for (int i = 0; i < oLines.getMat().rows(); i++) {
            int[] arrOneLine = new int[4];
            oLines.getMat().get(i, 0, arrOneLine);
            VisionLine.drawLine(oOut, new Point(arrOneLine[0], arrOneLine[1]),
                    new Point(arrOneLine[2], arrOneLine[3]),
                    new Scalar(0, 0, 255), 2, 8, 0);
        }
        VisionMat oDst = VisionMat.initByCopy(oOut);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oEdges, oLines, oOut));

        return oDst;
    }

    /**
     * TODO:need refactor
     * hough circle query
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat houghCircleQuery(VisionMat oSrc) {
        //gray
        VisionMat oGray = new VisionMat();
        Imgproc.pyrMeanShiftFiltering(oSrc.getMat(), oGray.getMat(), 15, 80);
        Imgproc.cvtColor(oGray.getMat(), oGray.getMat(), Imgproc.COLOR_BGR2GRAY);

        //gaussian blur
        Imgproc.GaussianBlur(oGray.getMat(), oGray.getMat(), new Size(3, 3),
                0);

        //detect circles
        VisionMat oDst = VisionMat.initByOnes(oSrc.getMat().size(), oSrc.getMat().type());
        VisionMat oCircles = new VisionMat();
        Imgproc.HoughCircles(oGray.getMat(), oCircles.getMat(), Imgproc.HOUGH_GRADIENT,
                1, 20, 100, 30,
                10, 200);
        for (int i = 0; i < oCircles.getMat().cols(); i++) {
            float[] arrInfo = new float[3];
            oCircles.getMat().get(0, i, arrInfo);
            int iX = (int) arrInfo[0];
            int iY = (int) arrInfo[1];
            int iR = (int) arrInfo[2];
            VisionCircle.drawCircle(oDst, iX, iY, iR, new Scalar(0, 255, 0),
                    2, 8, 0);
        }

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray, oCircles));

        return oDst;
    }

    //#endregion

    //#region contours

    //#region Common

    public ContoursCalcOutMo contoursCalc(ContoursCalcInMo oInMo,
                                          IContoursCalc oIContoursCalc) {
        //prepare
        VisionMat oPrepare = oIContoursCalc.prepare(oInMo);

        //calc
        VisionMatOfPSet oVisionMatOfPSet = oIContoursCalc.calc(oPrepare, oInMo);

        //measure
        ContoursCalcOutMo oOutMo = oIContoursCalc.measure(oVisionMatOfPSet, oInMo);

        return oOutMo;
    }

    public VisionMatOfPSet contoursCalc4Calc(VisionMat oPrepare, ContoursCalcInMo oInMo) {
        //calc
        VisionMat oHierarchy = new VisionMat();
        List<MatOfPoint> lstContours = new ArrayList<>();
        Imgproc.findContours(oPrepare.getMat(), lstContours, oHierarchy.getMat(),
                Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE, new Point(0, 0));
        VisionMatOfPSet oVisionMatOfPSet = new VisionMatOfPSet(lstContours);
        if (oInMo.isDebug()) {
            oHierarchy.save(oInMo.getDirPath4Debug() + "debug_hierarchy.bmp");
        }

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oHierarchy));

        return oVisionMatOfPSet;
    }

    public ContoursCalcOutMo contoursCalc4Measure(VisionMatOfPSet oVisionMatOfPSet,
                                                  ContoursCalcInMo oInMo) {
        ContoursCalcOutMo oOutMo = new ContoursCalcOutMo();

        oOutMo.setDst(VisionMat.initByEye(oInMo.getSrc()));
        for (VisionMatOfP oVisionMatOfP : oVisionMatOfPSet.getVisionMatOfPList()) {
            int iIndex = oVisionMatOfPSet.getVisionMatOfPList().indexOf(oVisionMatOfP);

            //get oInfo
            VisionRect oVisionRect = oVisionMatOfP.rectOuter();
            VisionRotateRect oVisionRotateRect = oVisionMatOfP.rectInner();
            VisionMatOfPInfo oInfo = oVisionMatOfP.getInfo(oVisionRect, oVisionRotateRect,
                    oVisionMatOfP, iIndex);

            //filter
            if (oInfo.getContoursArea() >= oInMo.getAreaMin()
                    && oInfo.getContoursArea() <= oInMo.getAreaMax()) {
                //drawContours
                Imgproc.drawContours(oOutMo.getDst().getMat(),
                        oVisionMatOfPSet.getVisionMatOfPListOrig(), iIndex,
                        new Scalar(0, 0, 255), 1);
                oOutMo.getContourInfoLst().add(oInfo);
                VisionCircle.drawCircle(oOutMo.getDst(),
                        oInfo.getContourCenterX(), oInfo.getContourCenterY(), 1,
                        new Scalar(0, 255, 0),
                        2, 8, 0);
                if (oInMo.isDebug()) {
                    oOutMo.getDst().save(oInMo.getDirPath4Debug() +
                            "debug_" + iIndex + ".bmp");
                }
            }
        }

        return oOutMo;
    }

    //#endregion

    //#region contoursCalc1

    public ContoursCalcOutMo contoursCalc1(ContoursCalcInMo oInMo) {
        ContoursCalcOutMo oOutMo = contoursCalc(oInMo, new AbstractContoursCalc() {
            @Override
            public VisionMat prepareEx(ContoursCalcInMo oInMo) {
                //threshold
                VisionMat oGray = VisionTools.imgOP.gray(oInMo.getSrc());
                VisionMat oBinary = new VisionMat();
                Imgproc.threshold(oGray.getMat(), oBinary.getMat(),
                        0, 255,
                        Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
                if (oInMo.isDebug()) {
                    oGray.save(oInMo.getDirPath4Debug() + "debug_gray.bmp");
                    oBinary.save(oInMo.getDirPath4Debug() + "debug_bin.bmp");
                }

                //destroyBatch
                VisionMat.destroyBatch(Arrays.asList(oGray));
                return oBinary;
            }
        });

        return oOutMo;
    }

    //#endregion

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
        VisionMat oGray = VisionTools.imgOP.gray(oSrc);

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oSrc.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oGray.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram.getMat(), oHistogram.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //get histogram data
        float[] arrHistogramData = oHistogram.dataOpBySub(0, 0, 256);

        //draw histogram
        VisionMat oDst = VisionMat.initByCreate(400, 400, oSrc.getMat().type(),
                new Scalar(200, 200, 200));
        int iOffsetX = 50;
        int iOffsetY = 350;
        VisionLine.drawLine(oDst, iOffsetX, 0, iOffsetX, iOffsetY, new Scalar(0, 0, 0));
        VisionLine.drawLine(oDst, iOffsetX, iOffsetY, 400, iOffsetY, new Scalar(0, 0, 0));
        int iHeight = oHistogram.getMat().rows();
        for (int i = 0; i < iHeight - 1; i++) {
            int y1 = (int) arrHistogramData[i];
            //int y2 = (int) arrHistogramData[i + 1];
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
        VisionMat oGray = VisionTools.imgOP.gray(oSrc);

        //equalize histogram
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray.getMat(), oDst.getMat());

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray));

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
        VisionMat oGray = VisionTools.imgOP.gray(oSrc);

        //equalize histogram
        VisionMat oDst = new VisionMat();
        Imgproc.equalizeHist(oGray.getMat(), oDst.getMat());

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oSrc.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram1 = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oGray.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram1.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram1.getMat(), oHistogram1.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //calc histogram
        VisionMat oHistogram2 = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oDst.getMat()), new MatOfInt(0), oMask.getMat(),
                oHistogram2.getMat(), new MatOfInt(256), new MatOfFloat(0, 255));
        Core.normalize(oHistogram2.getMat(), oHistogram2.getMat(), 0, 255,
                Core.NORM_MINMAX);

        //compare
        double[] arrDistance = new double[7];
        arrDistance[0] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CORREL);
        arrDistance[1] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CHISQR);
        arrDistance[2] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_INTERSECT);
        arrDistance[3] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_BHATTACHARYYA);
        arrDistance[4] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_HELLINGER);
        arrDistance[5] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_CHISQR_ALT);
        arrDistance[6] = Imgproc.compareHist(oHistogram1.getMat(), oHistogram2.getMat(),
                Imgproc.HISTCMP_KL_DIV);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray, oDst, oMask, oHistogram1, oHistogram2));

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
        VisionMat oHSVTemplate = VisionTools.imgOP.hsv(oTemplate);

        //calc histogram
        VisionMat oMask = VisionMat.initByOnes(oTemplate.getMat().size(), CvType.CV_8UC1);
        VisionMat oHistogram = new VisionMat();
        Imgproc.calcHist(Arrays.asList(oHSVTemplate.getMat()),
                new MatOfInt(0, 1), oMask.getMat(), oHistogram.getMat(),
                new MatOfInt(30, 32), new MatOfFloat(0, 179, 0, 255));

        //oHSVSrc
        VisionMat oHSVSrc = VisionTools.imgOP.hsv(oSrc);

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

    //#region template

    /**
     * match template
     *
     * @param oTemplate    VisionMat template
     * @param oSrc         VisionMat src
     * @param iMatchMethod 可选值:0~5(Imgproc.TM_*)
     * @return VisionMat dst
     */
    public VisionMat templateQuery(VisionMat oTemplate, VisionMat oSrc, int iMatchMethod) {
        //match template
        int iHeight = oSrc.getMat().rows() - oTemplate.getMat().rows() + 1;
        int iWidth = oSrc.getMat().cols() - oTemplate.getMat().cols() + 1;
        VisionMat oMatchRes = VisionMat.initByWH(iWidth, iHeight, CvType.CV_32FC1);
        Imgproc.matchTemplate(oSrc.getMat(), oTemplate.getMat(), oMatchRes.getMat(),
                iMatchMethod);
        Core.MinMaxLocResult oMinMaxLocResult = Core.minMaxLoc(oMatchRes.getMat());
        Point oMaxPoint = oMinMaxLocResult.maxLoc;
        Point oMinPoint = oMinMaxLocResult.minLoc;

        //analyse oMatchRes
        Point oMatchPoint;
        if (iMatchMethod == Imgproc.TM_SQDIFF || iMatchMethod == Imgproc.TM_SQDIFF_NORMED) {
            oMatchPoint = oMinPoint;
        } else {
            oMatchPoint = oMaxPoint;
        }

        //gen oDst
        VisionMat oDst = VisionMat.initByCopy(oSrc);
        VisionRect.drawRect(oDst, oMatchPoint,
                new Point(oMatchPoint.x + oTemplate.getMat().cols(),
                        oMatchPoint.y + oTemplate.getMat().rows()),
                new Scalar(0, 0, 255), 2, 8, 0);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oTemplate, oSrc, oMatchRes));

        //return
        return oDst;
    }

    //#endregion
}
