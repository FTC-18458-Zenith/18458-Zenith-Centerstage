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

public class RedFar {
    static int randomization;
    static int MAX = 3;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(650);
        Double farLeftScore = -28.0;
        Double farRightScore = -44.0;
        Double farCenterScore = -36.0;

        Pose2d farLeftSpike = new Pose2d(-36, -30, Math.toRadians(180));
        Pose2d farCenterSpike = new Pose2d(-47,-28, Math.toRadians(180));

        Pose2d farCenterSpline = new Pose2d(-50, -25, Math.toRadians(180));
        Pose2d farLeftSpline = new Pose2d(-45, -10, Math.toRadians(180));
        Pose2d moving = new Pose2d(0, -10, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(14, 16)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, -63, Math.toRadians(0)))
                                .back(10)
                                .lineToLinearHeading(farLeftSpike)
                                .lineToLinearHeading(farLeftSpline)
                                .lineToLinearHeading(moving)
                                .splineToConstantHeading(new Vector2d(49, farLeftScore), Math.toRadians(270))
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAllianceLeft)
                .start();
    }
}
