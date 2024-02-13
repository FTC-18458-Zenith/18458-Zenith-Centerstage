package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Wrist extends SubsystemBase {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public final static double grabPose = 0,
            scorePose = 0.20846,
            resetPose = 0.85;
    Telemetry telemetry;

    private static ServoEx leftArmServo, rightArmServo;

    public Wrist(final HardwareMap hMap, Telemetry telemetry) {
        leftArmServo = new SimpleServo(hMap, "leftArmServo", 0, 360);
        rightArmServo = new SimpleServo(hMap, "rightArmServo", 0, 360);

        leftArmServo.setInverted(REVERSED);
        rightArmServo.setInverted(true);

        leftArmServo.setPosition(resetPose);
        rightArmServo.setPosition(resetPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("WristPose", leftArmServo.getPosition());
    }

    public void Score() {

        leftArmServo.setPosition(scorePose);
        rightArmServo.setPosition(scorePose);
    }

    public void Reset() {
        leftArmServo.setPosition(resetPose);
        rightArmServo.setPosition(resetPose);
    }

}
