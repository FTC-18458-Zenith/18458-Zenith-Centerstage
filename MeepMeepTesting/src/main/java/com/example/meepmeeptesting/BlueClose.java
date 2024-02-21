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

        Pose2d blueClosePoseLeft = new Pose2d(34, 30, Math.toRadians(0));
        Pose2d leftBackdropBlueCenter = new Pose2d(50, 30, Math.toRadians(180));
        Pose2d parkingBlue = new Pose2d(60, 60, Math.toRadians(90));
        Pose2d blueClosePoseRight = new Pose2d(32, 30, Math.toRadians(180));
        // IDK WHAT THIS IS

        Pose2d scorePose = new Pose2d(47, 39, Math.toRadians(180));
        Pose2d resetPose = new Pose2d(35, 35, Math.toRadians(180));
        Pose2d rightPose = new Pose2d(32, 30, Math.toRadians(180));
        Pose2d leftPose = new Pose2d(10, 30, Math.toRadians(180));
        Pose2d midPose = new Pose2d(24, 24, Math.toRadians(180));


        RoadRunnerBotEntity blueAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(16, 16)
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                                driveShim.trajectorySequenceBuilder(new Pose2d(17, 63, Math.toRadians(0)))
//                                        .lineToLinearHeading(leftBackdropBlueCenter)
//                                        .lineToLinearHeading(blueClosePoseRight)
////                                .lineToLinearHeading(new Pose2d(47, 40, Math.toRadians(180)))
//                                        .splineToConstantHeading(new Vector2d(50, 62), Math.toRadians(90))
                                        .forward(20)
                                        .lineToLinearHeading(resetPose)
                                        .lineToLinearHeading(leftPose)
                                        .lineToLinearHeading(scorePose)
                                        .strafeRight(25)
                                        .splineToConstantHeading(new Vector2d(55, 55), Math.toRadians(180))
                                        .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueAllianceLeft)
                .start();
    }
}
