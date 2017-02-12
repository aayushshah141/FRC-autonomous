// assuming that 10m is 1.25 seconds at 0.3 speed
// assuming that 90deg is 1 sec at 0.3 speed
package org.usfirst.frc.team6024.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    public static VictorSP rearLeft, rearRight,frontLeft,frontRight;
    public static Joystick Logitech;

    public void moveSeconds(double x,double y,int time)
    {
    	long initTime = System.currentTimeMillis(); 
    	while(System.currentTimeMillis()-initTime <= time );
    	{
    		rearLeft.set(y);
        	frontLeft.set(x);
        	rearRight.set(x);
        	frontRight.set(y);

    	}
    }
    public void turnSeconds (boolean isRight,int degrees)
    {
    	int time = (degrees/90) * 1000;
    	long initTime = System.currentTimeMillis(); 
    	while(System.currentTimeMillis()-initTime <= time );
    	{
			if(isRight)
    		{
    			rearLeft.set(0.3);
            	frontLeft.set(0.3);
            	rearRight.set(-0.3);
            	frontRight.set(-0.3);
    		}
    		else
    		{
	    		rearLeft.set(-0.3);
	        	frontLeft.set(-0.3);
	        	rearRight.set(0.3);
	        	frontRight.set(0.3);
    		}
    	}
    }
    public void robotInit() {
        rearLeft = new VictorSP(3);
        rearRight = new VictorSP(4);
        frontLeft = new VictorSP(2);
        frontRight = new VictorSP(1);
        Logitech = new Joystick(0);
       
        
    }
    
	
    public void autonomousInit() {
    	moveSeconds(0.3,-0.3,1000);
    	moveSeconds(0,0,3000);
    	moveSeconds(-0.3,0.3,500);
/*    	moveSeconds(0.3,0.3,1000);
 * 		moveSeconds();
 * 	    
 */
    	
	}

   
    public void autonomousPeriodic() {
    	
    	
    }
    

    
    public void teleopPeriodic() {
    	double mult = 0.4;
    	double buffer = 0.1;
        double yaxis = Logitech.getRawAxis(1);
        double zaxis = Logitech.getRawAxis(2);
        boolean button1 = Logitech.getRawButton(1);
        boolean nitro = Logitech.getRawButton(2);
        if(button1)
        	brake();
        else if(zaxis>buffer || zaxis<-buffer)
        	move(zaxis*mult,-zaxis*mult);
        else if (yaxis>buffer || yaxis<-buffer)
        	if(nitro)
        		move(yaxis,yaxis);
        	else
        		move(yaxis*mult,yaxis*mult);
        else
        	brake();   
        move(yaxis*mult,yaxis*mult);
    }
    
   
    public void testPeriodic() {
    
    }
    
}
