package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
public class DistanceSensor extends SubsystemBase {
        private final ColorSensor frontSensor, backSensor;
        private final Telemetry telemetry;

        public DistanceSensor(Telemetry telemetry, HardwareMap hardwareMap) {
            this.frontSensor = hardwareMap.get(ColorSensor.class, "frontSensor");
            this.backSensor = hardwareMap.get(ColorSensor.class, "backSensor");
            this.telemetry = telemetry;
        }

        public void periodic() {
            telemetry.addData("Pixel in back: ", frontPixelDetection());
            telemetry.addData("Pixel in front: ", backPixelDetection());
        }

        public boolean whitePixelDetection(ColorSensor sensor) {
            return (sensor.blue() > 2000 && sensor.red() > 2000 && sensor.green() > 2000);
        }

        public boolean purplePixelDetection(ColorSensor sensor) {
            return (sensor.red() > 1000 && sensor.blue() > 1500 && sensor.green() > 1000);
        }

        public boolean greenPixelDetection(ColorSensor sensor) {
            return (sensor.green() > 1000);
        }

        public boolean yellowPixelDetection(ColorSensor sensor) {
            return (sensor.blue() > 250 && sensor.red() > 1000 && sensor.green() > 1000);
        }

        public boolean backPixelDetection() {
            return yellowPixelDetection(backSensor) || greenPixelDetection(backSensor) ||
                    purplePixelDetection(backSensor) || whitePixelDetection(backSensor);
        }

        public boolean frontPixelDetection() {
            return yellowPixelDetection(frontSensor) || greenPixelDetection(frontSensor) ||
                    purplePixelDetection(frontSensor) || whitePixelDetection(frontSensor);
        }
}