package org.firstinspires.ftc.teamcode.opmode.command.Sensor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;

public class DisableSensor extends SequentialCommandGroup {
    public DisableSensor(AutoDistance sensor) {
        addCommands(
                new InstantCommand(sensor::disableDistance)
        );
    }
}
