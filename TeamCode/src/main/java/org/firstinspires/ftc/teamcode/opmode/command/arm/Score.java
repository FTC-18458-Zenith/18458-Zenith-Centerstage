package org.firstinspires.ftc.teamcode.opmode.command.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Arm;
public class Score extends SequentialCommandGroup {
    public Score(Arm arm) {
        addCommands(
                new InstantCommand(arm::grab)
        );
    }
}
