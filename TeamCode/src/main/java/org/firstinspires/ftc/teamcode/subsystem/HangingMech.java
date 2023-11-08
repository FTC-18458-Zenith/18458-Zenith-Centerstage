package org.firstinspires.ftc.teamcode.subsystem;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangingMech {
    private final DcMotor hangingMotor;
    private final Gamepad gamepad2;
    private final int hanging = 1000;

    public HangingMech(HardwareMap hardwareMap, Gamepad gamepad2) {
        hangingMotor = (DcMotor) hardwareMap.get("hangingMotor");
        this.gamepad2 = gamepad2;

        hangingMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        hangingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.setTargetPosition(0);
        hangingMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangingMotor.setPower(1);
    }
    public void teleOp() {
        if (gamepad2.left_bumper) hanging();
    }
    public void hanging() {
        hangingMotor.setTargetPosition(hanging);
    }
}
