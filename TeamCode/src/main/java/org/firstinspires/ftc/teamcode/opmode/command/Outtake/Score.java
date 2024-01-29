package org.firstinspires.ftc.teamcode.opmode.command.Outtake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;

public class Score extends SequentialCommandGroup {
    public Score (Outtake outtake, IntakeV2 intakeV2) {
        new InstantCommand(outtake::score);
        new InstantCommand(intakeV2::IntakeOff);
    }
}
