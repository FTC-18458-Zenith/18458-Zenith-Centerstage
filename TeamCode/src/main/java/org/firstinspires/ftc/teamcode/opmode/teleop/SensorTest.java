package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
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

        Sensor = new Realignment(hardwareMap, telemetry);

    }

    @Override
    public void configureButtons() {


    }

    @Override
    public void matchStart() {
        Sensor.reAlignment();

    }
}
