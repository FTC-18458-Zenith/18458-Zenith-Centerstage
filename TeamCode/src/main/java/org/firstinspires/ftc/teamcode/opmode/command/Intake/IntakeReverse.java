package org.firstinspires.ftc.teamcode.opmode.command.Intake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;

public class IntakeReverse extends SequentialCommandGroup {
    public IntakeReverse (IntakeV2 intakeV2, Wheel wheel, boolean auto) {

        if (auto) {
            addCommands(
                    new ParallelCommandGroup(
                            new InstantCommand(wheel::outtake),
                    new InstantCommand(intakeV2::IntakeReverseAuto)

            ),
                    new WaitCommand(1000),
                    new InstantCommand(intakeV2::IntakeOff),
                    new InstantCommand(wheel::off)
                    );
        } else {
            addCommands(
                    new InstantCommand(intakeV2::IntakeReverse)
            );
            addCommands(
                    new InstantCommand(wheel::outtake)
            );
        }

    }
}
