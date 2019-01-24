package com.firelord.opencv.img;

import com.firelord.opencv.img.mo.ContoursCalcInMo;
import com.firelord.opencv.img.mo.ContoursCalcOutMo;
import com.firelord.opencv.img.mo.VisionMatOfPSet;
import com.firelord.opencv.mat.VisionMat;

/**
 * IContoursCalc
 */
public interface IContoursCalc {
    VisionMat prepare(ContoursCalcInMo oInMo);

    VisionMatOfPSet calc(VisionMat oPrepare, ContoursCalcInMo oInMo);

    ContoursCalcOutMo measure(VisionMatOfPSet oVisionMatOfPSet, ContoursCalcInMo oInMo);
}
