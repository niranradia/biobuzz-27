package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Drive", group = "Drive")
public class DriveTeleOp extends OpMode {
    private DcMotor fl, fr, bl, br;
    private IMU imu;
    private boolean fieldCentric = false; // robot-centric until the imu is confirmed
    private boolean lastY = false;

    @Override
    public void init() {
        fl = DriveConfig.initMotor(hardwareMap, DriveConfig.FRONT_LEFT, DriveConfig.FRONT_LEFT_DIR);
        fr = DriveConfig.initMotor(hardwareMap, DriveConfig.FRONT_RIGHT, DriveConfig.FRONT_RIGHT_DIR);
        bl = DriveConfig.initMotor(hardwareMap, DriveConfig.BACK_LEFT, DriveConfig.BACK_LEFT_DIR);
        br = DriveConfig.initMotor(hardwareMap, DriveConfig.BACK_RIGHT, DriveConfig.BACK_RIGHT_DIR);

        imu = hardwareMap.get(IMU.class, DriveConfig.IMU_NAME);
        imu.initialize(new IMU.Parameters(DriveConfig.HUB_ORIENTATION));
        imu.resetYaw(); // heading 0 = the way the robot faces now
    }

    @Override
    public void loop() {
        if (gamepad1.y && !lastY) fieldCentric = !fieldCentric; // once per press
        lastY = gamepad1.y;
        if (gamepad1.back) imu.resetYaw(); // point the robot away from you first

        double speed = 1.0 - (1.0 - DriveConfig.SLOW_MODE_SPEED) * gamepad1.right_trigger;
        double forward = -gamepad1.left_stick_y * speed;
        double strafe  =  gamepad1.left_stick_x * speed;
        double turn    =  gamepad1.right_stick_x * speed;

        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        if (fieldCentric) {
            double[] r = MecanumMath.fieldToRobot(forward, strafe, heading);
            forward = r[0];
            strafe = r[1];
        }

        strafe *= DriveConfig.STRAFE_GAIN; // after rotation: boosts the robot's sideways axis
        double[] p = MecanumMath.wheelPowers(forward, strafe, turn);
        fl.setPower(p[0]);
        fr.setPower(p[1]);
        bl.setPower(p[2]);
        br.setPower(p[3]);

        telemetry.addData("Mode (Y)", fieldCentric ? "FIELD-centric" : "ROBOT-centric");
        telemetry.addData("Heading (deg)", "%.1f", Math.toDegrees(heading));
        telemetry.addData("Powers fl fr bl br", "%.2f %.2f %.2f %.2f", p[0], p[1], p[2], p[3]);
        telemetry.update();
    }
}