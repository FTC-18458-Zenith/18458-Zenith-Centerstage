package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Wheel extends SubsystemBase {
    Telemetry telemetry;
    private final CRServo wheel;

    public Wheel(final HardwareMap hMap, Telemetry telemetry) {
        this.wheel = new CRServo(hMap, "wheel");
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {

    }
    public void intake() {
        wheel.set(1);
    }
    public void outtake() {
        wheel.set(-1);
    }
    public void off() {
        wheel.set(0);
    }

}
