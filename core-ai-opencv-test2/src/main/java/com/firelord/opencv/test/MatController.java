package com.firelord.opencv.test;

import com.firelord.opencv.mat.VisionMat;
import com.firelord.opencv.mat.mo.DataOpByBinSegmentOutMo;
import com.firelord.opencv.test.vo.mat.dataOpByAdd.DataOpByAddInVo;
import com.firelord.opencv.test.vo.mat.dataOpByAddWeight.DataOpByAddWeightInVo;
import com.firelord.opencv.test.vo.mat.dataOpByAnd.DataOpByAndInVo;
import com.firelord.opencv.test.vo.mat.dataOpByBinSegment.DataOpByBinSegmentInVo;
import com.firelord.opencv.test.vo.mat.dataOpByBinSegment.DataOpByBinSegmentOutVo;
import com.firelord.opencv.test.vo.mat.dataOpByBright.DataOpByBrightInVo;
import com.firelord.opencv.test.vo.mat.dataOpByContrast.DataOpByContrastInVo;
import com.firelord.opencv.test.vo.mat.dataOpByDivide.DataOpByDivideInVo;
import com.firelord.opencv.test.vo.mat.dataOpByMerge.DataOpByMergeInVo;
import com.firelord.opencv.test.vo.mat.dataOpByMultiply.DataOpByMultiplyInVo;
import com.firelord.opencv.test.vo.mat.dataOpByNormalAndAbs.DataOpByNormalAndAbs;
import com.firelord.opencv.test.vo.mat.dataOpByNot.DataOpByNotInVo;
import com.firelord.opencv.test.vo.mat.dataOpByOr.DataOpByOrInVo;
import com.firelord.opencv.test.vo.mat.dataOpBySplit.DataOpBySplitInVo;
import com.firelord.opencv.test.vo.mat.dataOpBySubtract.DataOpBySubtractInVo;
import com.firelord.spring.component.tmp.http.vo.ReqVo;
import com.firelord.spring.component.tmp.http.vo.RespVo;
import org.opencv.core.CvType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DemoController
 */
@Controller
@RequestMapping("/mat")
public class MatController {
    //#region dataOp

    @RequestMapping("/dataOpBySplit")
    @ResponseBody
    public RespVo dataOpBySplit(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpBySplitInVo oInVo = oReqVo.getReqBuVo(DataOpBySplitInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        List<VisionMat> lstDst = VisionMat.dataOpBySplit(oSrc);
        for (int i = 0; i < lstDst.size(); i++) {
            VisionMat oDst = lstDst.get(i);
            oDst.save(oInVo.getFilePathDstPrefix() + "_" + i + ".bmp");
        }
        VisionMat.destroyBatch(Arrays.asList(oSrc));
        VisionMat.destroyBatch(lstDst);

        return oRespVo;
    }

    @RequestMapping("/dataOpByMerge")
    @ResponseBody
    public RespVo dataOpByMerge(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByMergeInVo oInVo = oReqVo.getReqBuVo(DataOpByMergeInVo.class);

        //Provider
        List<VisionMat> lstSrc = new ArrayList<>();
        for (String strFilePathSrc : oInVo.getFilePathSrcLst()) {
            VisionMat oSrc = VisionMat.initByFilePath(strFilePathSrc);
            lstSrc.add(oSrc);
        }
        VisionMat oDst = VisionMat.dataOpByMerge(lstSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(lstSrc);
        VisionMat.destroyBatch(Arrays.asList(oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByBinSegment")
    @ResponseBody
    public RespVo dataOpByBinSegment(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByBinSegmentInVo oInVo = oReqVo.getReqBuVo(DataOpByBinSegmentInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        DataOpByBinSegmentOutMo oOutMo = VisionMat.dataOpByBinSegment(oSrc);
        oOutMo.getDst().save(oInVo.getFilePathDst());
        DataOpByBinSegmentOutVo oOutVo = new DataOpByBinSegmentOutVo();
        oOutVo.setMean(oOutMo.getMean());
        oOutVo.setStandardDeviation(oOutMo.getStandardDeviation());
        oRespVo.setResultObj(oOutVo);
        VisionMat.destroyBatch(Arrays.asList(oSrc, oOutMo.getDst()));

        return oRespVo;
    }

    @RequestMapping("/dataOpByAdd")
    @ResponseBody
    public RespVo dataOpByAdd(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByAddInVo oInVo = oReqVo.getReqBuVo(DataOpByAddInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpByAdd(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpBySubtract")
    @ResponseBody
    public RespVo dataOpBySubtract(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpBySubtractInVo oInVo = oReqVo.getReqBuVo(DataOpBySubtractInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpBySubtract(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByDivide")
    @ResponseBody
    public RespVo dataOpByDivide(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByDivideInVo oInVo = oReqVo.getReqBuVo(DataOpByDivideInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpByDivide(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByMultiply")
    @ResponseBody
    public RespVo dataOpByMultiply(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByMultiplyInVo oInVo = oReqVo.getReqBuVo(DataOpByMultiplyInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpByMultiply(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByBright")
    @ResponseBody
    public RespVo dataOpByBright(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByBrightInVo oInVo = oReqVo.getReqBuVo(DataOpByBrightInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionMat.dataOpByBright(oSrc, oInVo.getV());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByContrast")
    @ResponseBody
    public RespVo dataOpByContrast(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByContrastInVo oInVo = oReqVo.getReqBuVo(DataOpByContrastInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionMat.dataOpByContrast(oSrc, oInVo.getV());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByAddWeight")
    @ResponseBody
    public RespVo dataOpByAddWeight(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByAddWeightInVo oInVo = oReqVo.getReqBuVo(DataOpByAddWeightInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionMat.dataOpByAddWeight(oSrc, oInVo.getAlpha(), oInVo.getGamma());
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByAnd")
    @ResponseBody
    public RespVo dataOpByAnd(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByAndInVo oInVo = oReqVo.getReqBuVo(DataOpByAndInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpByAnd(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByOr")
    @ResponseBody
    public RespVo dataOpByOr(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByOrInVo oInVo = oReqVo.getReqBuVo(DataOpByOrInVo.class);

        //Provider
        VisionMat oSrc1 = VisionMat.initByFilePath(oInVo.getFilePathSrc1());
        VisionMat oSrc2 = VisionMat.initByFilePath(oInVo.getFilePathSrc2());
        VisionMat oDst = VisionMat.dataOpByOr(oSrc1, oSrc2);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc1, oSrc2, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByNot")
    @ResponseBody
    public RespVo dataOpByNot(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByNotInVo oInVo = oReqVo.getReqBuVo(DataOpByNotInVo.class);

        //Provider
        VisionMat oSrc = VisionMat.initByFilePath(oInVo.getFilePathSrc());
        VisionMat oDst = VisionMat.dataOpByNot(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    @RequestMapping("/dataOpByNormalAndAbs")
    @ResponseBody
    public RespVo dataOpByNormalAndAbs(@RequestBody ReqVo oReqVo) {
        RespVo oRespVo = new RespVo();

        //InVo
        DataOpByNormalAndAbs oInVo = oReqVo.getReqBuVo(DataOpByNormalAndAbs.class);

        //Provider
        VisionMat oSrc = VisionMat.initByZeros(400, 400, CvType.CV_32FC3);
        VisionMat oDst = VisionMat.dataOpByNormalAndAbs(oSrc);
        oDst.save(oInVo.getFilePathDst());
        VisionMat.destroyBatch(Arrays.asList(oSrc, oDst));

        return oRespVo;
    }

    //#endregion
}
