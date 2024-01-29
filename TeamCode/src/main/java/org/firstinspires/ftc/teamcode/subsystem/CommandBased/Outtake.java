package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Outtake extends SubsystemBase {

    public static boolean REVERSED = true;

    public final static double holdingPose = 0,
                                scorePose = 0;

    Telemetry telemetry;

    private final ServoEx Outtake;

    public Outtake(final HardwareMap hMap, Telemetry telemetry) {
        this.Outtake = new SimpleServo(hMap, "Outtake", 0, 360);

        Outtake.setInverted(REVERSED);

        Outtake.setPosition(holdingPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Outtake", Outtake.getPosition());
    }

    public void score() {
        Outtake.setPosition(scorePose);
    }

    public void reset() {
        Outtake.setPosition(holdingPose);
    }

}
