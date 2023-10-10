package org.firstinspires.ftc.teamcode.config;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Slide {
    public DcMotor slide;
    public Servo arm, claw;

    public Slide(HardwareMap map) {
        slide = map.dcMotor.get("slide");
        claw = map.servo.get("claw");
        arm = map.servo.get("arm");
    }

    public void moveSlide() {

    }

    public void setSlide(double power) {
        slide.setDirection(Math.signum(power) == -1 ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        slide.setPower(power);
    }

    public void setArmPos(double position)
    {
        arm.setPosition(position);
    }

    public void openClaw() {claw.setPosition(0.3);}
    public void middleClaw() {claw.setPosition(0.18);}
    public void closeClaw() {claw.setPosition(0);}

}
