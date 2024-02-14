package org.firstinspires.ftc.teamcode.opmode.command.drive;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;

public class SlowDriveCommand extends DefaultDriveCommand {
    public SlowDriveCommand(Drivetrain drive, GamepadEx driverGamepad, boolean isFieldCentric) {
        super(drive, driverGamepad, isFieldCentric);
        this.multiplier = 0.3;
    }
}