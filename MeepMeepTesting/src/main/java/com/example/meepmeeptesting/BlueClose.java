package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueClose {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(650);
        Double leftScore = 28.0;
        Double rightScore = 44.0;
        Double centerScore = 36.0;

        Pose2d closeRightSpike = new Pose2d(15, 39, Math.toRadians(295));
        Pose2d closeCenterSpike = new Pose2d(21, 32, Math.toRadians(180 - 45));
        Pose2d closeLeftSpike = new Pose2d(6, 39, Math.toRadians(180 - 45));

        Pose2d closeRightScore = new Pose2d(49, 44, Math.toRadians(180));
        Pose2d closeCenterScore = new Pose2d(49, 36, Math.toRadians(180));
        Pose2d closeLeftScore = new Pose2d(49, 28, Math.toRadians(180));

        Pose2d closeRightStartCycle = new Pose2d(30, 25, Math.toRadians(180));
        Pose2d closeLeftStartCycle = new Pose2d(30, 20, Math.toRadians(180));

        RoadRunnerBotEntity blueAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(14, 16)
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                                driveShim.trajectorySequenceBuilder(new Pose2d(7, 63, Math.toRadians(0)))
                                        .lineToLinearHeading(closeRightSpike)
                                        .lineToLinearHeading(closeRightScore)
                                        .waitSeconds(1)
//                                        //Cycle 1 start
//                                        .lineToLinearHeading(closeRightStartCycle)
//                                        .splineToConstantHeading(new Vector2d(59, 11), Math.toRadians(180))
//                                        .waitSeconds(1)
//                                        //Cycle 1 end
//                                        .lineToLinearHeading(new Pose2d(0, 14, Math.toRadians(180)))
//                                        .splineToConstantHeading(new Vector2d(49, rightScore), Math.toRadians(270))
//                                        .waitSeconds(1)
                                        .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueAllianceLeft)
                .start();
    }
}
