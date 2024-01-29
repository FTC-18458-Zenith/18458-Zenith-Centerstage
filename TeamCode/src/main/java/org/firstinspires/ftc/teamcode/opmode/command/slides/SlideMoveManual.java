package org.firstinspires.ftc.teamcode.opmode.command.slides;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.CommandBased.SlideV2;


import java.util.function.Supplier;

public class SlideMoveManual extends CommandBase {
    private final SlideV2 SlideV2;
    private final Supplier<Double> doubleSupplier;
    public SlideMoveManual(SlideV2 SlideV2, Supplier<Double> doubleSupplier) {
        this.SlideV2 = SlideV2;
        this.doubleSupplier = doubleSupplier;
        addRequirements(SlideV2);
    }
    @Override
    public void execute() {
        double position = doubleSupplier.get();
        if (Math.abs(position) > 0.1) {
            SlideV2.setPosition(SlideV2.getPosition() + position * -25);
        }
    }
}