package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import static org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance.Sensor.BackBoardDistance;
import static org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance.Sensor.Distance;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class AutoDistance extends SubsystemBase {
    private static ColorRangeSensor leftSensor;
    private static ColorRangeSensor rightSensor;
        private final Telemetry telemetry;

        public boolean Ignored = false;

        public static double error = 0;

    public static Sensor sensor;
    public static class Sensor {
        public static double Distance = 0;
        public static double BackBoardDistance = 2; //IN
    }

        public enum SensorState {
            LEFT,
            RIGHT,
            FORWARD,
            DISABLED
    }

    public static SensorState sensorState = SensorState.FORWARD;

        public AutoDistance(Telemetry telemetry, HardwareMap hardwareMap) {
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

            return (Distance) > Sensor.BackBoardDistance;
        }

        public boolean InRange() {
            Ignored = false;

            Distance = (leftSensor.getDistance(DistanceUnit.INCH) + rightSensor.getDistance(DistanceUnit.INCH) / 2);

            return (Distance) <= Sensor.BackBoardDistance;
        }

        /*public boolean Left() {
            Ignored = false;


        }*/

        public boolean disableDistance() {
            return Ignored;
        }

    public void run() {
        switch (sensorState) {
            case DISABLED:
                if (disableDistance()) break;
                sensorState = SensorState.FORWARD;
                break;
            case FORWARD:
                if (InRange()) break;
                if (TooFar())
                    error = Distance - Sensor.BackBoardDistance;

                break;
        }
    }
}