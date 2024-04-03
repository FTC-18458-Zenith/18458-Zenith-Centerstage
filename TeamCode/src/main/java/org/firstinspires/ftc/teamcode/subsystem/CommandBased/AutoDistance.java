package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class AutoDistance extends SubsystemBase {
        private ColorRangeSensor leftSensor;
    private ColorRangeSensor rightSensor;
        private final Telemetry telemetry;

        public boolean Ignored = false;

        public static double error = 0;

    public static Sensor sensor;


    private static class Sensor {
        public static volatile double BackBoardDistance = 1; //IN
    }

        public enum SensorState {
            LEFT,
            RIGHT,
            FORWARD,
            DISABLED
    }

    public static SensorState sensorState = SensorState.DISABLED;

        public AutoDistance(Telemetry telemetry, HardwareMap hardwareMap) {
            this.leftSensor = hardwareMap.get(ColorRangeSensor.class, "leftSensor");
            this.rightSensor = hardwareMap.get(ColorRangeSensor.class, "rightSensor");
            this.telemetry = telemetry;
        }

        public void periodic() {


        }

    public double Distance = leftSensor.getDistance(DistanceUnit.INCH) + rightSensor.getDistance(DistanceUnit.INCH) / 2;

        public boolean TooFar() {
            Ignored = false;

            telemetry.addData("Distance", Distance);

            return (Distance) < Sensor.BackBoardDistance;
        }

        public boolean InRange() {
            Ignored = false;

            telemetry.addData("Distance", Distance);

            return (Distance) >= Sensor.BackBoardDistance;
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