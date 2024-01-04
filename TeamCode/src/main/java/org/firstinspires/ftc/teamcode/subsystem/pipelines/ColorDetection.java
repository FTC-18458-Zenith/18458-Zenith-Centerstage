package org.firstinspires.ftc.teamcode.subsystem.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class ColorDetection extends OpenCvPipeline {
    private String location = "your mother has been found";
    private Mat hsvMat = new Mat();
    private final Mat binaryMat = new Mat();
    public final Point topLeft1 = new Point(10, 0);
    public final Point bottomRight1 = new Point(40, 20);
    public final Point topLeft2 = new Point(10, 0);
    public final Point bottomRight2 = new Point(40, 20);

    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

        Core.inRange(hsvMat, lower, upper, binaryMat); // thresholds image, and places results
        location = "I am locating your mother";

        double white1 = 0, white2 = 0;

        for (int i = (int) topLeft1.x; i <= bottomRight1.x; i++) {
            for (int j = (int) topLeft1.y; j <= bottomRight1.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    white1++;
                }
            }
        }

        for (int i = (int) topLeft2.x; i <= bottomRight2.x; i++) {
            for (int j = (int) topLeft2.y; j <= bottomRight2.y; j++) {
                if (binaryMat.get(i, j)[0] == 255) {
                    white2++;
                }
            }
        }

        if (white1 > white2) {
            location = "1";
        } else if (white1 < white2) {
            location = "2";
        }
        return binaryMat;
    }
    public String getLocation() {
        return location;
    }
}
