package org.firstinspires.ftc.teamcode.opmode.command.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Claw;

public class Reset extends SequentialCommandGroup {
    public Reset(Claw claw) {
        addCommands(
                new InstantCommand(claw::Reset)
        );
    }
}
