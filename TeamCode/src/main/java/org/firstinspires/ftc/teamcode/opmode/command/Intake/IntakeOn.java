package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;

public class IntakeOn extends SequentialCommandGroup {
    public IntakeOn (IntakeV2 intakeV2, Wheel wheel) {
        addCommands(
                new InstantCommand(intakeV2::IntakeOn)
        );
        addCommands(
                new InstantCommand(wheel::intake)
        );
    }
}
