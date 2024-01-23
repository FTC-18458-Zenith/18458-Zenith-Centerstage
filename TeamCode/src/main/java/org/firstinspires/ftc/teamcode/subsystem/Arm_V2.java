package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Arm_V2 extends SubsystemBase {

    public static boolean REVERSED = true;

    public final static double grabPose = 0.19,
                                scorePose = 0.5,
                                resetPose = 1;
    Telemetry telemetry;

    private final ServoEx leftArmServo, rightArmServo;

    public Arm_V2(final HardwareMap hMap, Telemetry telemetry) {
        this.leftArmServo = new SimpleServo(hMap, "leftArmServo", 0, 360);
        this.rightArmServo = new SimpleServo(hMap, "rightArmServo", 0, 360);

        leftArmServo.setInverted(REVERSED);

        leftArmServo.setPosition(resetPose);
        rightArmServo.setPosition(resetPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("ArmPose", leftArmServo.getPosition());
    }

    public void Score() {
        leftArmServo.setPosition(scorePose);
        rightArmServo.setPosition(scorePose);
    }

    public void Grab() {
        leftArmServo.setPosition(grabPose);
        rightArmServo.setPosition(grabPose);
    }

}
