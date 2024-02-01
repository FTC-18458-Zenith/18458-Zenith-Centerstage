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

        Pose2d scorePose = new Pose2d(47, -39, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(16,16)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40, -63, Math.toRadians(180)))
                                .waitSeconds(10)
                                .lineToLinearHeading(RightPose)
                                .lineToLinearHeading(ResetPose)
                                //.waitSeconds(10)
                                .back(82)
                                .lineToLinearHeading(scorePose)
                                .strafeLeft(25)
                                .build()

                );

        Pose2d resetPose = new Pose2d(35, -35, Math.toRadians(180));

        Pose2d rightPose = new Pose2d(32, -30, Math.toRadians(180));
        Pose2d leftPose = new Pose2d(10, -30, Math.toRadians(180));
        Pose2d midPose = new Pose2d(24, -24, Math.toRadians(180));


        RoadRunnerBotEntity redAllianceRight = new DefaultBotBuilder(meepMeep)
                //TODO: MAKE AUTO PARK IN THE CORNER
                .setColorScheme((new ColorSchemeRedLight()))
                .setDimensions(16, 16)
                .setConstraints(30, 40 , Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(7, -63, Math.toRadians(180)))
                                .back(20)
                                .lineToLinearHeading(resetPose)
                                .lineToLinearHeading(leftPose)
                                .lineToLinearHeading(scorePose)

                                .strafeRight(28)
                                .back(10)
                                .build()
                );

        Pose2d blueRightPose = new Pose2d(-36, 30, Math.toRadians(180));
        Pose2d blueResetPose = new Pose2d(-36,60, Math.toRadians(180));
        Pose2d blueBackdropPose = new Pose2d(47, 60, Math.toRadians(180));
        Pose2d blueRightScoringPose = new Pose2d(47, 28, Math.toRadians(180));

        RoadRunnerBotEntity blueAllianceRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueDark()))
                .setDimensions(16,16)
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-30, 64, Math.toRadians(0)))
                                .lineToLinearHeading(blueRightPose)
                                .lineToLinearHeading(blueResetPose)
                                .lineToLinearHeading(blueBackdropPose)
                                .lineToLinearHeading(blueRightScoringPose)
                                .build()
                );
        Pose2d leftPurplePixelBlueCenter = new Pose2d(10, 32, Math.toRadians(270));
        Pose2d leftBackdropBlueCenter = new Pose2d(50, 36, Math.toRadians(180));
        int backingUpBlue = 20;
        Pose2d parkingBlue= new Pose2d(60, 60 , Math.toRadians(90));

        RoadRunnerBotEntity blueAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeBlueLight()))
                .setDimensions(16, 16)
                .setConstraints(30, 40 , Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(driveShim ->
                        driveShim.trajectorySequenceBuilder(new Pose2d(17, 63, Math.toRadians(90)))
                                .lineToLinearHeading(leftPurplePixelBlueCenter)
                                .lineToLinearHeading(leftBackdropBlueCenter)
                                .forward(backingUpBlue)
                                .lineToLinearHeading(parkingBlue)
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAllianceLeft)
                .addEntity(blueAllianceRight)
                .start();
    }
}