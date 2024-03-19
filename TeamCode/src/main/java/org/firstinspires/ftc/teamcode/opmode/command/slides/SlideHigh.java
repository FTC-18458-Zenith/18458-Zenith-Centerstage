package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;

public class SlideHigh extends SequentialCommandGroup {
    public SlideHigh(SlideV2 slideV2, Wrist wrist) {
        addCommands(
                new InstantCommand(slideV2::liftHigh)
        );
        new WaitCommand(1000);
        addCommands(
                new InstantCommand(wrist::Score)
        );
    }
}
