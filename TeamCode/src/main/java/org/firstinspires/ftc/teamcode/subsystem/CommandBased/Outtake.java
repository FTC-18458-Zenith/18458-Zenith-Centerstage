package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Outtake extends SubsystemBase {

    public static boolean REVERSED = true;

    public final static double holdingPose = 0.7,
                                scorePose = 0;

    Telemetry telemetry;

    private final ServoEx clampServo;

    public Outtake(final HardwareMap hMap, Telemetry telemetry) {
        this.clampServo = new SimpleServo(hMap, "Outtake", 0, 360);

        clampServo.setInverted(REVERSED);

        clampServo.setPosition(holdingPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Outtake", clampServo.getPosition());
    }

    public void score() {
        clampServo.setPosition(scorePose);
    }

    public void reset() {
        clampServo.setPosition(holdingPose);
    }

}
