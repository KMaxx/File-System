package csci152.fss;

import csci152.adt.Queue;
import csci152.adt.Stack;
import csci152.impl.LinkedListStack;

public class FileSystem implements FileSystemInterface {
    
    private Stack<Command> history;
    private Folder root;
    private Folder currentFolder;
    
        public FileSystem(String rootName) {
            root = new Folder(rootName, null);
            currentFolder = root;
            history = new LinkedListStack();
        }

	@Override
	public void doCommand(Command cmd) {
            int size = currentFolder.getContents().getSize();
            switch(cmd.getCommandCode()) {
                case 1:
                    if(currentFolder.isNameInFolder(cmd.getName())) {
                        System.out.println("An item named '"+cmd.getName()+"' is already in the current folder '"+currentFolder.getName()+"'!");
                        break;
                    }
                    Folder fold = new Folder(cmd.getName(), currentFolder);
                    currentFolder.getContents().enqueue(fold);
                    history.push(cmd);
                    System.out.println("A new folder '"+fold.getName()+"' was successfully added to the current folder '"+currentFolder.getName()+"'.");
                    break;
                case 2:
                    if(currentFolder.isNameInFolder(cmd.getName())) {
                        System.out.println("An item named '"+cmd.getName()+"' is already in the current folder '"+currentFolder.getName()+"'!");
                        break;
                    }
                    Document doc = new Document(cmd.getName(), currentFolder);
                    currentFolder.getContents().enqueue(doc);
                    history.push(cmd);
                    System.out.println("A new document '"+doc.getName()+"' was successfully added to the current folder '"+currentFolder.getName()+"'.");
                    break;
                case 3:
                    if(!currentFolder.isNameInFolder(cmd.getName())) {
                        System.out.println("The folder named '"+cmd.getName()+"' does not exist in the current folder '"+currentFolder.getName()+"'!");
                        break;
                    }
                    for(; size>0; size--) {
                        try {
                            FolderOrDocument a = currentFolder.getContents().dequeue();
                            if(a.getName().compareTo(cmd.getName()) != 0) {
                                if(a.isFolder()) {
                                    Folder b = (Folder)a;
                                    currentFolder.getContents().enqueue(b);
                                } else {
                                    Document b = (Document)a;
                                    currentFolder.getContents().enqueue(b);
                                } 
                            } else {
                                if(!a.isFolder()) {
                                    Document b = (Document)a;
                                    currentFolder.getContents().enqueue(b);
                                    System.out.println("'"+a.getName()+"' is not a folder!");
                                    break; 
                                } 
                                Folder b = (Folder)a;
                                if(b.getContents().getSize() != 0) {
                                    currentFolder.getContents().enqueue(b);
                                    System.out.println("'"+a.getName()+"' is not an empty folder!");
                                    break;
                                }
                                history.push(cmd);
                                System.out.println("The empty folder named '"+b.getName()+"' was successfully removed from the current folder '"+currentFolder.getName()+"'.");
                                break;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }            
                    }
                    break;
                case 4:
                    if(!currentFolder.isNameInFolder(cmd.getName())) {
                        System.out.println("The document named '"+cmd.getName()+"' does not exist in the current folder '"+currentFolder.getName()+"'!");
                        break;
                    }
                    for(; size>0; size--) {
                        try {
                            FolderOrDocument a = currentFolder.getContents().dequeue();
                            if(a.getName().compareTo(cmd.getName()) != 0) {
                                if(a.isFolder()) {
                                    Folder b = (Folder)a;
                                    currentFolder.getContents().enqueue(b);
                                } else {
                                    Document b = (Document)a;
                                    currentFolder.getContents().enqueue(b);
                                } 
                            } else {
                                if(a.isFolder()) {
                                    Folder b = (Folder)a;
                                    currentFolder.getContents().enqueue(b);
                                    System.out.println("'"+a.getName()+"' is not a document!");
                                    break; 
                                } 
                                history.push(cmd);
                                System.out.println("The document named '"+a.getName()+"' was successfully removed from the current folder '"+currentFolder.getName()+"'.");
                                break;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }            
                    }
                    break;
                case 5:
                    if(currentFolder.compareTo(root) == 0) {
                        System.out.println("The current folder is the root folder! You cannot go up one folder!");
                        break;
                    }
                    cmd.setName(currentFolder.getName());
                    currentFolder = currentFolder.getParent();
                    history.push(cmd);
                    System.out.println("You successfully went up one folder to the folder named '"+currentFolder.getName()+"' from the folder '"+cmd.getName()+"'.");
                    break;
                case 6:
                    if(!currentFolder.isNameInFolder(cmd.getName())) {
                        System.out.println("The folder named '"+cmd.getName()+"' does not exist in the current folder '"+currentFolder.getName()+"'!");
                        break;
                    }
                    for(; size>0; size--) {
                        try {
                            FolderOrDocument a = currentFolder.getContents().dequeue();
                            if(a.getName().compareTo(cmd.getName()) != 0) {
                                if(a.isFolder()) {
                                    Folder b = (Folder)a;
                                    currentFolder.getContents().enqueue(b);
                                } else {
                                    Document b = (Document)a;
                                    currentFolder.getContents().enqueue(b);
                                } 
                            } else {
                                if(!a.isFolder()) {
                                    Document b = (Document)a;
                                    currentFolder.getContents().enqueue(b);
                                    System.out.println("'"+a.getName()+"' is not a folder!");
                                    break; 
                                } 
                                Folder b = (Folder)a;
                                currentFolder.getContents().enqueue(b);
                                currentFolder = b;
                                history.push(cmd);
                                System.out.println("You successfully went into the folder named '"+currentFolder.getName()+"' from the folder '"+currentFolder.getParent().getName()+"'.");
                                break;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }            
                    }
                    break;
            }
	}

	@Override
	public void undoLastCommand() {
            if(history.getSize() == 0) {
                System.out.println("No commands to undo");
            } else {
                try {
                    Command cmd = history.pop();
                    int size = currentFolder.getContents().getSize();
                    switch(cmd.getCommandCode()) {
                        case 1:
                            for(; size>1; size--) {
                                try {
                                    FolderOrDocument a = currentFolder.getContents().dequeue();
                                    if(a.isFolder()) {
                                        Folder b = (Folder)a;
                                        currentFolder.getContents().enqueue(b);
                                    } else {
                                        Document b = (Document)a;
                                        currentFolder.getContents().enqueue(b);
                                    } 
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }            
                            }
                            currentFolder.getContents().dequeue();
                            System.out.println("The command 'Make a folder named "+cmd.getName()+"' was successfully undone.");
                            break;
                        case 2:
                            for(; size>1; size--) {
                                try {
                                    FolderOrDocument a = currentFolder.getContents().dequeue();
                                        if(a.isFolder()) {
                                            Folder b = (Folder)a;
                                            currentFolder.getContents().enqueue(b);
                                        } else {
                                            Document b = (Document)a;
                                            currentFolder.getContents().enqueue(b);
                                        } 
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }            
                            }
                            currentFolder.getContents().dequeue();
                            System.out.println("The command 'Make a document named "+cmd.getName()+"' was successfully undone.");
                            break;
                        case 3:   
                            Folder fold = new Folder(cmd.getName(), currentFolder);
                            currentFolder.getContents().enqueue(fold);
                            System.out.println("The command 'Remove the empty folder named "+cmd.getName()+"' was successfully undone.");
                            break;
                        case 4:
                            Document doc = new Document(cmd.getName(), currentFolder);
                            currentFolder.getContents().enqueue(doc);
                            System.out.println("The command 'Remove the document named "+cmd.getName()+"' was successfully undone.");
                            break;
                        case 5:
                            for(; size>0; size--) {
                                try {
                                    FolderOrDocument a = currentFolder.getContents().dequeue();
                                    if(a.getName().compareTo(cmd.getName()) != 0) {
                                        if(a.isFolder()) {
                                            Folder b = (Folder)a;
                                            currentFolder.getContents().enqueue(b);
                                        } else {
                                            Document b = (Document)a;
                                            currentFolder.getContents().enqueue(b);
                                        } 
                                    } else {
                                        Folder b = (Folder)a;
                                        currentFolder.getContents().enqueue(b);
                                        currentFolder = b;
                                        break;
                                    }    
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }            
                            }
                            System.out.println("The command 'Go up one folder' was successfully undone."+" You went back into the folder named '"+currentFolder.getName()+"' from the folder '"+currentFolder.getParent().getName()+"'.");
                            break;
                        case 6:
                            currentFolder = currentFolder.getParent();
                            System.out.println("The command 'Go into folder' was successfully undone."+" You went back into the folder named '"+currentFolder.getName()+"' from the folder '"+cmd.getName()+"'.");
                            break;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
	}

	@Override
	public void listContents() {
            System.out.println(currentFolder.getContentNames());
	}

	@Override
	public Queue<String> getAllPaths() {
            return root.getAllPaths();
	}

}
