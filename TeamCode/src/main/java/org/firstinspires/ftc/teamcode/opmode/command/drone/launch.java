package org.firstinspires.ftc.teamcode.opmode.command.drone;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.Drone;

public class launch extends SequentialCommandGroup {
    public launch (Drone drone) {
        new InstantCommand(drone::score);
    }
}
