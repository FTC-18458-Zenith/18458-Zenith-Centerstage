package org.firstinspires.ftc.teamcode.opmode.command.Sensor;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.SummerProjects.Realignment;
import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;
import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;

public class Realign extends CommandBase {
            private final Realignment sensor;
            public Realign(Realignment sensor) {
                this.sensor = sensor;
                addRequirements(sensor);
            }
            @Override
            public void execute() {
                    new ConditionalCommand(
                            new SequentialCommandGroup(
                                    //True
                                    new InstantCommand(sensor::realignmentLeft)

                            ),
                            new SequentialCommandGroup(
                                    //False
                                    new InstantCommand(sensor::realignmentRight)
                            ),
                            sensor::leftImbalance);
    }
}
