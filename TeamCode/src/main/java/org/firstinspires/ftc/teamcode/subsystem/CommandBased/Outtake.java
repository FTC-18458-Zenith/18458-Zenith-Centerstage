package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SensorDistance;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Outtake extends SubsystemBase {

    public static boolean REVERSED = true;

    public static double holdingPose = 0.8,
                                scorePose = 0.7;
    Telemetry telemetry;
    private final ServoEx clampServo;
    private SensorDistance sensorDistance;
    DistanceSensor distanceSensor;

    public Outtake(final HardwareMap hMap, Telemetry telemetry) {
        this.clampServo = new SimpleServo(hMap, "Outtake", 0, 360);

        clampServo.setInverted(REVERSED);

        clampServo.setPosition(scorePose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Outtake", clampServo.getPosition());
        distanceSensor.periodic();
    }

    public void score() {
        clampServo.setPosition(scorePose);
    }

    public void reset() {
        clampServo.setPosition(holdingPose);
    }
    public void sensor() {
        if (distanceSensor.frontPixelDetection() && distanceSensor.frontPixelDetection()) {
            clampServo.setPosition(holdingPose);
        }
        else clampServo.setPosition(scorePose);
    }
}
