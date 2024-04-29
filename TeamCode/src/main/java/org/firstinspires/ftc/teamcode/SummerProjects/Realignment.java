package org.firstinspires.ftc.teamcode.SummerProjects;


import static org.firstinspires.ftc.teamcode.SummerProjects.Realignment.AlignmentState.ALIGNED;
import static org.firstinspires.ftc.teamcode.SummerProjects.Realignment.AlignmentState.UNALIGNED;
import static org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance.Sensor.Distance;

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
    private final Telemetry telemetry;

    public boolean Ignored = false;

    public static double error = 0;

    public static Drive drive;
    public static class Sensor {
        public static double Distance = 0;
        public static double BackBoardDistance = 2; //IN
    }

    public enum AlignmentState {
        ALIGNED,
        UNALIGNED
    }

    public static AlignmentState alignmentState = UNALIGNED;

    public Realignment(Telemetry telemetry, HardwareMap hardwareMap) {
        this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "leftSensor");
        this.rightSensor = hardwareMap.get(ColorRangeSensor.class, "rightSensor");
        this.telemetry = telemetry;
    }

    public void periodic() {
        telemetry.addData("Distance", Distance);
        telemetry.addData("Error", error);
    }

    public boolean TooFar() {
        Ignored = false;

        Distance = (leftSensor.getDistance(DistanceUnit.INCH) + rightSensor.getDistance(DistanceUnit.INCH) / 2);

        return (Distance) > AutoDistance.Sensor.BackBoardDistance;
    }

    public boolean InRange() {
        Ignored = false;

        Distance = (leftSensor.getDistance(DistanceUnit.INCH) + rightSensor.getDistance(DistanceUnit.INCH) / 2);

        return (Distance) <= AutoDistance.Sensor.BackBoardDistance;
    }
    public void check() {
        double leftDistance = leftSensor.getDistance(DistanceUnit.INCH);

        double rightDistance = rightSensor.getDistance(DistanceUnit.INCH);

        double error = 0;

        if (leftDistance > rightDistance) {
            error = Math.atan(DriveConstants.TRACK_WIDTH / leftDistance);
        }
        if (rightDistance > leftDistance) {
            error = Math.atan(DriveConstants.TRACK_WIDTH / rightDistance);
        }
        if (error <= 1) {
            double correction = error - 45;
            double currentHeading = drive.getExternalHeading();

            drive.turn(currentHeading - correction);
            alignmentState = UNALIGNED;
        }
        else alignmentState = ALIGNED;
    }

    public boolean disableDistance() {
        return Ignored;
    }

    public void run() {

        switch (alignmentState) {
            case UNALIGNED:
                if (TooFar()) break;
                check();
                break;
            case ALIGNED:
                break;
        }
    }
}

