package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystem.Vision;
import org.firstinspires.ftc.teamcode.subsystem.pipelines.ColorDetectionBlue;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp
public class CameraTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ColorDetectionBlue colorDetectionBlue = new ColorDetectionBlue();
        Vision vision = new Vision(this);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam 1");
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcam, cameraMonitorViewId);
        camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.SOFTWARE);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {

                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(colorDetectionBlue);
                telemetry.addData("I am informing you about how I'll find your mother", "give me attention");
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addData("Mother is being located", "Mother cannot be found, ERROR");

            }
        });
        waitForStart();
        while (opModeIsActive()) {
            vision.streaming();
            FtcDashboard.getInstance().startCameraStream(camera, 60);
        }
    }
}
