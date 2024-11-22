package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Arm;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Claw;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;

public class SlideReset extends SequentialCommandGroup {
    public SlideReset (SlideV2 slideV2, Claw claw, Arm arm) {
                addCommands(
                        new InstantCommand(arm::score),
                        new InstantCommand(claw::Reset),
                        new InstantCommand(slideV2::liftRest)
                );
    }
}
