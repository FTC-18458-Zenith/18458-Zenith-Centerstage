package org.firstinspires.ftc.teamcode.opmode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SummerProjects.Realignment;
import org.firstinspires.ftc.teamcode.opmode.command.Sensor.Realign;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

@Config
@TeleOp
public class SensorTest extends MatchOpMode {
    private GamepadEx driverGamepad;
    private Realignment sensor;
    private Drivetrain drivetrain;
    @Override
    public void robotInit() {


        sensor = new Realignment(telemetry, hardwareMap);

    }

    @Override
    public void configureButtons() {
         sensor.setDefaultCommand(new Realign(sensor));

    }
    @Override
    public void matchStart() {

    }
}
