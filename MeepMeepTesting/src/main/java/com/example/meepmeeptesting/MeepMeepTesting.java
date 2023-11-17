package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(900);

        RoadRunnerBotEntity redAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(13.4,14.8031)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14, -60, Math.toRadians(90)))
                                .forward(29)
                                .turn(Math.toRadians(-90))
                                .forward(30)
                                .build()
                );
        RoadRunnerBotEntity blueAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueDark()))
                .setDimensions(13.4,14.8031)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-34, 68, Math.toRadians(270)))
                                .forward(35)
                                .turn(Math.toRadians(-90))
                                .forward(-17)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAlliance)
                .addEntity(blueAlliance)
                .start();
    }
}