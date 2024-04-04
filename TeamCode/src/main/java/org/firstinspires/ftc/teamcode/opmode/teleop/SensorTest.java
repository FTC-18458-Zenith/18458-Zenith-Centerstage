package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.command.Sensor.AutoSensor;
import org.firstinspires.ftc.teamcode.opmode.command.Sensor.DisableSensor;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

@Config
@TeleOp
public class SensorTest extends MatchOpMode {

    private GamepadEx driverGamepad;

    private AutoDistance Sensor;

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);

        Sensor = new AutoDistance(telemetry, hardwareMap);

    }

    @Override
    public void configureButtons() {


        Button SensorOn = (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
                .whenPressed(new AutoSensor(Sensor));

        Button SensorOff = (new GamepadButton(driverGamepad, GamepadKeys.Button.B))
                .whenPressed(new DisableSensor(Sensor));

    }

    @Override
    public void matchStart() {

    }
}
