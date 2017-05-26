package csci152.fss;


public abstract class FolderOrDocument 
						implements Comparable<FolderOrDocument> {

	private String name;
	private Folder parent;
	
	protected FolderOrDocument(String name, Folder parent) {
		this.name = name;
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public Folder getParent() {
		return parent;
	}
	
	public int compareTo(FolderOrDocument other) {
		return this.name.compareTo(other.getName());
	}

	public abstract boolean isFolder();
	
}
