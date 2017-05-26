package csci152.fss;

import csci152.adt.Queue;

public class FSSTester {

	private int testCount;
	private int testsPassed;
	private FileSystemInterface fss;
	
	public FSSTester() {
		fss = new FileSystem("Drive");
		testCount = 0;
		testsPassed = 0;
	}
	
	public static void main(String[] args) throws Exception {
	
		FSSTester tester = new FSSTester();
		tester.runTests();
	}
	
	public void runTests() throws Exception {

		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.listContents();
		
		check(expected0);
		
		for (int i = 1; i <= 5; i++) {
			fss.doCommand(new Command(Command.MAKE_DOCUMENT, "file"+i));
		}
		fss.listContents();
		
		check(expected1);
		
		fss.doCommand(new Command(Command.MAKE_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.GO_INTO_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "insidefile"));
		fss.doCommand(new Command(Command.REMOVE_EMPTY_FODLER, "insidefile"));
		fss.doCommand(new Command(Command.REMOVE_EMPTY_FODLER, "mysteryfolder"));
		fss.listContents();
		
		check(expected2);
		
		fss.doCommand(new Command(Command.GO_INTO_FOLDER, "insidefile"));
		fss.doCommand(new Command(Command.GO_INTO_FOLDER, "notreallyhere"));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "insidefile2"));
		fss.listContents();
		
		check(expected3);
		
		fss.doCommand(new Command(Command.MAKE_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.GO_INTO_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "insidefile3"));
		fss.listContents();
		
		check(expected4);
		
		fss.doCommand(new Command(Command.GO_UP_ONE_FOLDER));
		fss.doCommand(new Command(Command.GO_UP_ONE_FOLDER));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "downer"));
		fss.doCommand(new Command(Command.GO_UP_ONE_FOLDER));
		fss.listContents();
		
		check(expected5);
		
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "upper"));
		fss.listContents();
		
		check(expected6);
		
		fss.doCommand(new Command(Command.GO_UP_ONE_FOLDER));
		fss.doCommand(new Command(Command.REMOVE_EMPTY_FODLER, "folder1"));
		fss.listContents();
		
		check(expected6);
		
		fss.doCommand(new Command(Command.GO_INTO_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.REMOVE_DOCUMENT, "upper"));
		fss.doCommand(new Command(Command.REMOVE_DOCUMENT, "nothere"));
		fss.doCommand(new Command(Command.REMOVE_DOCUMENT, "insidefile3"));
		fss.doCommand(new Command(Command.GO_UP_ONE_FOLDER));
		fss.doCommand(new Command(Command.REMOVE_DOCUMENT, "folder1"));
		fss.listContents();
		
		check(expected7);
		
		fss.doCommand(new Command(Command.REMOVE_EMPTY_FODLER, "folder1"));
		fss.listContents();
		
		check(expected8);
		
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.undoLastCommand();
		fss.listContents();
		
		check(expected6);
		
		for (char z = 'A'; z <= 'C'; z++) {
			fss.doCommand(new Command(Command.MAKE_FOLDER, "folder"+z));
			fss.doCommand(new Command(Command.GO_INTO_FOLDER, "folder"+z));
			fss.doCommand(new Command(Command.MAKE_DOCUMENT, "doc"+z));
		}
		fss.listContents();
		
		check(expected9);
		
		for (int i = 0; i < 9; i++) {
			fss.undoLastCommand();
		}
		fss.listContents();
		
		check(expected6);
		
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "insidefile"));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "folder1"));
		fss.doCommand(new Command(Command.MAKE_FOLDER, "insidefile"));
		fss.doCommand(new Command(Command.MAKE_FOLDER, "folder1"));
		fss.doCommand(new Command(Command.MAKE_DOCUMENT, "finishingup"));
		fss.listContents();
		
		check(expected10);
		
		for (int i = 0; i < 20; i++) {
			fss.undoLastCommand();
		}
		fss.listContents();
		
		check(expected0);
		
		showResults();
		
	}

	
	public String toStringAllPaths(FileSystemInterface fss) throws Exception {
		
		String result = new String();
		Queue<String> queue = fss.getAllPaths();
		while (queue.getSize() > 0) {
			result += queue.dequeue() + "\n";
		}
		
		return result;
	}
	
	public void check(String expected) throws Exception {
		
		testCount++;
		
		String actual = toStringAllPaths(fss);
		
		System.out.println("****************");
		System.out.print(actual);
		System.out.println("****************");
		
		if (actual.equals(expected)) {
			System.out.println("Test " + testCount + " passed");
			testsPassed++;
		} else {
			System.out.println("Test " + testCount + " failed");
		}
		System.out.println("****************");
	}
	
	public void showResults() {
		System.out.println("RESULTS");
		System.out.println("Tests Passed: " + testsPassed
				+" / "+ testCount);
	}
	
	public static String expected0 = "Drive\n";
	
	public static String expected1 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n";
	
	public static String expected2 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/insidefile\n";
	
	public static String expected3 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected4 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/folder1\n" +
			 "Drive/folder1/folder1/insidefile3\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected5 = 
			 "Drive\n" +
			 "Drive/downer\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/folder1\n" +
			 "Drive/folder1/folder1/insidefile3\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";

	public static String expected6 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/folder1\n" +
			 "Drive/folder1/folder1/insidefile3\n"+
			 "Drive/folder1/folder1/upper\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected7 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/folder1\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected8 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected9 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/folder1\n" +
			 "Drive/folder1/folder1/insidefile3\n"+
			 "Drive/folder1/folder1/upper\n"+
			 "Drive/folder1/folderA\n"+
			 "Drive/folder1/folderA/docA\n"+
			 "Drive/folder1/folderA/folderB\n"+
			 "Drive/folder1/folderA/folderB/docB\n"+
			 "Drive/folder1/folderA/folderB/folderC\n"+
			 "Drive/folder1/folderA/folderB/folderC/docC\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
	
	public static String expected10 = 
			 "Drive\n" +
			 "Drive/file1\n" +
			 "Drive/file2\n" +
			 "Drive/file3\n" +
			 "Drive/file4\n" +
			 "Drive/file5\n" +
			 "Drive/folder1\n" +
			 "Drive/folder1/finishingup\n" +
			 "Drive/folder1/folder1\n" +
			 "Drive/folder1/folder1/insidefile3\n"+
			 "Drive/folder1/folder1/upper\n"+
			 "Drive/folder1/insidefile\n"+
			 "Drive/folder1/insidefile2\n";
}

