package org.usfirst.frc.team3559.robot;

import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;

// import org.usfirst.frc.team3559.robot.Gamepad;
 // robot drive


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private RobotDrive myRobot;
	Joystick left, right, lift;
	Talon winch;
	private Gyro gyro;
	int autoLoopCounter;

	double Kp = 0.03;
	double angle;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		myRobot = new RobotDrive(8, 9);
		left = new Joystick(0);
		right = new Joystick(1);
		winch = new Talon(0);
		
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		myRobot.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		
		CameraServer server = CameraServer.getInstance();
		server.setQuality(20);
		server.startAutomaticCapture("cam0");
	}
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autoLoopCounter = 0;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomous() {
		
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	public void teleopInit() {
		gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			winch.set(0.0);
			gyro.getAngle();
			myRobot.tankDrive(left, right);
			Timer.delay(0.01);
			while (left.getRawButton(1) == true){
				winch.set(-0.5);
			}
			while (right.getRawButton(1) == true){
				winch.set(0.3);
			}
			}
			
			
			
		}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
