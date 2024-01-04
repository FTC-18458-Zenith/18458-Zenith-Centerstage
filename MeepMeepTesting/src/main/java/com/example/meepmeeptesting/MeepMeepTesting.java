package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.util.Random;
import java.util.Vector;

public class MeepMeepTesting {
    static int randomization;
    static int MAX = 3;
    public static void main(String[] args) {
        Pose2d purplePixelRed = new Pose2d(10, -32, Math.toRadians(180));
        Pose2d backdropRed = new Pose2d(50, -32, Math.toRadians(180));
        Pose2d setUpCycle = new Pose2d(0, -13, Math.toRadians(180));
        Pose2d beginCycleRed = new Pose2d(-61, -11.5, Math.toRadians(180));

        Random random = new Random();
        randomization = random.nextInt(MAX) + 1;
        MeepMeep meepMeep = new MeepMeep(650);

        RoadRunnerBotEntity redAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(13.4,14.8031)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, -63, Math.toRadians(270)))
                                .lineToLinearHeading(purplePixelRed)
                                .lineToLinearHeading(new Pose2d(10, -49, Math.toRadians(180)))
                                .waitSeconds(0.15)
                                .lineToLinearHeading(backdropRed)
                                .waitSeconds(0.15)
                                .lineToLinearHeading(setUpCycle)
                                .lineToLinearHeading(beginCycleRed)
                                .waitSeconds(0.15)
                                .lineToLinearHeading(setUpCycle)
                                .lineToLinearHeading(backdropRed)
                                .waitSeconds(0.15)
                                .build()

                );
        RoadRunnerBotEntity blueAlliance = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueDark()))
                .setDimensions(13.4,14.8031)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(90), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-34, 63, Math.toRadians(90)))
                                /*
                                What to do for the Farside auto
                                Do the same for purple pixel, but go to the pixel stack, and continue from there
                                 */
                                .lineToLinearHeading(new Pose2d(-34, 30, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-34, 48, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-60, 36, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-60, 26, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(0,8, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(50, 41.5, Math.toRadians(180)))

                                .lineToLinearHeading(new Pose2d(0,8, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-60, 24, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(0,8, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(50, 41.5, Math.toRadians(180)))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAlliance)
                .addEntity(blueAlliance)
                .start();
    }
}