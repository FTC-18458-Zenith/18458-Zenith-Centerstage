package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Arm;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Claw;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
public class SlideHigh extends SequentialCommandGroup {
    public SlideHigh(SlideV2 slideV2, Claw claw, Arm arm) {
        addCommands(
                new InstantCommand(arm::reset)
        );
        addCommands(
                new InstantCommand(slideV2::liftHigh)
        );
    }
}
