package com.firelord.opencv.img.mo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.opencv.core.MatOfPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * VisionMatOfPSet
 */
@ToString
public class VisionMatOfPSet {
    //#region Fields

    /**
     * VisionMatOfP list
     */
    @Setter
    @Getter
    private List<VisionMatOfP> visionMatOfPList = new ArrayList<>();

    //#endregion

    //#region Construction

    /**
     * VisionMatOfPSet Construction
     *
     * @param lstMatOfPoint List<MatOfPoint>
     */
    public VisionMatOfPSet(List<MatOfPoint> lstMatOfPoint) {
        for (MatOfPoint oMatOfPoint : lstMatOfPoint) {
            VisionMatOfP oVisionMatOfP = VisionMatOfP.builder()
                    .matOfPoint(oMatOfPoint)
                    .build();
            this.getVisionMatOfPList().add(oVisionMatOfP);
        }
    }

    //#endregion

    //#region getVisionMatOfPListOrig

    /**
     * get MatOfPoint list
     *
     * @return List<MatOfPoint>
     */
    public List<MatOfPoint> getVisionMatOfPListOrig() {
        List<MatOfPoint> lstRes = new ArrayList<>();

        for (VisionMatOfP oVisionMatOfP : this.visionMatOfPList) {
            lstRes.add(oVisionMatOfP.getMatOfPoint());
        }

        return lstRes;
    }

    //#endregion
}
