package org.firstinspires.ftc.teamcode.opmode.command.Outtake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;

public class SensorScore extends SequentialCommandGroup {
    public SensorScore(Outtake outtake) {
        addCommands(
                new InstantCommand(outtake::sensor)
        );
    }
}
