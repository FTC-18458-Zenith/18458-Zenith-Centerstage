package org.firstinspires.ftc.teamcode.opmode.auto.Other;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ConfigurableAuto extends SubsystemBase {
    private final Telemetry telemetry;
    private final Gamepad gamepad1;

    public ConfigurableAuto(Telemetry telemetry, Gamepad gamepad1) {
        this.telemetry = telemetry;
        this.gamepad1 = gamepad1;
    }

    public Position position() {
        if (gamepad1.dpad_down) {
            return Position.CLOSE;
        }

        if (gamepad1.dpad_up) {
            return Position.FAR;
        }
            else return null;
        }

    public enum Position {
            CLOSE,
            FAR;
    }
}
