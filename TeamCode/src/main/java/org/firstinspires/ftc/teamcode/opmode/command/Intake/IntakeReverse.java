package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;

public class IntakeReverse extends SequentialCommandGroup {
    public IntakeReverse (IntakeV2 intakeV2, Wheel wheel) {
        addCommands(
                new InstantCommand(intakeV2::IntakeReverse)
        );
        addCommands(
                new InstantCommand(wheel::outtake)
        );
    }
}
