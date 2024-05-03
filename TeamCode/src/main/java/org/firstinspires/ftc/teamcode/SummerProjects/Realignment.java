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
    private final ColorRangeSensor leftSensor;
    private final Telemetry telemetry;
    public boolean Ignored = false;
    public static double error = 0;
    private static Drivetrain drive;
    public static HardwareMap hardwareMap;
    public boolean leftImbalance;

    public Realignment(Telemetry telemetry, HardwareMap hardwareMap) {
        this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "leftSensor");
        this.telemetry = telemetry;
    }

    public void periodic() {
        telemetry.addData("DistanceLeft", leftSensor.getDistance(DistanceUnit.CM));
    }
    public boolean distance() {
        return leftSensor.getDistance(DistanceUnit.INCH) < 2.1;
    }
    public void realignmentLeft() {

        double error = 0;
        error = Math.atan(DriveConstants.TRACK_WIDTH / leftSensor.getDistance(DistanceUnit.INCH));
        if (error > 1) {
            drive.turn(drive.getHeading() - (error - 45));
        }
    }
    public void backingUp() {
        if (leftSensor.getDistance(DistanceUnit.INCH) <= 2.1) {
            drive.setPowers(-0.2, -0.2, -0.2, -0.2);
        }
        else if (leftSensor.getDistance(DistanceUnit.INCH) >= 2.1) {
            drive.setPowers(0,0,0,0);
        }
    }
}

