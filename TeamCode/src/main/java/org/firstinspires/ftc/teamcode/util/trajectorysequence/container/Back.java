package org.firstinspires.ftc.teamcode.util.trajectorysequence.container;

import org.firstinspires.ftc.teamcode.opmode.command.Sensor.AutoSensor;

public class Back extends PathSegment {
    public volatile double distance;
    public Back(double distance) {
        this.distance = distance;
    }
}