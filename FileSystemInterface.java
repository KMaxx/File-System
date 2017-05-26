package csci152.fss;

import csci152.adt.Queue;


public interface FileSystemInterface {
	
	/**
	 * Executes the given command on the file system.  See the 
	 * instructions for details about how each individual command
	 * should to handled.
	 * 
	 * @param cmd - the command object to execute
	 */
	public void doCommand(Command cmd);
	
	/**
	 * Undoes the last command on the command stack.
	 */
	public void undoLastCommand();
	
	/**
	 * Prints the names of the folders and documents in the
	 * current folder
	 */
	public void listContents();
	
	/**
	 * Provides a listing of all documents and folders on 
	 * your file system, in the form of full pathnames starting
	 * from the root.  The pathnames will be listed in the queue in
	 * the order defined by the rules:
	 * 
	 * 1. If path1 represents a folder which contains the item
	 *    path2 somewhere down the hierarchy, then path1 < path2
	 *    
	 * 2. If path1 and path2 represent items that are directly
	 *    inside the same folder, and path1 comes before path2 in
	 *    lexicographic order, then path1 < path2 
	 * 
	 * @return a queue of the pathnames of all items on the file system
	 */
	public Queue<String> getAllPaths();
}
