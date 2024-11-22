package org.firstinspires.ftc.teamcode.opmode.command.Outtake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;

public class Score extends SequentialCommandGroup {
    public Score (Outtake outtake, Wheel wheel) {
        addCommands(
                new InstantCommand(outtake::score)
        );

    }
}
