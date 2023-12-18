package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.util.Random;

public class MeepMeepTesting {
    static int randomization;
    static int MAX = 3;
    public static void main(String[] args) {
        Random random = new Random();

        randomization = random.nextInt(MAX) + 1;
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity redAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(13.4,14.8031)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, -60, Math.toRadians(90)))
                                .forward(28)
                                .turn(Math.toRadians(90))
                                .waitSeconds(0.5)
                                .back(40)
                                .waitSeconds(0.5)
                                .strafeRight(20.5)
                                .forward(110)
                                .waitSeconds(0.25)
                                .back(110)
                                .strafeLeft(23)
                                .waitSeconds(1)
                                .strafeLeft(27.5)
                                .build()
                );
        RoadRunnerBotEntity blueAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueDark()))
                .setDimensions(13.4,14.8031)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-34, 60, Math.toRadians(270)))
                                .forward(27)
                                .waitSeconds(0.25)
                                .turn(Math.toRadians(-90))
                                .back(82)
                                .waitSeconds(0.5)
                                .strafeLeft(21.5)
                                .forward(110)
                                .waitSeconds(0.25)
                                .back(110)
                                .strafeRight(23)
                                .waitSeconds(1)
                                .strafeRight(27.5)
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