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

        Pose2d LeftPose = new Pose2d(-47, -35, Math.toRadians(270));
        Pose2d MidPose = new Pose2d(-35, -32, Math.toRadians(90));
        Pose2d RightPose = new Pose2d(-34, -34, Math.toRadians(180));

        Pose2d ResetPose = new Pose2d(-35, -60, Math.toRadians(180));
        Pose2d redScorePose = new Pose2d(47, -39, Math.toRadians(180));

        RoadRunnerBotEntity redAllianceLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme((new ColorSchemeRedDark()))
                .setDimensions(16, 16)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 40, Math.toRadians(180), Math.toRadians(270), 11.15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40, -63, Math.toRadians(180)))
                                .waitSeconds(10)
                                .lineToLinearHeading(MidPose)
                                .lineToLinearHeading(ResetPose)
                                .back(82)
                                .lineToLinearHeading(redScorePose)
                                .strafeLeft(25)
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redAllianceLeft)
                .start();
    }
}
