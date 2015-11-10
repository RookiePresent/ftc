package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by aldas_000 on 9/13/2015.
 */
public class TeleOp extends OpMode {

  

    DcMotor arm;

   // DcMotor leftArm;
   // DcMotor rightArm;


    DcMotor leftDrive;
    DcMotor rightDrive;

    //TouchSensor touchSensor;
    //UltrasonicSensor ultrasonicSensor;

    @Override
    public void init() {

        //Gets references for motors/sensors from hardware map
      
        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        arm = hardwareMap.dcMotor.get("arm");


        //touchSensor = hardwareMap.touchSensor.get("touch");
        //ultrasonicSensor = hardwareMap.ultrasonicSensor.get("sonic");

        //Reverse the right motor arm, left drive
       // rightArm.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //leftTrack.setDirection(DcMotor.Direction.REVERSE);
    }


    double armPow;
    double drivePow;
    double sonicVal;

    @Override
    public void loop() {


        telemetry.addData("Drive Power: ", drivePow);
       // telemetry.addData("Ultrasonic Value: ", sonicVal);
        telemetry.addData("Arm Power: ", armPow);


        //Get the values from the gamepads.
        //Note: pushing the stick all the way up returns -1,
        //so we need to reverse the values.
        float stickL1 = gamepad1.left_stick_y;
        float stickR1 = gamepad1.right_stick_y;

        float stickL2 = -gamepad2.left_stick_y;
        float stickR2 = -gamepad2.right_stick_y;


           
      
       
       //Speed Control for arm, if we need it (buggy?)
/*
        if (gamepad1.dpad_left) {
            trackPow = 0.10;
        } else if (gamepad1.dpad_down) {
                trackPow = 0.30;
        } else if (gamepad1.dpad_right) {
            trackPow = 0.50;
        } else if (gamepad1.dpad_up) {
            trackPow = 1;
        }
*/

//For test purposes, we'll ignore variable speeds and just make sure everything works right
//This just sets the power to the value of the joystick, ranging from -1 to 1
        arm.setPower(stickL2);
        
      
      
      //Code might not work, meant as a backup if the variable code still gives me jittery movements
      //Don't think this allows us to go backwards - whatever, just for testing
      //Controller 1 is drive, 2 is gunner
left_drive.setPower(gamepad2.right_trigger);
right_drive.setPower(gamepad2.left_trigger);

        
        //Code for wheel speed control
        if (gamepad1.x) {
            drivePow = 0.10;
        } else if (gamepad1.a) {
            drivePow = 0.30;
        } else if (gamepad1.b) {
            drivePow = 0.50;
        } else if (gamepad1.y) {
            drivePow = 1;
        }
        //Moves left drive wheel with given speed chosen above
        if (stickL1 < 0) {
            leftDrive.setPower(-drivePow);
        } else if (stickL1 > 0) {
            leftDrive.setPower(drivePow);
        } else {
            leftDrive.setPower(0);
        }
        //Moves right drive wheel with given speed chosen above
        if (stickR1 < 0) {
            rightDrive.setPower(-drivePow);
        } else if (stickR1 > 0) {
            rightDrive.setPower(drivePow);
        } else {
            rightDrive.setPower(0);
        }


//Will display the value of an ultrasonic sensor when a touch sensor is pressed
//Reference code
        /*if(touchSensor.isPressed()) {
            sonicVal = ultrasonicSensor.getUltrasonicLevel();
        }*/
        //end of loop
    }
}

