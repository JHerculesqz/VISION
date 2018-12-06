package com.firelord.opencv.test;

import com.firelord.javafx.JavaFXUtils;
import com.firelord.opencv.VisionTools;
import com.firelord.opencv.matrix.VisionMat;
import com.firelord.opencv.video.VisionVideo4PCCallback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MainForm {
    //#region Fields

    @FXML
    private Button btn;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageViewHistogram;

    //#endregion

    //#region inner

    //#region btn

    public void onBtnClick(ActionEvent actionEvent) {
        VisionTools.video4PC.toggole(new VisionVideo4PCCallback() {
            @Override
            public void whenOpenSuccess() {
                btn.setText("Stop");
            }

            @Override
            public void whenGrabImgFrame() {
                VisionMat oSrc = VisionTools.video4PC.grabImgFrame();
                //#region contourseCalc
                VisionMat oDst = VisionTools.imgBasicFeature.contoursCalc(oSrc);
                //#endregion
                //#region histogram
//                VisionMat oDst = new VisionMat(oSrc, VisionMatrixInit.COPY);
//                VisionMat oDst1 = VisionTools.imgBasicFeature.histogramDisplay(oSrc);
//                JavaFXUtils.imageView.updateImageView(oDst1.toBufferImg(), imageViewHistogram);
//                VisionMat oDst2 = VisionTools.imgBasicFeature.histogramEqualize(oSrc);
//                JavaFXUtils.imageView.updateImageView(oDst2.toBufferImg(), imageViewHistogram);
//                double[] arrDistance = VisionTools.imgBasicFeature.histogramCompare(oSrc);
//                for (int i = 0; i < arrDistance.length; i++) {
//                    System.out.println("[distanceType]" + i + ",[distance]" + arrDistance[i]);
//                }
//                VisionMat oTemp3 = new VisionMat("C:/test/circle.bmp");
//                VisionMat oDst3 = VisionTools.imgBasicFeature.histogramBackProject(oSrc, oTemp3);
//                JavaFXUtils.imageView.updateImageView(oDst3.toBufferImg(), imageViewHistogram);

                //#endregion
                //#region templateQuery
//                VisionMat oTemp = new VisionMat("C:/test/circle.bmp");
//                VisionMat oDst = VisionTools.imgBasicFeature.templateQuery(oTemp, oSrc,
//                        5);
                //#endregion
                JavaFXUtils.imageView.updateImageView(oDst.toBufferImg(), imageView);
            }

            @Override
            public void whenOpenFail() {
                btn.setText("Start");
            }
        });
    }

    //#endregion

    //#endregion

    //#region callback

    //#endregion

    //#region 3rd

    //#endregion
}
