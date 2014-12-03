/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.jenks.test;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot 
{
    boolean beenInit = false;
    RobotDrive mainDrive;
    CANJaguar jag2;
    CANJaguar jag3;
    Joystick joystick1;
    Joystick joystick2;
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    private void init()
    {
        joystick1 = new Joystick(1);
        joystick2 = new Joystick(2);
        
        try 
        {
        jag2 = new CANJaguar(2);
        jag3 = new CANJaguar(3);
        
        }
        catch (CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
        
        mainDrive = new RobotDrive  (jag2,jag3);
        mainDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        
        beenInit = true;
    }
    public void autonomous() 
    {
        System.out.println("I'M IN AUTONOMOUSSSSS~");
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() 
    {
        if(!beenInit){
            init();
        }
        
        while (isOperatorControl() && isEnabled())
        {
            mainDrive.tankDrive(joystick1, joystick2);
            if (joystick1.getRawButton(1))
            {
                System.out.println("IMMA FIRING MAH LAZERRR");
                mainDrive.drive(1, 0);
            }
        
            if (joystick2.getRawButton(1))
            {
                System.out.println("WRONG LAZER!!!!!");
                mainDrive.drive(-1, 0);
            }
        }
        

        
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() 
    {
        
    
    }
}
