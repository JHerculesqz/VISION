package com.firelord.opencv.test.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * TemplateQueryInVo
 */
@ToString
public class TemplateQueryInVo {
    //#region Fields

    /**
     * src file path
     */
    @Setter
    @Getter
    private String filePathSrc;

    /**
     * template file path
     */
    @Setter
    @Getter
    private String filePathTemplate;

    /**
     * match method type
     */
    @Setter
    @Getter
    private int matchType;

    /**
     * dst file path
     */
    @Setter
    @Getter
    private String filePathDst;

    //#endregion
}
