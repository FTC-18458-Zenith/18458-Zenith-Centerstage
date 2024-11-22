package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;

public class AutoStack extends SequentialCommandGroup {
    public AutoStack (IntakeV2 intake) {

        addCommands(
                new InstantCommand(intake::autoStack)
        );
    }

}

