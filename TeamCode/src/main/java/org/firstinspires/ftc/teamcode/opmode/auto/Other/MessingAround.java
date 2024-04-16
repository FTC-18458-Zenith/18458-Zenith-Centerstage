package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;


public class MessingAround extends SubsystemBase {
    private final ColorRangeSensor leftSensor, rightSensor;

    public static Sensor sensor;
    public static Drive drive;
    public static IMU imu;
    public static class Sensor {
        public static double leftDistance = 0;
        public static double rightDistance = 0;
    }

    public MessingAround(HardwareMap hardwareMap) {
        this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "frontSensor");
        this.rightSensor = hardwareMap.get(ColorRangeSensor.class, "backSensor");

    }
    public void reAlignment() {
        double leftDistance = leftSensor.getDistance(DistanceUnit.INCH);
        double rightDistance = rightSensor.getDistance(DistanceUnit.INCH);

        if (leftDistance > rightDistance) {
            // make -1 tan
            double error = Math.atan(DriveConstants.TRACK_WIDTH / leftDistance);
            double correction = error - 45;
            double currentHeading = drive.getExternalHeading();

            drive.turn(currentHeading - correction);
        }
        if (rightDistance > leftDistance) {
            double error = Math.atan(DriveConstants.TRACK_WIDTH / rightDistance);
            double correction = error - 45;
            double currentHeading = drive.getExternalHeading();

            drive.turn(currentHeading - correction);
        }
    }
}
