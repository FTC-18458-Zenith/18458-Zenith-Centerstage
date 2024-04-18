package org.firstinspires.ftc.teamcode.SummerProjects;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;


public class Realignment extends SubsystemBase {
    private final ColorRangeSensor leftSensor, rightSensor;
    private static Drive drive;
    private static AutoDistance autoDistance;
    private static Telemetry telemetry;
    public static class Sensor {
        public static double leftDistance = 0;
        public static double rightDistance = 0;
    }

    public Realignment(HardwareMap hardwareMap, Telemetry telemetry) {
        this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "frontSensor");
        this.rightSensor = hardwareMap.get(ColorRangeSensor.class, "backSensor");

    }
    public void reAlignment() {
        double leftDistance = leftSensor.getDistance(DistanceUnit.INCH);

        double rightDistance = rightSensor.getDistance(DistanceUnit.INCH);

        double error = 0;

        if (autoDistance.InRange()) {
            if (leftDistance > rightDistance) {

                error = Math.atan(DriveConstants.TRACK_WIDTH / leftDistance);
                if (error <= 1) {
                    double correction = error - 45;
                    double currentHeading = drive.getExternalHeading();

                    drive.turn(currentHeading - correction);
                }
                else drive.turn(drive.getExternalHeading());
            }
            if (rightDistance > leftDistance) {

                error = Math.atan(DriveConstants.TRACK_WIDTH / rightDistance);
                if (error <= 1) {
                    double correction = error - 45;
                    double currentHeading = drive.getExternalHeading();

                    drive.turn(currentHeading - correction);
                }
                else drive.turn(drive.getExternalHeading());

            }
        }
        else drive.turn(drive.getExternalHeading());
    }
}
