package org.firstinspires.ftc.teamcode.opmode.command.Sensor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.SummerProjects.Realignment;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.AutoDistance;

public class Realign extends SequentialCommandGroup {
    public Realign(Realignment sensor) {
        addCommands(
                new InstantCommand(sensor::check),
                new InstantCommand(sensor::run)
        );
    }
}
