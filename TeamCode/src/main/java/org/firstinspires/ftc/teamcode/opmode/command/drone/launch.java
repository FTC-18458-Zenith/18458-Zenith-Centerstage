package org.firstinspires.ftc.teamcode.opmode.command.drone;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystem.Drone;
import org.firstinspires.ftc.teamcode.subsystem.Outtake;
import org.firstinspires.ftc.teamcode.subsystem.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.Wrist;

public class launch extends SequentialCommandGroup {
    public launch (Drone drone) {
        new InstantCommand(drone::score);
    }
}
