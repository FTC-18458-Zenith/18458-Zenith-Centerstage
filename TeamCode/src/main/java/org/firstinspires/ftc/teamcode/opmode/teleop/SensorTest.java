package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SummerProjects.Realignment;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

@Config
@TeleOp
public class SensorTest extends MatchOpMode {

    private GamepadEx driverGamepad;

    private Realignment Sensor;

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);

        Sensor = new Realignment(telemetry, hardwareMap);

    }

    @Override
    public void configureButtons() {


    }

    @Override
    public void matchStart() {
        Button sensorOn = (new GamepadButton(driverGamepad, GamepadKeys.Button.B))
                .whenPressed(new InstantCommand(Sensor::run));

    }
}
