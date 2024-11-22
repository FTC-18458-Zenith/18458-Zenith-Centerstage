package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wrist;

public class SlideAuto extends SequentialCommandGroup {
    public SlideAuto(SlideV2 slideV2, Wrist wrist, Wheel wheel) {
        addCommands(
                new InstantCommand(slideV2::liftAuto)
        );
        addCommands(
                new InstantCommand(wrist::Score)
        );
        addCommands(
                new InstantCommand(wheel::off)
        );
    }
}
