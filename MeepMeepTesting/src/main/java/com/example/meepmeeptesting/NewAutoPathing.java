package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


public class NewAutoPathing {
    public static void main(String[] args) {
        Double leftScore = -28.0;
        Double rightScore = -44.0;
        Double centerScore = -36.0;

        Pose2d closeRightSpike = new Pose2d(15, -39, Math.toRadians(45));
        Pose2d closeCenterSpike = new Pose2d(21, -32, Math.toRadians(180 - 45));
        Pose2d closeLeftSpike = new Pose2d(6, -39, Math.toRadians(180 - 45));

        Pose2d closeRightScore = new Pose2d(49, -44, Math.toRadians(180));
        Pose2d closeCenterScore = new Pose2d(49, -36, Math.toRadians(180));
        Pose2d closeLeftScore = new Pose2d(49, -28, Math.toRadians(180));

        Pose2d closeRightStartCycle = new Pose2d(30, -25, Math.toRadians(180));
        Pose2d closeLeftStartCycle = new Pose2d(30, -20, Math.toRadians(180));

        MeepMeep meepMeep = new MeepMeep(650);
        RoadRunnerBotEntity Robot = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(14, 16)
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(90)))
                                .lineToLinearHeading(closeCenterSpike)
                                .lineToLinearHeading(closeCenterScore)
                                .waitSeconds(1)
//
                                .lineToLinearHeading(closeRightStartCycle)
                                .splineToConstantHeading(new Vector2d(-59, -11), Math.toRadians(180))
                                .waitSeconds(1)
                                //Cycle 1 end
                                .lineToLinearHeading(new Pose2d(0, -14, Math.toRadians(180)))
                                .splineToConstantHeading(new Vector2d(49, centerScore), Math.toRadians(270))
                                .waitSeconds(1)
                                .splineToConstantHeading(new Vector2d(60, -60), Math.toRadians(0))
                                .build()

                );
        Double farLeftScore = 28.0;
        Double farRightScore = 44.0;
        Double farCenterScore = 36.0;
        Pose2d farLeftSpike = new Pose2d(-36, 30, Math.toRadians(180));
        Pose2d farCenterSpike = new Pose2d(-55,23, Math.toRadians(180));

        Pose2d farCenterSpline = new Pose2d(-46, 10, Math.toRadians(180));
        Pose2d farLeftSpline = new Pose2d(-45, 25, Math.toRadians(180));
        Pose2d moving = new Pose2d(0, 10, Math.toRadians(180));

        RoadRunnerBotEntity Robot2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedLight())
                .setDimensions(13, 16)
                .setConstraints(45,45, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(-40, 63, Math.toRadians(270)))
                                .lineToLinearHeading(farCenterSpike)
                                .back(15)
//                                .turn(Math.toRadians(180))
                                .lineToLinearHeading(farCenterSpline)
                                .lineToLinearHeading(moving)
                                .splineToConstantHeading(new Vector2d(49, farCenterScore), Math.toRadians(90))
                                //Cycle 1
                                .lineToLinearHeading(moving)
                                .splineToConstantHeading(new Vector2d(-52, 10 ), Math.toRadians(0))
                                .waitSeconds(1)
                                .lineToLinearHeading(moving)
                                .splineToConstantHeading(new Vector2d(49, farCenterScore), Math.toRadians(90))
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Robot)
                .addEntity(Robot2)
                .start();
    }
}
