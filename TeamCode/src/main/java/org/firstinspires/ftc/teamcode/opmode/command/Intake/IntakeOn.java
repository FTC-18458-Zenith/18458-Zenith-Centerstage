package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.IntakeV2;

public class IntakeOn extends SequentialCommandGroup {
    public IntakeOn (IntakeV2 intakeV2) {
        new InstantCommand(intakeV2::IntakeOn);

    }
}
