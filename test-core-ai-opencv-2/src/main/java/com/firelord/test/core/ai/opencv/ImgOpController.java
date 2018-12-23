package com.firelord.test.core.ai.opencv;

import com.firelord.opencv.VisionTools;
import com.firelord.opencv.mat.VisionMat;
import com.firelord.test.core.ai.opencv.vo.imgop.blur.BlurInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_bilateral.BlurByBilateralInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_custom.BlurByCustomInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_erode.BlurByErodeInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_gaussian.BlurByGaussianInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_median.BlurByMedianInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.blur_by_pyr.BlurByPyrInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.morphology.MorphologyInVo;
import com.firelord.test.core.ai.opencv.vo.imgop.threshold.ThresholdInVo;
import com.firelord.spring.component.rpc.http.vo.ReqVo;
import com.firelord.spring.component.rpc.http.vo.RespVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * ImgOpController
 */
@Controller
@RequestMapping("/imgOp")
public class ImgOpController {
    //#region blur

    @RequestMapping("/blur")
    @ResponseBody
    public RespVo blur(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurInVo oInVo = oReqVo.getReqBuVo(BlurInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blur(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByGaussian")
    @ResponseBody
    public RespVo blurByGaussian(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByGaussianInVo oInVo = oReqVo.getReqBuVo(BlurByGaussianInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByGaussian(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByMedian")
    @ResponseBody
    public RespVo blurByMedian(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByMedianInVo oInVo = oReqVo.getReqBuVo(BlurByMedianInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByMedian(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByErode")
    @ResponseBody
    public RespVo blurByErode(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByErodeInVo oInVo = oReqVo.getReqBuVo(BlurByErodeInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByErode(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByBilateral")
    @ResponseBody
    public RespVo blurByBilateral(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByBilateralInVo oInVo = oReqVo.getReqBuVo(BlurByBilateralInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByBilateral(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByPyr")
    @ResponseBody
    public RespVo blurByPyr(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByPyrInVo oInVo = oReqVo.getReqBuVo(BlurByPyrInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByPyr(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/blurByCustom")
    @ResponseBody
    public RespVo blurByCustom(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        BlurByCustomInVo oInVo = oReqVo.getReqBuVo(BlurByCustomInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.blurByCustom(oSrc, oInVo.getType());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion

    //#region morphology

    @RequestMapping("/morphology")
    @ResponseBody
    public RespVo morphology(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        MorphologyInVo oInVo = oReqVo.getReqBuVo(MorphologyInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.morphology(oSrc, oInVo.getOption());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion

    //#region threshold

    @RequestMapping("/threshold")
    @ResponseBody
    public RespVo threshold(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ThresholdInVo oInVo = oReqVo.getReqBuVo(ThresholdInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.threshold(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/thresholdByAdp")
    @ResponseBody
    public RespVo thresholdByAdp(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ThresholdInVo oInVo = oReqVo.getReqBuVo(ThresholdInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.thresholdByAdp(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/gray")
    @ResponseBody
    public RespVo gray(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ThresholdInVo oInVo = oReqVo.getReqBuVo(ThresholdInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.gray(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/hsv")
    @ResponseBody
    public RespVo hsv(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ThresholdInVo oInVo = oReqVo.getReqBuVo(ThresholdInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgOP.hsv(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion
}
