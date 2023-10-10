package org.firstinspires.ftc.teamcode.teleops;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.Slide;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "NewSlideTest", group = "drive")
public class SlideTest2 extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {
        Slide slide = new Slide(hardwareMap);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        waitForStart();

        while(opModeIsActive() && !isStopRequested())
        {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad2.left_stick_y * 0.75,
                            -gamepad2.left_stick_x * 0.75,
                            -gamepad2.right_stick_x * 0.75
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();

            slide.setSlide(gamepad1.right_stick_y);
            if(gamepad1.right_trigger >= 0.5) slide.setArmPos(0.875);
            if(gamepad1.left_trigger >= 0.5) slide.setArmPos(0.65);
            if(gamepad1.right_stick_button) slide.setArmPos(0.17);
            if(gamepad1.left_stick_button) slide.setArmPos(0.19);
            if(gamepad1.right_bumper) slide.middleClaw();
            if(gamepad1.left_bumper) slide.openClaw();
        }
    }
}
