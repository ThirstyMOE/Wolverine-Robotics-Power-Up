package com.team949.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSideLeftSwitch extends CommandGroup {

    public MiddleSideLeftSwitch() {
    	addSequential(new HardMove(0.5));
    	addSequential(new HardTurn(1.0, -90.0)); // TODO: Does ( - ) value actually wrap around?
    	addSequential(new HardMove(1.0));
    	addSequential(new HardTurn(1.0, 0.0)); // Face to move forward
    	addSequential(new HardMove(0.5));
    	addSequential(new HardTurn(1.0, 90.0)); // Face switch from left side
    	
    	addSequential(new HardMove(0.2)); // Move in flush with switch
    	
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
