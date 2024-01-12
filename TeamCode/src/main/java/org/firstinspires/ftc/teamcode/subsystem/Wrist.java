package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Wrist extends SubsystemBase {

    public final static double grabPose = 0,
            scorePose = 0,
            resetPose = 0;
    Telemetry telemetry;

    private final ServoEx Wrist;

    public Wrist(final HardwareMap hMap, Telemetry telemetry) {
        this.Wrist = new SimpleServo(hMap, "Wrist", 0, 360);

        Wrist.setInverted(false);

        Wrist.setPosition(resetPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("WristPose", Wrist.getPosition());
    }

    public void Score() {
        Wrist.setPosition(scorePose);
    }

    public void Grab() {
        Wrist.setPosition(grabPose);
    }

}
