package com.firelord.opencv.img.mo;

import com.firelord.opencv.mat.VisionMat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * ContoursCalcOutMo
 */
@ToString
public class ContoursCalcOutMo {
    //#region Fields

    /**
     * dst vision mat
     */
    @Setter
    @Getter
    private VisionMat dst;

    /**
     * contour info list
     */
    @Setter
    @Getter
    private List<VisionMatOfPInfo> contourInfoLst = new ArrayList<>();

    //#endregion
}
