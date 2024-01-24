package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Drone extends SubsystemBase {

    public static boolean REVERSED = true;

    public final static double holdingPose = 0,
            scorePose = 0;

    Telemetry telemetry;

    private final ServoEx Drone;

    public Drone(final HardwareMap hMap, Telemetry telemetry) {
        this.Drone = new SimpleServo(hMap, "Drone", 0, 360);

        Drone.setInverted(REVERSED);

        Drone.setPosition(holdingPose);

        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addData("Drone", Drone.getPosition());
    }

    public void score() {
        Drone.setPosition(scorePose);
    }

    public void reset() {
        Drone.setPosition(holdingPose);
    }

}
