package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

// configuration for drive constants
public final class DriveConfig {
    private DriveConfig() {}

    // names must match driver station
    public static final String FRONT_LEFT  = "front_left";
    public static final String FRONT_RIGHT = "front_right";
    public static final String BACK_LEFT   = "back_left";
    public static final String BACK_RIGHT  = "back_right";
    public static final String IMU_NAME    = "imu";

    // currently a guess; depends on results of wheel test
    public static final DcMotorSimple.Direction FRONT_LEFT_DIR  = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FRONT_RIGHT_DIR = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction BACK_LEFT_DIR   = DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction BACK_RIGHT_DIR  = DcMotorSimple.Direction.FORWARD;

    // defines control hub orientation; field-centric driving depends on this
    public static final RevHubOrientationOnRobot HUB_ORIENTATION = new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

    // driving experience
    public static final double SLOW_MODE_SPEED = 0.35; // speed with trigger fully pressed
    public static final double STRAFE_GAIN     = 1.1; // boost strafe to compensate for rollers slipping sideways

    // set up any given motor
    public static DcMotor initMotor(HardwareMap hw, String name, DcMotorSimple.Direction direction) {
        DcMotor m = hw.get(DcMotor.class, name);
        m.setDirection(direction);
        m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // raw power, no speed loop
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // stop when the sticks are released
        return m;
    }
}