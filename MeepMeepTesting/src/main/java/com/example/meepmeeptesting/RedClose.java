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

public class RedClose {
    static int randomization;
    static int MAX = 3;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(650);


        Pose2d leftScoringPose = new Pose2d(49, -28, Math.toRadians(180));
        Pose2d midScoringPose = new Pose2d(49,-34, Math.toRadians(180));
        Pose2d rightScoringPose = new Pose2d(49, -41, Math.toRadians(180));
        Pose2d resetPose = new Pose2d(35, -35, Math.toRadians(180));
        Pose2d rightPoseSpike = new Pose2d(32, -30, Math.toRadians(180));
        Pose2d leftPoseSpike = new Pose2d(10, -30, Math.toRadians(180));
        Pose2d midPoseSpike = new Pose2d(24, -24, Math.toRadians(180));


        RoadRunnerBotEntity redAllianceRight = new DefaultBotBuilder(meepMeep)
                //TODO: MAKE AUTO PARK IN THE CORNER
                .setColorScheme((new ColorSchemeRedLight()))
                .setDimensions(16, 16)
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(90)))
                                .strafeRight(20)
                                .lineToLinearHeading(resetPose)
                                .lineToLinearHeading(leftPoseSpike)
                                .lineToLinearHeading(leftScoringPose)
                                .strafeRight(28)
                                .splineToConstantHeading(new Vector2d(60, -9), Math.toRadians(180))
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAllianceRight)
                .start();
    }
};