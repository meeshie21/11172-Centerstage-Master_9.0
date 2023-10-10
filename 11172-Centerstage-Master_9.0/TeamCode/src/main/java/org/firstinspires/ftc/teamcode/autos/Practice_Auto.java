package org.firstinspires.ftc.teamcode.autos;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.config.Slide;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class Practice_Auto extends LinearOpMode
{
    //penis

    @Override
    public void runOpMode() throws InterruptedException
    {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Slide slide = new Slide(hardwareMap);
        String path = "middle";

        TrajectorySequence middle = drive.trajectorySequenceBuilder(new Pose2d(-37.97, -61.48, Math.toRadians(90.00)))
                .lineToLinearHeading(new Pose2d(-35.87, -30.78, Math.toRadians(89.17)))
                .lineTo(new Vector2d(-36.39, -42.53))
                .lineToLinearHeading(new Pose2d(-60.07, -38.32, Math.toRadians(180.00)))
                .build();

        TrajectorySequence right = drive.trajectorySequenceBuilder(new Pose2d(-37.97, -61.48, Math.toRadians(90.00)))
                .lineToLinearHeading(new Pose2d(-37.45, -46.74, Math.toRadians(72.51)))
                .lineToLinearHeading(new Pose2d(-27.98, -36.04, Math.toRadians(30.00)))
                .lineTo(new Vector2d(-40.08, -42.01))
                .lineToLinearHeading(new Pose2d(-60.07, -38.32, Math.toRadians(180.00)))
                .build();

        TrajectorySequence left = drive.trajectorySequenceBuilder(new Pose2d(-37.97, -61.48, Math.toRadians(90.00)))
                .lineToLinearHeading(new Pose2d(-42.18, -32.71, Math.toRadians(89.17)))
                .lineTo(new Vector2d(-42.01, -45.87))
                .lineToLinearHeading(new Pose2d(-60.77, -36.39, Math.toRadians(180.00)))
                .build();

        TrajectorySequence park = drive.trajectorySequenceBuilder(new Pose2d(-60.77, -36.39, Math.toRadians(180.00)))
                .lineTo(new Vector2d(-41.28, -36.05))
                .lineTo(new Vector2d(-58.99, -60.62))
                .build();


        waitForStart();
        while(!isStarted())
        {
            if(gamepad1.dpad_left) path = "left";
            if(gamepad1.dpad_right) path = "right";
        }

        slide.middleClaw();
        sleep(500);


        drive.setPoseEstimate(middle.start());

        switch(path)
        {
            case "left":
                slide.setArmPos(0.35);
                drive.followTrajectorySequence(left);
                sleep(1000);
                slide.openClaw();

                drive.setPoseEstimate(park.start());
                drive.followTrajectorySequence(park);
                break;
            case "right":
                slide.setArmPos(0.35);
                drive.followTrajectorySequence(right);
                sleep(1000);
                slide.openClaw();

                drive.setPoseEstimate(park.start());
                drive.followTrajectorySequence(park);
                break;
            case "middle":
                slide.setArmPos(0.35);
                drive.followTrajectorySequence(middle);
                sleep(1000);
                slide.openClaw();

                drive.setPoseEstimate(park.start());
                drive.followTrajectorySequence(park);
                break;
        }


    }
}
