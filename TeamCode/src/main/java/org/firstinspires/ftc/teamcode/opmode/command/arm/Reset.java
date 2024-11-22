package org.firstinspires.ftc.teamcode.opmode.command.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Arm;
public class Reset extends SequentialCommandGroup {
    public Reset(Arm arm) {
        addCommands(
                new InstantCommand(arm::reset)
        );
    }
}
