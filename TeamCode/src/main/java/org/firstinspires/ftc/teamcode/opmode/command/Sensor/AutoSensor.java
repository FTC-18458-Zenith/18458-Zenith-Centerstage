package org.firstinspires.ftc.teamcode.opmode.command.Sensor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;

public class AutoSensor extends SequentialCommandGroup {
    public AutoSensor(AutoDistance sensor) {
        addCommands(
                new InstantCommand(sensor::run)
        );
    }
}
