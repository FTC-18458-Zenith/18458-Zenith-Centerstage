package org.firstinspires.ftc.teamcode.opmode.command.claw;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Arm;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Claw;

public class Grab extends SequentialCommandGroup {
    public Grab(Claw claw) {
        addCommands(
                new InstantCommand(claw::Score)
        );
    }
}
