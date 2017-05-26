package csci152.fss;

public class Document extends FolderOrDocument {

	protected Document(String name, Folder parent) {
		super(name, parent);
	}

	@Override
	public boolean isFolder() {
		return false;
	}

}
