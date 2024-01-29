package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;

public class SlideReset extends SequentialCommandGroup {
    public SlideReset (SlideV2 slideV2, Wrist wrist, Outtake outtake) {
        new InstantCommand(slideV2::liftRest);
        //new WaitCommand(100);
        new InstantCommand(wrist::Reset);
        new InstantCommand(outtake::reset);
    }
}
