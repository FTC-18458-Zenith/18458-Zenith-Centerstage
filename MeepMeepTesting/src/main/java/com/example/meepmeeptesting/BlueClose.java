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

        Pose2d closeLeftSpike = new Pose2d(21, 39, Math.toRadians(90));
        Pose2d closeCenterSpike = new Pose2d(12, 32, Math.toRadians(90));
        Pose2d closeRightSpike = new Pose2d(10, 32, Math.toRadians(0));

        Pose2d closeLeftScore = new Pose2d(49, 44, Math.toRadians(180));
        Pose2d closeCenterScore = new Pose2d(49, 36, Math.toRadians(180));
        Pose2d closeRightScore = new Pose2d(49, 28, Math.toRadians(180));

        Vector2d ParkLeft = new Vector2d(45, 60);
        Vector2d ParkRight = new Vector2d(45, 12);

        RoadRunnerBotEntity blueAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(14, 14)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                                driveShim.trajectorySequenceBuilder(new Pose2d(17, 63, Math.toRadians(90)))

                                        //Pre Load
                                        .lineToLinearHeading(closeLeftSpike)
                                        .splineToLinearHeading(new Pose2d(49, 36, Math.toRadians(180)), Math.toRadians(0))

                                        .waitSeconds(1)

                                        .splineTo(new Vector2d(0, 11), Math.toRadians(180))
                                        .splineTo(new Vector2d(-58, 11), Math.toRadians(180))

                                        .waitSeconds(1)

                                        .lineToConstantHeading(new Vector2d(0, 11))
                                        .lineToSplineHeading(new Pose2d(49, 21, Math.toRadians(-120)))

                                        .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueAllianceLeft)
                .start();
    }
}
