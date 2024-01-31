package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.util.Random;
import java.util.Vector;

public class MeepMeepTesting {
    static int randomization;
    static int MAX = 3;
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(650);

        Pose2d LeftPose = new Pose2d(-47, -35, Math.toRadians(270));
        Pose2d MidPose = new Pose2d(-35, -32, Math.toRadians(270));
        Pose2d RightPose = new Pose2d(-34, -34, Math.toRadians(180));

        Pose2d ResetPose = new Pose2d(-35, -60, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(16,16)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40, -63, Math.toRadians(270)))
                                //.forward(15)
                                .lineToLinearHeading(RightPose)
                                .lineToLinearHeading(ResetPose)
                                .back(82)
                                .strafeRight(20)
                                .build()

                );

        Pose2d resetPose = new Pose2d(35, -35, Math.toRadians(180));

        Pose2d rightPose = new Pose2d(32, -30, Math.toRadians(180));
        Pose2d leftPose = new Pose2d(10, -30, Math.toRadians(180));
        Pose2d midPose = new Pose2d(24, -24, Math.toRadians(180));

        Pose2d scorePose = new Pose2d(47, -39, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceRight = new DefaultBotBuilder(meepMeep)
                //TODO: MAKE AUTO PARK IN THE CORNER
                .setColorScheme((new ColorSchemeRedLight()))
                .setDimensions(16, 16)
                .setConstraints(60, 60 , Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(180)))
                                .back(20)
                                .lineToLinearHeading(resetPose)
                                .lineToLinearHeading(midPose)
                                .lineToLinearHeading(scorePose)
                                .build()
                );

       /* Pose2d purplePixelBlueLeft = new Pose2d(-38, 30, Math.toRadians(180));
        //.strafeLeft(10)
        Pose2d purplePixelBlueRight = new Pose2d(-31, 30, Math.toRadians(0));
        //.back(10)
        Pose2d purplePixelBlueCenter = new Pose2d(-36, 32, Math.toRadians(270));
        //.back(15)
        //.lineToLinearHeading(goDownBlue)
        Pose2d goDownBlue = new Pose2d(-60, 18, Math.toRadians(180));
        Pose2d goingToBackdropBlue = new Pose2d(-20,8, Math.toRadians(180));
        Pose2d centerBackdropBlue = new Pose2d(50, 34, Math.toRadians(180));
        Pose2d leftBackdropBlue = new Pose2d(50, 42, Math.toRadians(180));
        Pose2d rightBackdropBlue = new Pose2d(50, 28, Math.toRadians(180));

        RoadRunnerBotEntity blueAllianceRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueDark()))
                .setDimensions(13.4,14.8031)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(90), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, 64, Math.toRadians(90)))
                                .lineToLinearHeading(purplePixelBlueRight)
                                .back(10)
                                .lineToLinearHeading(goingToBackdropBlue)
                                .lineToLinearHeading(rightBackdropBlue)
                                .build()
                );
        Pose2d rightPurplePixelRedLeft = new Pose2d(8, -30, Math.toRadians(180));
        //.strafeLeft(10)
        Pose2d rightPurplePixelRedRight = new Pose2d(16, -30, Math.toRadians(0));
        Pose2d rightPurplePixelRedCenter = new Pose2d(11, -32, Math.toRadians(90));
        //.back(10)
        Pose2d rightGoDownRed = new Pose2d(-60, 18, Math.toRadians(180));
        Pose2d rightGoingToBackdropRed = new Pose2d(-20,8, Math.toRadians(180));
        Pose2d rightBackdropRedCenter = new Pose2d(50, -35, Math.toRadians(180));
        Pose2d rightBackdropRedLeft = new Pose2d(50,-28, Math.toRadians(180));
        Pose2d rightBackdropRedRight = new Pose2d(50, -42, Math.toRadians(180));
        int backingUpRed = 20;
        Pose2d parkingRed = new Pose2d(60, -60, Math.toRadians(90));

        RoadRunnerBotEntity redAllianceRight = new DefaultBotBuilder(meepMeep)
                //TODO: MAKE AUTO PARK IN THE CORNER
                .setColorScheme((new ColorSchemeRedLight()))
                .setDimensions(13.4, 14.8031)
                .setConstraints(60, 60 , Math.toRadians(180), Math.toRadians(180), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(180)))
                                .lineToLinearHeading(rightPurplePixelRedCenter)
                                .lineToLinearHeading(rightBackdropRedCenter)
                                .forward(backingUpRed)
                                .lineToLinearHeading(parkingRed)
                                .build()
                );
        Pose2d leftPurplePixelBlueLeft = new Pose2d(15, 30, Math.toRadians(0));
        Pose2d leftPurplePixelBlueCenter = new Pose2d(10, 32, Math.toRadians(270));
        //  .turn(Math.toRadians(90))
        //  .back(10)
        Pose2d leftPurplePixelBlueRight = new Pose2d(10, 30, Math.toRadians(180));
        Pose2d leftBackdropBlueRight = new Pose2d(50, 30, Math.toRadians(180));
        Pose2d leftBackDropBlueLeft = new Pose2d(50, 42, Math.toRadians(180));
        Pose2d leftBackdropBlueCenter = new Pose2d(50, 36, Math.toRadians(180));
        //.back(10)
        //.strafeLeft(15)
        Pose2d leftSetUpCycleBlue = new Pose2d(0, 11, Math.toRadians(180));
        Pose2d leftBeginCycleBlue = new Pose2d(-61, 11.5, Math.toRadians(180));
        int backingUpBlue = 20;
        Pose2d parkingBlue= new Pose2d(60, 60 , Math.toRadians(90));

        RoadRunnerBotEntity blueAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(13.4, 14.8031)
                .setConstraints(60, 60 , Math.toRadians(180), Math.toRadians(90), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(17, 63, Math.toRadians(90)))
                                .lineToLinearHeading(leftPurplePixelBlueCenter)
                                .lineToLinearHeading(leftBackdropBlueCenter)
                                .forward(backingUpBlue)
                                .lineToLinearHeading(parkingBlue)
                                .build()
                );*/
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(blueAllianceLeft)
                //.addEntity(redAllianceRight)
                .addEntity(redAllianceLeft)
                .addEntity(redAllianceRight)
                .start();
    }
}