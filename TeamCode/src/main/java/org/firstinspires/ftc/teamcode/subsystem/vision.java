package org.firstinspires.ftc.teamcode.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetection;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionBlue;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionRed;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class vision {
    private AprilTagDetection tagOfInterest;
    private final OpenCvCamera camera;
    private final ColorDetection colorDetection;
    private final OpMode opMode;
    private static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    private static final double fx = 578.272;
    private static final double fy = 578.272;
    private static final double cx = 402.145;
    private static final double cy = 221.506;

    // UNITS ARE METERS
    private static final double tagSize = 0.166;

    // Tag ID 1.2.3 from the 36h11 family
    private static final int LEFT_BLUE = 1;
    private static final int MIDDLE_BLUE = 2;
    private static final int RIGHT_BLUE = 3;
    private static final int LEFT_RED = 4;
    private static final int MIDDLE_RED = 5;
    private static final int RIGHT_RED = 6;
    private boolean tagFound = false;

    public enum AllianceColor {
        RED,
        BLUE
    }

    public vision(OpMode opMode, AllianceColor allianceColor) {
        this.opMode = opMode;

        // Obtain camera id to allow for camera preview
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());

        // Obtain webcam name
        camera = OpenCvCameraFactory.getInstance().createWebcam(opMode.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagSize, fx, fy, cx, cy);

        if (allianceColor == AllianceColor.RED) {
            colorDetection = new ColorDetectionRed();
        } else {
            colorDetection = new ColorDetectionBlue();
        }
        camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.NATIVE_VIEW);
        // Initialize OpenCvWebcam with live preview
        camera.setPipeline(colorDetection);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                opMode.telemetry.addData("OpenCV ran into an error", errorCode);
            }
        });

    }
}
