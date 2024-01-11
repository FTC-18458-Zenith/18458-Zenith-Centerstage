package org.firstinspires.ftc.teamcode.subsystem.pipelines;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class ColorDetectionBlue extends ColorDetection {
    @Override
    public Scalar getPrimaryScalar() {
        return new Scalar(0, 0, 255);
    }

    @Override
    public Scalar getSecondaryScalar() {
        return new Scalar(0, 255, 0);
    }
}
