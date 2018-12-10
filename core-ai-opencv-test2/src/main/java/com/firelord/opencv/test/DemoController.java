package com.firelord.opencv.test;

import com.firelord.opencv.VisionTools;
import com.firelord.opencv.img.mo.ContoursCalcInMo;
import com.firelord.opencv.img.mo.ContoursCalcOutMo;
import com.firelord.opencv.matrix.VisionMat;
import com.firelord.opencv.test.vo.canny.CannyEdgeQuery2InVo;
import com.firelord.opencv.test.vo.canny.CannyEdgeQueryInVo;
import com.firelord.opencv.test.vo.contours.ContoursCalcInVo;
import com.firelord.opencv.test.vo.histogram.HistogramBackProjectInVo;
import com.firelord.opencv.test.vo.histogram.HistogramCompareInVo;
import com.firelord.opencv.test.vo.histogram.HistogramDisplayInVo;
import com.firelord.opencv.test.vo.histogram.HistogramEqualizeInVo;
import com.firelord.opencv.test.vo.hough.HoughCircleQueryInVo;
import com.firelord.opencv.test.vo.hough.HoughLineQueryInVo;
import com.firelord.opencv.test.vo.sobel.LaplianInVo;
import com.firelord.opencv.test.vo.sobel.ScharrInVo;
import com.firelord.opencv.test.vo.sobel.SobelInVo;
import com.firelord.opencv.test.vo.template.TemplateQueryInVo;
import com.firelord.spring.component.tmp.http.vo.ReqVo;
import com.firelord.spring.component.tmp.http.vo.RespVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * DemoController
 */
@Controller
@RequestMapping("/opencv")
public class DemoController {
    //#region image basic feature

    //#region sobel

    @RequestMapping("/sobel")
    @ResponseBody
    public RespVo sobel(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        SobelInVo oInVo = oReqVo.getReqBuVo(SobelInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.sobel(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/scharr")
    @ResponseBody
    public RespVo scharr(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ScharrInVo oInVo = oReqVo.getReqBuVo(ScharrInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.scharr(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/laplian")
    @ResponseBody
    public RespVo laplian(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        LaplianInVo oInVo = oReqVo.getReqBuVo(LaplianInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.laplian(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion

    //#region canny

    @RequestMapping("/cannyEdgeQuery")
    @ResponseBody
    public RespVo cannyEdgeQuery(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        CannyEdgeQueryInVo oInVo = oReqVo.getReqBuVo(CannyEdgeQueryInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.cannyEdgeQuery(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/cannyEdgeQuery2")
    @ResponseBody
    public RespVo cannyEdgeQuery2(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        CannyEdgeQuery2InVo oInVo = oReqVo.getReqBuVo(CannyEdgeQuery2InVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.cannyEdgeQuery2(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion

    //#region hough

    @RequestMapping("/houghLineQuery")
    @ResponseBody
    public RespVo houghLineQuery(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HoughLineQueryInVo oInVo = oReqVo.getReqBuVo(HoughLineQueryInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.houghLineQuery(oSrc, oInVo.getThreshold());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/houghLinePQuery")
    @ResponseBody
    public RespVo houghLinePQuery(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HoughLineQueryInVo oInVo = oReqVo.getReqBuVo(HoughLineQueryInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.houghLinePQuery(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/houghCircleQuery")
    @ResponseBody
    public RespVo houghCircleQuery(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HoughCircleQueryInVo oInVo = oReqVo.getReqBuVo(HoughCircleQueryInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.houghCircleQuery(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion

    //#region contours

    @RequestMapping("/contoursCalc")
    @ResponseBody
    public RespVo contoursCalc(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        ContoursCalcInVo oInVo = oReqVo.getReqBuVo(ContoursCalcInVo.class);

        //Provider
        ContoursCalcInMo oInMo = ContoursCalcInMo.builder()
                .src(VisionMat.initByFilePath(oInVo.getFilePathSrc()))
                .filePath4Debug(oInVo.getFilePath4Debug())
                .areaMin(oInVo.getAreaMin())
                .areaMax(oInVo.getAreaMax())
                .build();
        ContoursCalcOutMo oOutMo = VisionTools.imgBasicFeature.contoursCalc(oInMo);
        oOutMo.getDst().save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oInMo.getSrc(), oOutMo.getDst()));

        oRespVo.setResultObj(oOutMo.getContourInfoLst());
        return oRespVo;
    }

    //#endregion

    //#region histogram

    @RequestMapping("/histogramDisplay")
    @ResponseBody
    public RespVo histogramDisplay(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HistogramDisplayInVo oInVo = oReqVo.getReqBuVo(HistogramDisplayInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.histogramDisplay(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/histogramEqualize")
    @ResponseBody
    public RespVo histogramEqualize(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HistogramEqualizeInVo oInVo = oReqVo.getReqBuVo(HistogramEqualizeInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionTools.imgBasicFeature.histogramEqualize(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/histogramCompare")
    @ResponseBody
    public RespVo histogramCompare(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HistogramCompareInVo oInVo = oReqVo.getReqBuVo(HistogramCompareInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        double[] arrDistance = VisionTools.imgBasicFeature.histogramCompare(oSrc);
        VisionMat.destroyBatch(Arrays.asList(oSrc));

        oRespVo.setResultObj(arrDistance);
        return oRespVo;
    }

    @RequestMapping("/histogramBackProject")
    @ResponseBody
    public RespVo histogramBackProject(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        HistogramBackProjectInVo oInVo = oReqVo.getReqBuVo(HistogramBackProjectInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oTemplate = VisionMat.initByFilePath(oInVo.getFilePathTemplate());
        VisionMat oDst = VisionTools.imgBasicFeature.histogramBackProject(oSrc, oTemplate);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oTemplate, oDst));

        return oRespVo;
    }

    //#endregion

    //#region template

    @RequestMapping("/templateQuery")
    @ResponseBody
    public RespVo templateQuery(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        TemplateQueryInVo oInVo = oReqVo.getReqBuVo(TemplateQueryInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oTemplate = VisionMat.initByFilePath(oInVo.getFilePathTemplate());
        VisionMat oDst = VisionTools.imgBasicFeature.templateQuery(oTemplate, oSrc,
                oInVo.getMatchType());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oTemplate, oDst));

        return oRespVo;
    }

    //#endregion

    //#endregion
}
