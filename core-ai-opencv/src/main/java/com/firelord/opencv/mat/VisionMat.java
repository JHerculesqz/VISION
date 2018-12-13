package com.firelord.opencv.mat;

import com.firelord.opencv.mat.mo.DataOpByBinSegmentOutMo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * VisionMat
 */
@ToString
public class VisionMat {
    //#region Fields

    /**
     * mat object
     */
    @Setter
    @Getter
    private Mat mat;

    //#endregion

    //#region Construction

    public VisionMat() {
        this.mat = new Mat();
    }

    //#endregion

    //#region init/destroy

    /**
     * Init VisionMat object by file path
     *
     * @param strFilePath file path
     * @return VisionMat object
     */
    public static VisionMat initByFilePath(String strFilePath) {
        VisionMat oDst = new VisionMat();

        Mat oMat = Imgcodecs.imread(strFilePath, Imgcodecs.IMREAD_COLOR);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * Init VisionMat object by eye
     *
     * @param oSrc VisionMat
     * @return VisionMat
     */
    public static VisionMat initByEye(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        Mat oMat = Mat.eye(oSrc.getMat().size(), oSrc.getMat().type());
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * Init VisionMat object by copy
     *
     * @param oSrc VisionMat
     * @return VisionMat
     */
    public static VisionMat initByCopy(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        Mat oMat = new Mat();
        oSrc.getMat().copyTo(oMat);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * Init VisionMat object by width and height
     *
     * @param iWidth  width
     * @param iHeight height
     * @param iCvType CvType
     * @return VisionMat
     */
    public static VisionMat initByWH(int iWidth, int iHeight, int iCvType) {
        VisionMat oDst = new VisionMat();

        Mat oMat = new Mat(iHeight, iWidth, iCvType);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * init by ones
     *
     * @param oSize   size
     * @param iCvType cvType
     * @return vision mat
     */
    public static VisionMat initByOnes(Size oSize, int iCvType) {
        VisionMat oDst = new VisionMat();

        Mat oMat = Mat.ones(oSize, iCvType);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * init by create
     *
     * @param iRows   width
     * @param iCols   height
     * @param iCvType cvType
     * @param oScalar scalar
     * @return vision mat
     */
    public static VisionMat initByCreate(int iRows, int iCols, int iCvType, Scalar oScalar) {
        VisionMat oDst = new VisionMat();

        Mat oMat = new Mat();
        oMat.create(iRows, iCols, iCvType);
        oMat.setTo(oScalar);

        return oDst;
    }

    /**
     * init by zeros
     *
     * @param oSize   size
     * @param iCvType cvType
     * @return vision mat
     */
    public static VisionMat initZeros(Size oSize, int iCvType) {
        VisionMat oDst = new VisionMat();

        Mat oMat = Mat.zeros(oSize, iCvType);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * initByZeros
     *
     * @param iRows   rows
     * @param iCols   cols
     * @param iCvType cvType
     * @return src vision mat
     */
    public static VisionMat initByZeros(int iRows, int iCols, int iCvType) {
        VisionMat oDst = new VisionMat();

        Mat oMat = Mat.zeros(iRows, iCols, iCvType);
        oDst.setMat(oMat);

        return oDst;
    }

    /**
     * destroy
     */
    public void destroy() {
        this.mat.release();
    }

    /**
     * batch destroyBatch vision mat
     *
     * @param lstVisionMat VisionMat list
     */
    public static void destroyBatch(List<VisionMat> lstVisionMat) {
        for (VisionMat oVisionMat : lstVisionMat) {
            oVisionMat.destroy();
        }
    }

    //#endregion

    //#region files

    /**
     * save mat to file
     *
     * @param strFilePath file path
     */
    public void save(String strFilePath) {
        Imgcodecs.imwrite(strFilePath, this.mat);
    }

    /**
     * to buffer image
     *
     * @return BufferedImage
     */
    public BufferedImage toBufferImg() {
        BufferedImage oBufferedImage;

        int iWidth = this.getMat().width();
        int iHeight = this.getMat().height();
        int iChannels = this.getMat().channels();
        byte[] arrSourcePixels = new byte[iWidth * iHeight * iChannels];
        this.getMat().get(0, 0, arrSourcePixels);
        if (this.getMat().channels() > 1) {
            oBufferedImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_3BYTE_BGR);
        } else {
            oBufferedImage = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_BYTE_GRAY);
        }
        final byte[] arrTargetPixels = ((DataBufferByte) oBufferedImage.getRaster().getDataBuffer()).getData();
        System.arraycopy(arrSourcePixels, 0, arrTargetPixels,
                0, arrSourcePixels.length);

        return oBufferedImage;
    }

    //#endregion

    //#region dataOp

    /**
     * get sub mat data
     *
     * @param iRow    start row index
     * @param iCol    start col index
     * @param iLength sub mat data length
     * @return sub mat data
     */
    public float[] dataOpBySub(int iRow, int iCol, int iLength) {
        float[] arrSubData = new float[iLength];
        this.mat.get(iRow, iCol, arrSubData);
        return arrSubData;
    }

    /**
     * dataOpBySplit
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public static List<VisionMat> dataOpBySplit(VisionMat oSrc) {
        List<VisionMat> lstDst = new ArrayList<>();

        //split
        List<Mat> lstDstOrig = new ArrayList<>();
        Core.split(oSrc.getMat(), lstDstOrig);

        //trans
        for (Mat oDstOrig : lstDstOrig) {
            VisionMat oDst = new VisionMat();
            oDst.setMat(oDstOrig);
            lstDst.add(oDst);
        }

        return lstDst;
    }

    /**
     * dataOpByMerge
     * TODO:refactor
     *
     * @param lstSrc src vision mat list
     * @return dst vision mat
     */
    public static VisionMat dataOpByMerge(List<VisionMat> lstSrc) {
        VisionMat oDst = new VisionMat();

        //gen lstSrcOrig
        List<Mat> lstSrcOrig = new ArrayList<>();
        for (VisionMat oSrc : lstSrc) {
            lstSrcOrig.add(oSrc.getMat());
        }

        //merge
        Core.merge(lstSrcOrig, oDst.getMat());

        return oDst;
    }

    /**
     * binary segmentation
     *
     * @param oSrc src vision mat list
     * @return DataOpByBinSegmentOutMo
     */
    public static DataOpByBinSegmentOutMo dataOpByBinSegment(VisionMat oSrc) {
        DataOpByBinSegmentOutMo oOutMo = new DataOpByBinSegmentOutMo();

        //gray
        VisionMat oGray = new VisionMat();
        Imgproc.cvtColor(oSrc.getMat(), oGray.getMat(), Imgproc.COLOR_BGR2GRAY);

        //mean/standard deviation
        MatOfDouble oMeans = new MatOfDouble();
        MatOfDouble oStdDev = new MatOfDouble();
        Core.meanStdDev(oGray.getMat(), oMeans, oStdDev);
        oOutMo.setMean(oMeans.toArray());
        oOutMo.setStandardDeviation(oStdDev.toArray());

        //binary segmentation
        int iWidth = oGray.getMat().cols();
        int iHeight = oGray.getMat().rows();
        byte[] arrData = new byte[iWidth * iHeight];
        oGray.getMat().get(0, 0, arrData);
        int pv;
        int t = (int) oMeans.toArray()[0];
        for (int i = 0; i < arrData.length; i++) {
            pv = arrData[i] & 0xff;
            if (pv > t) {
                arrData[i] = (byte) 255;
            } else {
                arrData[i] = (byte) 0;
            }
        }
        oGray.getMat().put(0, 0, arrData);
        VisionMat oDst = new VisionMat();
        Imgproc.cvtColor(oGray.getMat(), oDst.getMat(), Imgproc.COLOR_GRAY2RGBA);
        oOutMo.setDst(oDst);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oGray));

        return oOutMo;
    }

    /**
     * dataOpByAdd
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByAdd(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        //add
        Core.add(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpBySubtract
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpBySubtract(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        //add
        Core.subtract(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByMultiply
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByMultiply(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        //add
        Core.multiply(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByDivide
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByDivide(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        //add
        Core.divide(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByBright
     *
     * @param oSrc src vision mat
     * @param iV   scalar v
     * @return dst vision mat
     */
    public static VisionMat dataOpByBright(VisionMat oSrc, double iV) {
        VisionMat oDst = new VisionMat();

        //add
        Core.add(oSrc.getMat(), new Scalar(iV, iV, iV), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByContrast
     *
     * @param oSrc src vision mat
     * @param iV   scalar v
     * @return dst vision mat
     */
    public static VisionMat dataOpByContrast(VisionMat oSrc, double iV) {
        VisionMat oDst = new VisionMat();

        //add
        Core.multiply(oSrc.getMat(), new Scalar(iV, iV, iV), oDst.getMat());

        return oDst;
    }

    /**
     * addByWeight
     *
     * @param oSrc   src vision mat
     * @param iAlpha alpha
     * @param iGamma gamma
     * @return dst vision mat
     */
    public static VisionMat dataOpByAddWeight(VisionMat oSrc, double iAlpha, double iGamma) {
        VisionMat oDst = new VisionMat();

        //addWeight
        VisionMat oMask = VisionMat.initZeros(oSrc.getMat().size(), oSrc.getMat().type());
        Core.addWeighted(oSrc.getMat(), iAlpha, oMask.getMat(), 1.0 - iAlpha,
                iGamma, oDst.getMat());
        VisionMat.destroyBatch(Arrays.asList(oMask));

        return oDst;
    }

    /**
     * dataOpByAnd
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByAnd(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        Core.bitwise_and(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByOr
     *
     * @param oSrc1 src1 vision mat
     * @param oSrc2 src2 vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByOr(VisionMat oSrc1, VisionMat oSrc2) {
        VisionMat oDst = new VisionMat();

        Core.bitwise_or(oSrc1.getMat(), oSrc2.getMat(), oDst.getMat());

        return oDst;
    }

    /**
     * dataOpByNot
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public static VisionMat dataOpByNot(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        Core.bitwise_not(oSrc.getMat(), oDst.getMat());

        return oDst;
    }

    public static VisionMat dataOpByNormalAndAbs(VisionMat oSrc) {
        //gen arrData
        float[] arrData = new float[400 * 400 * 3];
        for (int i = 0; i < arrData.length; i++) {
            arrData[i] = (float) new Random().nextGaussian();
        }
        oSrc.getMat().put(0, 0, arrData);

        //normalize
        VisionMat oDst = new VisionMat();
        VisionMat oMask = new VisionMat();
        Core.normalize(oSrc.getMat(), oDst.getMat(), 0, 255,
                Core.NORM_MINMAX, -1, oMask.getMat());

        //covert
        VisionMat oDst8U = new VisionMat();
        oDst.getMat().convertTo(oDst8U.getMat(), CvType.CV_8UC3);
        VisionMat oResult = new VisionMat();
        Imgproc.cvtColor(oDst8U.getMat(), oResult.getMat(), Imgproc.COLOR_BGR2RGBA);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oDst, oMask, oDst8U));

        return oResult;
    }

    //#endregion

    //#region ?bak

    public boolean isEmpty() {
        return this.getMat().empty();
    }

    //#endregion
}
