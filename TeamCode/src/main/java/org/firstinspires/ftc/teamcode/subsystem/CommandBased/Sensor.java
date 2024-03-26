package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.arcrobotics.ftclib.hardware.SensorDistance;
import com.arcrobotics.ftclib.hardware.SensorDistanceEx;
import com.arcrobotics.ftclib.hardware.SensorRevTOFDistance;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.List;
import java.util.Map;

public interface Sensor extends SensorDistance {
    class Distance {
        private DistanceUnit unit;
        private double target;
        private double threshold;
        private String name;
        private DistanceUnit distanceUnit;
        public Distance(DistanceUnit unit, double target) {
            this(unit, target, 5);
        }
        public Distance(DistanceUnit unit, double target, double threshold) {
            this(unit, target, threshold, "Distance Target");
        }
        public Distance(DistanceUnit unit, double target, double threshold, String name) {
            this.unit = unit;
            this.target = target;
            this.threshold = threshold;
            this.name = name;
        }
        public boolean atTarget(double currentDistance) {
            currentDistance = unit.fromUnit(unit, currentDistance);
            double clippedRange = Range.clip(currentDistance, currentDistance - threshold, currentDistance + threshold);
            if (clippedRange >= currentDistance + threshold)
                return false;
            else return !(clippedRange <= currentDistance + threshold);
        }
        public void setTarget(double target) {
            this.target = target;
        }
        public void setUnit(DistanceUnit unit) {
            this.target = unit.fromUnit(this.unit, target);
            this.threshold = unit.fromUnit(this.unit, threshold);
            this.unit = unit;
        }
        public void setName(String name) {
            this.name = name;
        }
        public DistanceUnit getUnit() {
            return unit;
        }
        public double getThreshold() {
            return threshold;
        }
        public double getTarget() {
            return target;
        }
        public String getName() {
            return this.name;
        }
    }
    boolean targetReached(SensorRevTOFDistance.DistanceTarget target);
    void addTarget(SensorRevTOFDistance.DistanceTarget target);
    void addTargets(List<SensorRevTOFDistance.DistanceTarget> targets);
    Map<SensorRevTOFDistance.DistanceTarget, Boolean> checkAllTargets();
}
