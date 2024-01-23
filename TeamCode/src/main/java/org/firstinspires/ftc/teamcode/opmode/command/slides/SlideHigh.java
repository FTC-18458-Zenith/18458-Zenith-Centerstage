package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;

public class SlideHigh extends SequentialCommandGroup {
    public SlideHigh(SlideV2 slideV2, Wrist wrist) {
        new InstantCommand(slideV2::liftHigh);

        new WaitCommand(100);
        new InstantCommand(wrist::Score);
    }
}
