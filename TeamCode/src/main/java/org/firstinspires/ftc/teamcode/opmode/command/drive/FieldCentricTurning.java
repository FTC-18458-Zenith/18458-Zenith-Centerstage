//package org.firstinspires.ftc.teamcode.opmode.command.drive;
//
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.arcrobotics.ftclib.gamepad.GamepadKeys;
//import com.qualcomm.robotcore.hardware.IMU;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.teamcode.subsystem.DriveSub.Drivetrain;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
//public class FieldCentricTurning extends CommandBase {
//    private Drivetrain drive;
//    private GamepadEx driverGamepad;
//    public static double MIN_HEADING_P = 0.05;
//    public static double MAX_HEADING_P = 1.0;
//    public static double STICK_THRESHOLD = 0.05;
//    private IMU imu;
//    double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
//    protected double multiplier;
//    boolean isFieldCentric;
//
//    public FieldCentricTurning(Drivetrain drive, GamepadEx driverGamepad, boolean isFieldCentric) {
//
//        this.drive = drive;
//        this.driverGamepad = driverGamepad;
//
//        this.multiplier = 1.0;
//        addRequirements(this.drive);
//
//        this.isFieldCentric = isFieldCentric;
//    }
//
//    @Override
//    public void execute() {
//        if(driverGamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
//            multiplier = 0.3;
//        } else {
//            multiplier = 1;
//        }
//        if(isFieldCentric) {
//            drive.fieldCentric(
//                    driverGamepad.getLeftY() * multiplier,
//                    driverGamepad.getLeftX() * multiplier,
//                    -driverGamepad.getRightX() * multiplier
//            );
//        } else {
//            drive.mecDrive(
//                    driverGamepad.getLeftY() * multiplier, //Removed - from drivergamepad
//                    driverGamepad.getLeftX() * multiplier,
//                    driverGamepad.getRightX() * multiplier //Changed from -driverGamepad.getLeftY(), so the drive mturns right
//            );
//            if (driverGamepad.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON) && driverGamepad.getButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
//                Vector2d turn2d = new Vector2d(rightX, rightY);
//                double stickHeading = turn2d
//                        .rotated(Math.PI / 2) // Rotate by Pi / 2 Radians (90 degrees) to move from right to top
//                        .angle() - Math.PI; // Subtract Pi radians (180 degrees) from final angle This makes the range -PI through PI instead of 0 through 2PI
//
//                // Find the lowest of the 3 coterminal angles
//                double defaultHeadingError = (botHeading - stickHeading); // Default angle
//                double lowHeadingError = defaultHeadingError - (Math.PI * 2); // Low coterminal angle (360 degrees off from original)
//                double highHeadingError = defaultHeadingError + (Math.PI * 2); // High coterminal angle (360 degrees off from original)
//
//                // Create map of the absolute value of each value to the original value
//                double headingError = Arrays.stream(new Double[]{
//                                defaultHeadingError,
//                                lowHeadingError,
//                                highHeadingError
//                        })
//                        .min(Comparator.comparingDouble(Math::abs)).get();
//
//                double distance = turn2d.distTo(new Vector2d(0,0));
//
//
//                double headingP = MIN_HEADING_P + ((MAX_HEADING_P - MIN_HEADING_P) * distance);
//                turn = headingError * headingP;
//
//                if (distance < STICK_THRESHOLD) turn = 0;
//            }
//        }
//    }
//    @Override
//    public void end(boolean interrupted) {
//        drive.stop();
//    }
//}
