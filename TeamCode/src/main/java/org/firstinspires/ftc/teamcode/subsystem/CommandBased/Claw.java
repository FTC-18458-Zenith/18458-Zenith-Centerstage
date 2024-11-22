package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw extends SubsystemBase {

    // testing to see if you can change booleans
    public static boolean REVERSED = false;

    public static double grabPose = 0,
            scorePose = 0.225;

    public static double resetPose = 0;
    Telemetry telemetry;
    private static ServoEx Claw;


    public Claw(final HardwareMap hMap, Telemetry telemetry) {
        Claw = new SimpleServo(hMap, "Claw", 0, 360);

        Claw.setInverted(false);

        Claw.setPosition(resetPose);

        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        telemetry.addData("ClawPose", Claw.getPosition());
    }

    public void Score() {
        Claw.setPosition(scorePose);
    }

    public void Reset() {
        Claw.setPosition(resetPose);
    }
}
