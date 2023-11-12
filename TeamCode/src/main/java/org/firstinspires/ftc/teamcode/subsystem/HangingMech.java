package org.firstinspires.ftc.teamcode.subsystem;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangingMech {
    private final DcMotor hangingMotor;
    private final Gamepad gamepad1;
    private final int hanging = 1000;
    private HardwareMap hardwareMap;

    public HangingMech(OpMode opMode) {
        this.hardwareMap = opMode.hardwareMap;
        this.gamepad1 = opMode.gamepad1;

        hangingMotor = (DcMotor) hardwareMap.get("hangingMotor");

        hangingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        hangingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.setTargetPosition(0);
        hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangingMotor.setPower(1);
    }
    public void teleOp() {
        if (gamepad1.left_bumper) hanging();
    }
    public void soloTeleOp() {
        if (gamepad1.dpad_up && gamepad1.dpad_down) hanging();
    }
    public void hanging() {
        hangingMotor.setTargetPosition(hanging);
    }
}
