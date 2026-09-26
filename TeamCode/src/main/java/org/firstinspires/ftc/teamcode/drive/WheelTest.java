package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Wheel Test", group = "Drive")
public class WheelTest extends OpMode {
    private static final double POWER = 0.3;
    private DcMotor fl, fr, bl, br;

    @Override
    public void init() {
        fl = DriveConfig.initMotor(hardwareMap, DriveConfig.FRONT_LEFT, DriveConfig.FRONT_LEFT_DIR);
        fr = DriveConfig.initMotor(hardwareMap, DriveConfig.FRONT_RIGHT, DriveConfig.FRONT_RIGHT_DIR);
        bl = DriveConfig.initMotor(hardwareMap, DriveConfig.BACK_LEFT, DriveConfig.BACK_LEFT_DIR);
        br = DriveConfig.initMotor(hardwareMap, DriveConfig.BACK_RIGHT, DriveConfig.BACK_RIGHT_DIR);
    }

    @Override
    public void loop() {
        // X = front left, Y = front right, A = back left, B = back right.
        fl.setPower(gamepad1.x ? POWER : 0);
        fr.setPower(gamepad1.y ? POWER : 0);
        bl.setPower(gamepad1.a ? POWER : 0);
        br.setPower(gamepad1.b ? POWER : 0);

        telemetry.addLine("Wheels OFF the floor. Hold one button at a time.");
        telemetry.addLine("The top of the wheel should move towards the robot's FRONT.");
        telemetry.addData("X  " + DriveConfig.FRONT_LEFT, gamepad1.x ? "SPINNING" : "-");
        telemetry.addData("Y  " + DriveConfig.FRONT_RIGHT, gamepad1.y ? "SPINNING" : "-");
        telemetry.addData("A  " + DriveConfig.BACK_LEFT, gamepad1.a ? "SPINNING" : "-");
        telemetry.addData("B  " + DriveConfig.BACK_RIGHT, gamepad1.b ? "SPINNING" : "-");
        telemetry.update();
    }
}