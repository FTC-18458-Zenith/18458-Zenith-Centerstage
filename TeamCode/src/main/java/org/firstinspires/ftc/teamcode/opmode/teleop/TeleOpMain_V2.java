package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystem.Arm_V2;
import org.firstinspires.ftc.teamcode.subsystem.Drive;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;
import org.firstinspires.ftc.teamcode.util.MatchOpMode;

public class TeleOpMain_V2 extends MatchOpMode {

    private GamepadEx driverGamepad; //Driver 1
    private GamepadEx operatorGamepad; // Driver 2

    private Wrist wrist;
    private Arm_V2 arm;

    Drive drive = new Drive(this);

    @Override
    public void robotInit() {

        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        wrist = new Wrist(hardwareMap, telemetry);
        arm = new Arm_V2(hardwareMap, telemetry);

    }

    @Override
    public void configureButtons() {

        Button clawGrab = new GamepadButton(operatorGamepad, GamepadKeys.Button.A);

    }

    @Override
    public void matchStart() {
        drive.teleOp();

    }
}
