package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by aldas_000 on 9/13/2015.
 */
public class TeleOp extends OpMode {

    /* Needed:
    4 DC motors - drive and track
    variable speed
    no sensors

    drive motors and tracks get variable speed
     */


    DcMotor arm;

   // DcMotor leftArm;
   // DcMotor rightArm;

    //DcMotor leftTrack;
    //DcMotor rightTrack;

    DcMotor leftDrive;
    DcMotor rightDrive;

    //TouchSensor touchSensor;
    //UltrasonicSensor ultrasonicSensor;

    @Override
    public void init() {

        //Gets references for motors/sensors from hardware map
       // leftArm = hardwareMap.dcMotor.get("left_arm");
       // rightArm = hardwareMap.dcMotor.get("right_arm");

        leftDrive = hardwareMap.dcMotor.get("left_drive");
        rightDrive = hardwareMap.dcMotor.get("right_drive");

        arm = hardwareMap.dcMotor.get("arm");

        //leftTrack = hardwareMap.dcMotor.get("left_track");
        //rightTrack = hardwareMap.dcMotor.get("right_track");

        //touchSensor = hardwareMap.touchSensor.get("touch");
        //ultrasonicSensor = hardwareMap.ultrasonicSensor.get("sonic");

        //Reverse the right motor arm, left drive
       // rightArm.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //leftTrack.setDirection(DcMotor.Direction.REVERSE);
    }


    double trackPow;
    double drivePow;
    double sonicVal;

    @Override
    public void loop() {


        telemetry.addData("Drive Power: ", drivePow);
        telemetry.addData("Ultrasonic Value: ", sonicVal);
        telemetry.addData("Track Power: ", trackPow);


        //Get the values from the gamepads.
        //Note: pushing the stick all the way up returns -1,
        //so we need to reverse the values.
        float stickL1 = gamepad1.left_stick_y;
        float stickR1 = gamepad1.right_stick_y;

        float stickL2 = -gamepad2.left_stick_y;
        float stickR2 = -gamepad2.right_stick_y;


            //set the power of the motor with the game pad values
       // leftArm.setPower(stickL2);
       // rightArm.setPower(stickR2);

        if (gamepad1.dpad_left) {
            trackPow = 0.10;
        } else if (gamepad1.dpad_down) {
                trackPow = 0.30;
        } else if (gamepad1.dpad_right) {
            trackPow = 0.50;
        } else if (gamepad1.dpad_up) {
            trackPow = 1;
        }



        if (gamepad2.right_stick_y > 0) {
            arm.setPower(trackPow);
        }

        if (gamepad2.right_stick_y < 0) {
            arm.setPower(-trackPow);
        }

       /* if (gamepad1.right_trigger > 0) {
            rightTrack.setPower(trackPow);
        } else if (gamepad1.left_bumper) {
            rightTrack.setPower(-trackPow);
        }*/

        //Code for speed control

        // Choosing a speed with a button (x,a,b,y)
        if (gamepad1.x) {
            drivePow = 0.10;
        } else if (gamepad1.a) {
            drivePow = 0.30;
        } else if (gamepad1.b) {
            drivePow = 0.50;
        } else if (gamepad1.y) {
            drivePow = 1;
        }
        //Code for moving left drive wheel with given speed chosen below below
        if (stickL1 < 0) {
            leftDrive.setPower(-drivePow);
        } else if (stickL1 > 0) {
            leftDrive.setPower(drivePow);
        } else {
            leftDrive.setPower(0);
        }
        //Code for moving right drive wheel with given speed chosen below
        if (stickR1 < 0) {
            rightDrive.setPower(-drivePow);
        } else if (stickR1 > 0) {
            rightDrive.setPower(drivePow);
        } else {
            rightDrive.setPower(0);
        }

        /*if(touchSensor.isPressed()) {
            sonicVal = ultrasonicSensor.getUltrasonicLevel();
        }*/
        //end of loop
    }
}

