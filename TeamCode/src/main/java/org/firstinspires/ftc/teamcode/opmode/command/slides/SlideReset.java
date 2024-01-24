package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;

public class SlideReset extends SequentialCommandGroup {
    public SlideReset (SlideV2 slideV2, Wrist wrist, Outtake outtake) {
        new InstantCommand(slideV2::liftRest);
        //new WaitCommand(100);
        new InstantCommand(wrist::Reset);
        new InstantCommand(outtake::reset);
    }
}
