package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;

public class AutoStack extends SequentialCommandGroup {
    public AutoStack (IntakeV2 intake, double stack) {

        if (stack == 1) {
            new InstantCommand(intake::autoStack);
            new InstantCommand(intake::firstCycle1);
        } else if (stack == 2) {
            new InstantCommand(intake::autoStack);
            new InstantCommand(intake::firstCycle2);
        } else if (stack == 3) {
            new InstantCommand(intake::autoStack);
            new InstantCommand(intake::secondCycle1);
        } else if (stack == 4) {
            new InstantCommand(intake::autoStack);
            new InstantCommand(intake::secondCycle2);
        }
    }

}

