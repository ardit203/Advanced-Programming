It is required to define an application for a simple file system in which objects that represent files (objects that implement the interface `IFile`) will be stored.

You must declare the interface `IFile` with the appropriate methods, so that every file has the following characteristics:

* it is possible to access its name (`String getFileName()`)
* it is possible to get its size as long (`long getFileSize()`)
* it is possible to get the String representation of the file (`String getFileInfo(???)`)
* it is possible to sort a folder if it contains a collection of files according to their sizes (`void sortBySize()`)
* it is possible to determine the size of the largest regular file in the folder (`findLargestFile()`)

There are two types of files: `File` (regular file) and `Folder` (directory/folder).
These two classes need to implement the interface `IFile`.

For a regular `File`, the information stored is its name and its size (as long).

In the class `Folder` the same information as in `File` is stored, and additionally a list of files (both regular files and directories).
For this class you must implement the following methods:

* `void addFile(IFile file)` – a method for adding any file to the list of files.
* If a file with the same name already exists in the directory being added to, an exception of type `FileNameExistsException` should be thrown, containing the name that already exists.

Both classes must implement the methods declared in the interface `IFile`. The following factors must be respected:

* the size of a `Folder` is the sum of the sizes of all files (regular or directories) found in it.
* when generating the String representation of directories, the files and subdirectories in that directory should be indented with a tab (`"\t"`).
* The String representation of a regular file is
  `File name [file_name aligned to the right with width 10] File size: [file size aligned to the right with width 10]`
* The String representation of a directory is
  `Folder name [folder_name aligned to the right with width 10] Folder size: [folder size aligned to the right with width 10]`
* it is possible to sort only the files inside a directory, so all files in that directory must be sorted by size in ascending order.
* the method `getLargestFile()` should return the size of the largest regular file within the directory where it is invoked.
* when the method `sortBySize()` is called on a directory, it should also be called on all its subdirectories.

A class `FileSystem` must be defined that will contain only one directory (`rootDirectory`).
For this class you must implement:

* default constructor `FileSystem()`
* `void addFile(IFile file)` – method for adding any file to the root directory.
* `long findLargestFile()` – method that returns the size of the largest regular file in the root directory.
* `void sortBySize()` – method that sorts the files in the root directory (and its subdirectories) according to their size in ascending order.
