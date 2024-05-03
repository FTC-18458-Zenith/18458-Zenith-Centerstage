package org.firstinspires.ftc.teamcode.opmode.command.Sensor;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.SummerProjects.Realignment;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

public class Realign extends CommandBase {
            private final Realignment sensor;
            private final Drivetrain drive;
            public Realign(Realignment sensor, Drivetrain drive) {
                this.sensor = sensor;
                this.drive = drive;
                addRequirements(sensor);
            }
            @Override
            public void execute() {
                    new ConditionalCommand(
                            new SequentialCommandGroup(
                                    //True
//                                    new InstantCommand(sensor::backingUp)
                                    new InstantCommand(drive::backUp),
                                    new WaitCommand(400),
                                    new InstantCommand(drive::waiting)

                            ),
                            new SequentialCommandGroup(
                                    //False
                                   new InstantCommand(drive::waiting)

                            ),
                            sensor::distance);
    }
}
