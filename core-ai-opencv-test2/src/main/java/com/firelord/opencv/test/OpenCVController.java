package com.firelord.opencv.test;

import com.firelord.opencv.VisionTools;
import com.firelord.opencv.matrix.VisionMat;
import com.firelord.opencv.test.vo.*;
import com.firelord.spring.component.tmp.http.vo.ReqVo;
import com.firelord.spring.component.tmp.http.vo.RespVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@RequestMapping("/opencv")
public class OpenCVController {
    //#region Fields

    //#endregion

    //#region test

    @RequestMapping("/contoursCalc")
    @ResponseBody
    public void contoursCalc() {
//        VisionMat oSrc = VisionTools.mat.initByFilePath("C:/test/contoursCalc.bmp");
//        VisionMat oDst = VisionTools.imgBasicFeature.contoursCalc(oSrc);
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

    //#region templateQuery

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
}
