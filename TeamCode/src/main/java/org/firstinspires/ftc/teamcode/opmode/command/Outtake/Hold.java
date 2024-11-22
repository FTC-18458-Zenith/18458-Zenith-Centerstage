package org.firstinspires.ftc.teamcode.opmode.command.Outtake;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.hardware.SensorDistance;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.IntakeV2;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Wheel;

public class Hold extends SequentialCommandGroup {
    public Hold (Outtake outtake) {
        addCommands(
                new InstantCommand(outtake::reset)
        );
    }
}
