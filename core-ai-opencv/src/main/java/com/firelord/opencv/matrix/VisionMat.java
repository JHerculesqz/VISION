package com.firelord.opencv.matrix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.List;

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

    //#endregion

    //#region ?bak

    public boolean isEmpty() {
        return this.getMat().empty();
    }

    //#endregion
}
