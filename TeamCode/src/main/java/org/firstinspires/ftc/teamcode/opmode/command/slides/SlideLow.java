package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;

public class SlideLow extends SequentialCommandGroup {
    public SlideLow(SlideV2 slideV2, Wrist wrist) {
        new InstantCommand(slideV2::liftLow);

        new WaitCommand(100);
        new InstantCommand(wrist::Score);
    }
}
