package com.firelord.opencv.img;

import com.firelord.opencv.mat.VisionMat;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

/**
 * ImgOP
 */
public class ImgOP {
    //#region blur

    /**
     * blur
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blur(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();
        Imgproc.blur(oSrc.getMat(), oDst.getMat(), new Size(5, 5),
                new Point(-1, -1), Core.BORDER_DEFAULT);
        return oDst;
    }

    /**
     * blurByGaussian
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blurByGaussian(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();
        Imgproc.GaussianBlur(oSrc.getMat(), oDst.getMat(), new Size(0, 0),
                15);
        return oDst;
    }

    /**
     * blurByMedian
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blurByMedian(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();
        Imgproc.medianBlur(oSrc.getMat(), oDst.getMat(), 5);
        return oDst;
    }

    /**
     * blurByErode
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blurByErode(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        Mat oKernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(3, 3));
        Imgproc.erode(oSrc.getMat(), oDst.getMat(), oKernel);
        oKernel.release();

        return oDst;
    }

    /**
     * blurByBilateral
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blurByBilateral(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();
        Imgproc.bilateralFilter(oSrc.getMat(), oDst.getMat(), 0,
                150, 15);
        return oDst;
    }

    /**
     * blurByPyr
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat blurByPyr(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();
        Imgproc.pyrMeanShiftFiltering(oSrc.getMat(), oDst.getMat(), 10, 50);
        return oDst;
    }

    /**
     * blurByCustom
     *
     * @param oSrc  src vision mat
     * @param iType type(1~3)
     * @return dst vision mat
     */
    public VisionMat blurByCustom(VisionMat oSrc, int iType) {
        VisionMat oDst = new VisionMat();

        //blur1
        if (1 == iType) {
            Mat k = new Mat(3, 3, CvType.CV_32FC1);
            float[] data = new float[]{1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                    1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
                    1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f};
            k.put(0, 0, data);
            Imgproc.filter2D(oSrc.getMat(), oDst.getMat(), -1, k);
            k.release();
        }
        //blur2
        else if (2 == iType) {
            Mat k = new Mat(3, 3, CvType.CV_32FC1);
            float[] data = new float[]{0, 1.0f / 8.0f, 0,
                    1.0f / 8.0f, 0.5f, 1.0f / 8.0f,
                    0, 1.0f / 8.0f, 0};
            k.put(0, 0, data);
            Imgproc.filter2D(oSrc.getMat(), oDst.getMat(), -1, k);
            k.release();
        }
        //blur3
        else if (3 == iType) {
            Mat kx = new Mat(3, 3, CvType.CV_32FC1);
            Mat ky = new Mat(3, 3, CvType.CV_32FC1);

            float[] robert_x = new float[]{-1, 0, 0, 1};
            kx.put(0, 0, robert_x);

            float[] robert_y = new float[]{0, 1, -1, 0};
            ky.put(0, 0, robert_y);

            Imgproc.filter2D(oSrc.getMat(), oDst.getMat(), -1, kx);
            Imgproc.filter2D(oSrc.getMat(), oDst.getMat(), -1, ky);
            kx.release();
            ky.release();
        }

        return oDst;
    }

    //#endregion

    //#region morphology

    /**
     * morphology
     *
     * @param oSrc   src vision mat
     * @param option 0~6
     * @return dst vision mat
     */
    public VisionMat morphology(VisionMat oSrc, int option) {
        VisionMat oDst = new VisionMat();

        //gen k
        Mat k = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(15, 15), new Point(-1, -1));

        //morphology
        switch (option) {
            //dilate
            case 0:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_DILATE, k);
                break;
            //erode
            case 1:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_ERODE, k);
                break;
            //open
            case 2:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_OPEN, k);
                break;
            //close
            case 3:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_CLOSE, k);
                break;
            //blackHat
            case 4:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_BLACKHAT, k);
                break;
            //topHat
            case 5:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_TOPHAT, k);
                break;
            //gradient
            case 6:
                Imgproc.morphologyEx(oSrc.getMat(), oDst.getMat(), Imgproc.MORPH_GRADIENT, k);
                break;
            default:
                break;
        }

        return oDst;
    }

    //#endregion

    //#region threshold

    /**
     * threshold
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat threshold(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        //gray
        Mat oGray = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oGray, Imgproc.COLOR_BGR2GRAY);
        //threshold
        Imgproc.threshold(oGray, oDst.getMat(), 127, 255,
                Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
//        Imgproc.threshold(oGray, oDst.getMat(), 127, 255,
//                Imgproc.THRESH_BINARY | Imgproc.THRESH_TRIANGLE);
        oGray.release();

        return oDst;
    }

    /**
     * thresholdByAdp
     *
     * @param oSrc src vision mat
     * @return dst vision mat
     */
    public VisionMat thresholdByAdp(VisionMat oSrc) {
        VisionMat oDst = new VisionMat();

        //gray
        Mat oGray = new Mat();
        Imgproc.cvtColor(oSrc.getMat(), oGray, Imgproc.COLOR_BGR2GRAY);
        //threshold
        Imgproc.adaptiveThreshold(oGray, oDst.getMat(), 255,
                Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY,
                15, 10);
        oGray.release();

        return oDst;
    }

    /**
     * Image graying
     *
     * @return Image graying VisionMat
     */
    public VisionMat gray(VisionMat oSrc) {
        VisionMat oGray = new VisionMat();

        Imgproc.cvtColor(oSrc.getMat(), oGray.getMat(), Imgproc.COLOR_BGR2GRAY);

        return oGray;
    }

    /**
     * Image HSV
     *
     * @return Image HSV
     */
    public VisionMat hsv(VisionMat oSrc) {
        VisionMat oHSV = new VisionMat();
        Imgproc.cvtColor(oSrc.getMat(), oHSV.getMat(), Imgproc.COLOR_BGR2HSV);
        return oHSV;
    }

    //#endregion
}
