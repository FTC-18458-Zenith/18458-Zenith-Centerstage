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
public abstract class ColorDetection extends OpenCvPipeline {
    public static int point1x = 100;
    public static int point1y = 0;

    public static int point2x = 400;
    public static int point2y = 80;

    public static int point3x = 700;
    public static int point3y = 200;

    public static int boxWidth = 300;
    public static int boxHeight = 510;

    private final Point region1 = new Point(point1x,point1y);
    private final Point region2 = new Point(point2x,point2y);
    private final Point region3 = new Point(point3x,point3y);
    public enum propLocation {
        LEFT,
        CENTER,
        RIGHT
    }
    public abstract Scalar getPrimaryScalar();
    public abstract Scalar getSecondaryScalar();
    Point REGION1 = region1;
    Point REGION2 = region2;
    Point REGION3 = region3;
    Point recatnagle1_pointA = new Point(
            REGION1.x,
            REGION1.y);
    Point rectangle1_pointB = new Point(
            REGION1.x + boxWidth,
            REGION1.y + boxHeight);
    Point recatnagle2_pointA = new Point(
            REGION2.x,
            REGION2.y);
    Point rectangle2_pointB = new Point(
            REGION2.x + boxWidth,
            REGION2.y + boxHeight);
    Point rectnagle3_pointA = new Point(
            REGION3.x,
            REGION3.y);
    Point rectangle3_pointB = new Point(
            REGION3.x + boxWidth,
            REGION3.y + boxHeight);
    Mat rectangle1, rectangle2, rectangle3;
     Mat hsvMat = new Mat();
     Mat binaryMat = new Mat();
     int avg1, avg2, avg3;
     private volatile propLocation location = propLocation.RIGHT;
    void converter(Mat input) {
         Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
         Core.inRange(hsvMat, getPrimaryScalar(), getSecondaryScalar(), binaryMat);
     }
    @Override
    public void init(Mat firstFrame) {

        converter(firstFrame);
        rectangle1 = hsvMat.submat(new Rect(recatnagle1_pointA, rectangle1_pointB));
        rectangle2 = hsvMat.submat(new Rect(recatnagle2_pointA, rectangle2_pointB));
        rectangle3 = hsvMat.submat(new Rect(rectnagle3_pointA, rectangle3_pointB));
    }
    @Override
    public Mat processFrame(Mat input) {
        converter(input);
        avg1 = -(int) Core.mean(rectangle1).val[0];
        avg2 = -(int) Core.mean(rectangle2).val[0];
        avg3 = -(int) Core.mean(rectangle3).val[0];

        Imgproc.rectangle(
                input,
                recatnagle1_pointA,
                rectangle1_pointB,
                getPrimaryScalar(),
                2);
        Imgproc.rectangle(
                input,
                recatnagle2_pointA,
                rectangle2_pointB,
                getPrimaryScalar(),
                2);
        Imgproc.rectangle(
                input,
                rectnagle3_pointA,
                rectangle3_pointB,
                getPrimaryScalar(),
                2);

        int maxOne2 = Math.max(avg1, avg2);
        int max = Math.max(maxOne2, avg3);
        if(max == avg1)
        {
            location = propLocation.LEFT;

            Imgproc.rectangle(
                    input,
                    recatnagle1_pointA,
                    rectangle1_pointB,
                    getSecondaryScalar(),
                    -1);
        }
        else if(max == avg2) // Was it from region 2?
        {
            location = propLocation.CENTER; // Record our analysis

            Imgproc.rectangle(
                    input, // Buffer to draw on
                    recatnagle2_pointA, // First point which defines the rectangle
                    rectangle2_pointB, // Second point which defines the rectangle
                    getSecondaryScalar(), // The color the rectangle is drawn in
                    -1); // Negative thickness means solid fill
        }
        else if(max == avg3) // Was it from region 3?
        {
            location = propLocation.RIGHT; // Record our analysis

            Imgproc.rectangle(
                    input,
                    rectnagle3_pointA,
                    rectangle3_pointB,
                    getSecondaryScalar(),
                    -1); // Negative thickness means solid fill
        }
        return input;
    }
    public propLocation getLocation() {
        return this.location;
    }
}
