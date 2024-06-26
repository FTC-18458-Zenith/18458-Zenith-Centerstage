package org.firstinspires.ftc.teamcode.subsystem.Vision;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class BlueCLoseVision extends SubsystemBase {
    private final Telemetry telemetry;
    private final FFRectDetector duckDetector;
    private TeamMarkerPipeline.FFPosition finalPos;

    public BlueCLoseVision(HardwareMap hw, Telemetry tl) {
        duckDetector = new FFRectDetector(hw, tl);
        duckDetector.init();
//y-
//y+

        //x- <---> x+

        duckDetector.setLeftRectangle(0.2, 0.4);
        duckDetector.setCenterRectangle(0.6, 0.4);
        duckDetector.setRightRectangle(.9, 0.7);
        duckDetector.setRectangleSize(40,50);
        telemetry = tl;
//        currentPos = duckDetector.getPosition();
    }


    @Override
    public void periodic() {
//        currentPos = duckDetector.getPosition();
//        telemetry.addData("Position", duckDetector.getPosition());

        telemetry.addData("Current Position", getPosition());
        telemetry.addData("Final Position", getFinalPosition());

    }

    public TeamMarkerPipeline.FFPosition getPosition() {
        return duckDetector.getPosition();
    }
    public void setPosition(TeamMarkerPipeline.FFPosition position) {
        finalPos = position;
    }

    public TeamMarkerPipeline.FFPosition getFinalPosition() {
        return finalPos;
    }

}