package org.firstinspires.ftc.teamcode.SummerProjects;



import static org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance.Sensor.Distance;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drive;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;


public class Realignment extends SubsystemBase {
    private final ColorRangeSensor leftSensor, rightSensor;
    private final Telemetry telemetry;
    public boolean Ignored = false;
    public static double error = 0;
    private static Drivetrain drive;
    public static HardwareMap hardwareMap;
    public boolean leftImbalance;

    public Realignment(Telemetry telemetry, HardwareMap hardwareMap) {
        this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "leftSensor");
        this.rightSensor = hardwareMap.get(ColorRangeSensor.class, "rightSensor");
        this.telemetry = telemetry;
    }

    public void periodic() {
        telemetry.addData("DistanceLeft", leftSensor.getDistance(DistanceUnit.MM));
        telemetry.addData("DistanceRight", rightSensor.getDistance(DistanceUnit.MM));
        telemetry.addData("leftImbalence", leftImbalance());
        telemetry.addData("rightImbalence", rightImbalance());
        telemetry.addData("Error", error);
    }
    public boolean distance() {
        return leftSensor.getDistance(DistanceUnit.CM) > 3 && rightSensor.getDistance(DistanceUnit.CM) > 3;
    }
    public boolean leftImbalance() {
            return leftSensor.getDistance(DistanceUnit.CM) < rightSensor.getDistance(DistanceUnit.CM);
    }
    public boolean rightImbalance() {
        return rightSensor.getDistance(DistanceUnit.CM) < leftSensor.getDistance(DistanceUnit.CM);
    }
    public void realignmentLeft() {

        double error = 0;
        error = Math.atan(DriveConstants.TRACK_WIDTH / leftSensor.getDistance(DistanceUnit.INCH));
        if (error > 1) {
            drive.turn(drive.getHeading() - (error - 45));
        }
    }
    public void realignmentRight() {
        double error = 0;
        error = Math.atan(DriveConstants.TRACK_WIDTH / rightSensor.getDistance(DistanceUnit.INCH));
        if (error > 1) {
            drive.turn(drive.getHeading() - (error - 45));
        }
    }
    public void backingUp() throws InterruptedException {
        if (leftSensor.getDistance(DistanceUnit.INCH) <= 2 && rightSensor.getDistance(DistanceUnit.INCH) <= 2) {
            drive.timedPowers(-0.2, -0.2, -0.2, -0.2, 1000);
        }
    }
}

