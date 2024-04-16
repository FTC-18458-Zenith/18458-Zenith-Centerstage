package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmode.auto.Other.MessingAround;
import org.firstinspires.ftc.teamcode.opmode.command.Sensor.AutoSensor;
import org.firstinspires.ftc.teamcode.opmode.command.Sensor.DisableSensor;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.DriveConstants;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

@Config
@TeleOp
public class SensorTest extends MatchOpMode {

    private GamepadEx driverGamepad;

    private MessingAround Sensor;

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);

        Sensor = new MessingAround(hardwareMap);

    }

    @Override
    public void configureButtons() {


    }

    @Override
    public void matchStart() {
        Sensor.reAlignment();

    }
}
