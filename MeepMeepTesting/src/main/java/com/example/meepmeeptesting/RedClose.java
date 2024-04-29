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

        Double leftCycleScore = -28.0;
        Double rightCycleScore = -44.0;
        Double centerCycleScore = -36.0;

        Pose2d rightSpike = new Pose2d(15, -39, Math.toRadians(45));
        Pose2d centerSpike = new Pose2d(21, -32, Math.toRadians(180 - 45));
        Pose2d leftSpike = new Pose2d(6, -39, Math.toRadians(180 - 45));

        Pose2d rightScore = new Pose2d(49, -44, Math.toRadians(180));
        Pose2d centerScore = new Pose2d(49, -36, Math.toRadians(180));
        Pose2d leftScore = new Pose2d(49, -28, Math.toRadians(180));

        Pose2d rightStartCycle = new Pose2d(30, -25, Math.toRadians(180));
        Pose2d leftStartCycle = new Pose2d(30, -20, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceRight = new DefaultBotBuilder(meepMeep)
                //TODO: MAKE AUTO PARK IN THE CORNER
                .setColorScheme((new ColorSchemeRedLight()))
                .setDimensions(16, 16)
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(7, 60, Math.toRadians(90)))
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAllianceRight)
                .start();
    }
};