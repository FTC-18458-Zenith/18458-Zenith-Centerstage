package org.firstinspires.ftc.teamcode.opmode.command.Outtake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;

public class Score extends SequentialCommandGroup {
    public Score (Outtake outtake, IntakeV2 intakeV2) {
        new InstantCommand(outtake::score);
        new InstantCommand(intakeV2::IntakeOff);
    }
}
