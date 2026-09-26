package org.firstinspires.ftc.teamcode.drive;

// pure mecanum math with no imports
public final class MecanumMath {
    private MecanumMath() {}

    /**
     * wheel powers {fl, fr, bl, br}, each in [-1, 1].
     * forward: + forward; strafe: + right; turn: + clockwise.
     */
    public static double[] wheelPowers(double forward, double strafe, double turn) {
        double fl = forward + strafe + turn;
        double fr = forward - strafe - turn;
        double bl = forward - strafe + turn;
        double br = forward + strafe - turn;
        double d = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1.0);
        return new double[]{fl / d, fr / d, bl / d, br / d};
    }

    /**
     * field-centric: turns a driver-frame command into a robot-frame one, {forward, strafe}.
     * heading: IMU yaw in radians, anticlockwise positive, 0 = facing away from the driver.
     */
    public static double[] fieldToRobot(double forward, double strafe, double heading) {
        double cos = Math.cos(heading), sin = Math.sin(heading);
        return new double[]{
                forward * cos - strafe * sin,
                forward * sin + strafe * cos
        };
    }
}