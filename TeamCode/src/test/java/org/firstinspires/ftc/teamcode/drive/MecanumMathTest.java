package org.firstinspires.ftc.teamcode.drive;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MecanumMathTest {
    private static final double EPS = 1e-9;

    @Test
    public void forwardDrivesAllWheelsForward() {
        assertArrayEquals(new double[]{1, 1, 1, 1}, MecanumMath.wheelPowers(1, 0, 0), EPS);
    }

    @Test
    public void strafeRightDrivesOneDiagonalForward() {
        // fl and br forward, fr and bl backward
        assertArrayEquals(new double[]{1, -1, -1, 1}, MecanumMath.wheelPowers(0, 1, 0), EPS);
    }

    @Test
    public void clockwiseTurnDrivesLeftSideForward() {
        assertArrayEquals(new double[]{1, -1, 1, -1}, MecanumMath.wheelPowers(0, 0, 1), EPS);
    }

    @Test
    public void powersNeverExceedOne() {
        for (double f = -1; f <= 1; f += 0.25)
            for (double s = -1; s <= 1; s += 0.25)
                for (double t = -1; t <= 1; t += 0.25)
                    for (double p : MecanumMath.wheelPowers(f, s, t))
                        assertTrue(Math.abs(p) <= 1 + EPS);
    }

    @Test
    public void normalisingKeepsTheRatios() {
        // raw powers {2, 0, 0, 2} scale down to {1, 0, 0, 1}
        assertArrayEquals(new double[]{1, 0, 0, 1}, MecanumMath.wheelPowers(1, 1, 0), EPS);
    }

    @Test
    public void fieldCentricAtZeroHeadingChangesNothing() {
        assertArrayEquals(new double[]{0.3, -0.7}, MecanumMath.fieldToRobot(0.3, -0.7, 0), EPS);
    }

    @Test
    public void fieldCentricWhenFacingDriversLeft() {
        // Robot turned 90° anticlockwise: "away from the driver" is the robot's right.
        assertArrayEquals(new double[]{0, 1}, MecanumMath.fieldToRobot(1, 0, Math.PI / 2), EPS);
    }
}