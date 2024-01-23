package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Claw_V2 extends SubsystemBase {

    public final static double Close = 0.59,
                                Open = 0.42;

    Telemetry telemetry;

    private final ServoEx Claw;

    public Claw_V2(Telemetry telemetry, HardwareMap hw) {
        this.Claw = new SimpleServo(hw, "Claw", 0 , 360);
        this.Claw.setPosition(Open);
        this.Claw.setInverted(true);

        this.telemetry = telemetry;
    }
}
