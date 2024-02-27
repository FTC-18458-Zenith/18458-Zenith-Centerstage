package org.firstinspires.ftc.teamcode.subsystem.CommandBased;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class SlideV2 extends SubsystemBase {
    private final Telemetry telemetry;
    private final MotorEx leftSlide;
    private final MotorEx rightSlide;

    //Prob gonna have to mess with these values
    //Use https://www.ctrlaltftc.com/the-pid-controller
    public static PIDFCoefficients pidfUpCoefficients = new PIDFCoefficients(0.005, 0.00, 0,0);

    private PIDFController upController;
    private boolean slideAutomatic;

    public static double CPR = 537.7; //Counts Per Revolution (312 RPM)

    public double upSpeed = 1;
    public double downSpeed= -1;

    //Make sure to test positions with manual
    public static int restingPose = 0;
    public static int lowPose = 450;
    public static int midPose = 900;
    public static int highPose = 1780;
    double output = 0;

    public enum LiftPos{
        REST,
        LOW, MID, HIGH,
    }
    LiftPos liftPos;

    public SlideV2 ( HardwareMap hardwareMap,Telemetry telemetry) {

        leftSlide = new MotorEx(hardwareMap, "leftSlide");
        rightSlide = new MotorEx(hardwareMap, "rightSlide");

        rightSlide.setInverted(true);
        leftSlide.setInverted(true);

        leftSlide.resetEncoder();
        rightSlide.resetEncoder();

        leftSlide.setDistancePerPulse(360 / CPR);
        rightSlide.setDistancePerPulse(360 / CPR);

        upController = new PIDFController(pidfUpCoefficients.p, pidfUpCoefficients.i, pidfUpCoefficients.d, pidfUpCoefficients.f, getAngle(), getAngle());
        upController.setTolerance(10);

        this.telemetry = telemetry;
        slideAutomatic = false;
        liftPos = liftPos.REST;
    }

    @Override
    public void periodic() {
        if (slideAutomatic) {

            upController.setF(pidfUpCoefficients.f * Math.cos(Math.toRadians(upController.getSetPoint())));

            output = upController.calculate(getAngle());

            leftSlide.set(output);
            rightSlide.set(-output);

        }
        telemetry.addLine( "Slide Pose");
        telemetry.addData("Lift Motor Output", output);
        telemetry.addData("     Lift Left Power", leftSlide.getVelocity());
        telemetry.addData("     Lift Right Power:", rightSlide.getVelocity());

        telemetry.addData("     Left Encoder: ", leftSlide.getCurrentPosition());
        telemetry.addData("     Right Encoder: ", rightSlide.getCurrentPosition());
        telemetry.addData("     List Pos:", liftPos);
    }

    public void upSlideManual(){
        slideAutomatic = false;
        leftSlide.set(upSpeed);
        rightSlide.set(-upSpeed);
    }

    public void downSlideManual() {
        slideAutomatic = false;
        leftSlide.set(downSpeed);
        rightSlide.set(-downSpeed);
    }

    public void setPower(double power) {
        leftSlide.set(power);
        rightSlide.set(power);
    }

    public void stopSlide() {
        leftSlide.stopMotor();
        upController.setSetPoint(getAngle());
        rightSlide.stopMotor();
        slideAutomatic = false;
    }

    public double getAngle() {
        return leftSlide.getDistance();
    }

    //Lift Pose
    public void liftRest() {
        slideAutomatic = true;
        upController.setSetPoint(restingPose);
        liftPos = liftPos.REST;
    }

    public void encoderRecenter() {
        leftSlide.resetEncoder();
        rightSlide.resetEncoder();
        telemetry.addLine("ENCODER RESET");
    }

    public void liftLow() {
        slideAutomatic = true;
        upController.setSetPoint(lowPose);
        liftPos = liftPos.LOW;
    }

    public void liftMid() {
        slideAutomatic = true;
        upController.setSetPoint(midPose);
        liftPos = liftPos.MID;
    }

    public void liftHigh() {
        slideAutomatic = true;
        upController.setSetPoint(highPose);
        liftPos = liftPos.HIGH;
    }

    public void liftAuto() {
        slideAutomatic = true;
        upController.setSetPoint(1400);
        liftPos = liftPos.HIGH;
    }

    public void setPosition(double position) {
        upController.setSetPoint(position);
    }
    public double getPosition() {
        return upController.getSetPoint();
    }

}
