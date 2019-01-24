package com.firelord.opencv.img;

import com.firelord.opencv.VisionTools;
import com.firelord.opencv.img.mo.ContoursCalcInMo;
import com.firelord.opencv.img.mo.ContoursCalcOutMo;
import com.firelord.opencv.img.mo.VisionMatOfPSet;
import com.firelord.opencv.mat.VisionMat;

import java.util.Arrays;

/**
 * AbstractContoursCalc
 */
public abstract class AbstractContoursCalc implements IContoursCalc {
    //#region prepare

    @Override
    public VisionMat prepare(ContoursCalcInMo oInMo) {
        VisionMat oPrepare = prepareEx(oInMo);
        return oPrepare;
    }

    public abstract VisionMat prepareEx(ContoursCalcInMo oInMo);

    //#endregion

    //#region calc

    @Override
    public VisionMatOfPSet calc(VisionMat oPrepare, ContoursCalcInMo oInMo) {
        //findContours
        VisionMatOfPSet oVisionMatOfPSet = VisionTools.imgBasicFeature.contoursCalc4Calc(oPrepare,
                oInMo);

        //destroyBatch
        VisionMat.destroyBatch(Arrays.asList(oPrepare));

        return oVisionMatOfPSet;
    }

    //#endregion

    //#region measure

    @Override
    public ContoursCalcOutMo measure(VisionMatOfPSet oVisionMatOfPSet, ContoursCalcInMo oInMo) {
        ContoursCalcOutMo oOutMo = VisionTools.imgBasicFeature.contoursCalc4Measure(
                oVisionMatOfPSet, oInMo);
        return oOutMo;
    }

    //#endregion
}
