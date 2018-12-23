package com.firelord.test.core.ai.opencv.vo.mat.dataOpByMerge;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * DataOpByMergeInVo
 */
@ToString
public class DataOpByMergeInVo {
    //#region Fields

    /**
     * src file path list
     */
    @Setter
    @Getter
    private List<String> filePathSrcLst;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;


    //#endregion
}
