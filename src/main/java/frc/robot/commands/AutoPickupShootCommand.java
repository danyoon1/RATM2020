/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoPickupShootCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutoPickupShootCommand.
   */
  public AutoPickupShootCommand(DriveTrain driveTrain, Intake intake, Shooter shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
        new DriveDistanceCommand(0.5, 0.5, 160, driveTrain)
        .raceWith(new IntakeCommand(Constants.Intake.INTAKE_SPEED, 
        Constants.Intake.CONVEYOR_SPEED, intake)),
        new DriveDistanceCommand(-0.5, -0.5, -73.37, driveTrain),
        new DriveTurnCommand(17.948, driveTrain),
        new ShootCommandGroup(intake, 0.8, shooter).withTimeout(6),
        new DriveTurnCommand(-17.948, driveTrain),
        new DriveDistanceCommand(-0.5, -0.5, 114, driveTrain)
        .raceWith(new IntakeCommand(Constants.Intake.INTAKE_SPEED,
        Constants.Intake.CONVEYOR_SPEED, intake))
    );
  }
}
