package csci152.fss;


public class Command {
	
	/* 
	 * Integer used to represent the kind of command (1-8)
	 */
	private int cmdCode;
	
	/*
	 * Name of the folder or document argument to this command;
	 * will be initially null if the command doesn't need such a name
	 */
	private String name;
	
	/** 
	 * Integer constants used to represent the different kinds 
	 * of commands
	 */
	public static final int MAKE_FOLDER 		= 1;
	public static final int MAKE_DOCUMENT 		= 2;	
	public static final int REMOVE_EMPTY_FODLER     = 3;
	public static final int REMOVE_DOCUMENT 	= 4;	
	public static final int GO_UP_ONE_FOLDER 	= 5;
	public static final int GO_INTO_FOLDER 		= 6;
	

	/**
	 * Constructor for commands that should not take a name argument
	 * 
	 * @param cmdCode the integer command code
	 * @throws Exception if the command code is bad
	 */
	public Command(int cmdCode) throws Exception {
	
		if (cmdCode < 1 || cmdCode > 6) {
			throw new Exception("Illegal command code.");
		}
		
		if (needsName(cmdCode)) {
			throw new Exception("Given command code needs a name argument.");
		}
		
		this.cmdCode = cmdCode;
		this.name = null;
	}
	
	/**
	 * Constructor for commands that should take a name argument for 
	 * the folder or document
	 * 
	 * @param cmdCode the integer command code
	 * @param name argument for the command
	 * @throws Exception if the command code is bad
	 */
	public Command(int cmdCode, String name) throws Exception {
		
		if (cmdCode < 1 || cmdCode > 6) {
			throw new Exception("Illegal command code.");
		}
		
		if (name != null && !needsName(cmdCode)) {
			throw new Exception("Given command code should not have a name argument.");
		}
		
		this.cmdCode = cmdCode;
		this.name = name;
	}
	
	/**
	 * @return the integer command code
	 */
	public int getCommandCode() {
		return cmdCode;
	}
	
	/**
	 * @return the string argument for the command
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name to set to the string argument for the command
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Checks if the cmdCode represents a command that needs a name
	 * argument
	 */
	private boolean needsName(int cmdCode) {
		return (cmdCode == Command.MAKE_FOLDER) ||
				(cmdCode == Command.MAKE_DOCUMENT) ||
				(cmdCode == Command.REMOVE_EMPTY_FODLER) ||
				(cmdCode == Command.REMOVE_DOCUMENT) ||
				(cmdCode == Command.GO_INTO_FOLDER);
	}
	
	/**
	 * @return the string representation of the command
	 */
	public String toString() {
		return "Code:" + cmdCode + " NameArg:" + name;
	}
}
