# **First Midterm Exam (+ composite design pattern)**

You need to define an application for a simple file system in which objects representing files/directories will be stored (objects that implement the interface `IFile`).

The interface `IFile` should be declared with the appropriate methods so that every file/directory has the following characteristics:

* it can access its name (`String getFileName()`)
* its size can be obtained (`long getFileSize()`)
* it can return a String representation of the file (`String getFileInfo()`)
* it can sort the collection of files inside it based on their sizes (`void sortBySize()`)
* it can find the largest file/directory inside it (`findLargestFile()`)

There are two types of file objects: `File` (regular file) and `Folder` (directory/folder). Both classes should implement the `IFile` interface.

A `File` stores information about its name and size (`long`).

The class `Folder` stores the same information as `File`, plus a list of files (both regular files and directories). For this class the following methods must be implemented:

* `void addFile(IFile file)` – a method that adds any file to the list.
* If a file with the same name already exists, the method should throw a `FileNameExistsException`, passing the conflicting file name.

Both classes must implement the methods declared in the `IFile` interface. The following must be respected:

* The size of a `Folder` is the sum of the sizes of all files (regular or directories) inside it.
* When generating the String representation for a directory, files and subdirectories inside that directory must be indented with a tab (`"\t"`).
* The String representation of a regular file is:
  `File name [file name left-padded to 10 characters] File size: [file size right-padded to 10 characters]`
* The String representation of a directory is:
  `Folder name [folder name left-padded to 10 characters] Folder size: [folder size right-padded to 10 characters]`
* Sorting is allowed only within the directory where the method is called, and files should be sorted by size in ascending order.
* The method `getLargestFile()` should return the largest regular file within the directory from which it is invoked.
* When `sortBySize()` is called on a directory, it should also call this method recursively on all its subdirectories.

A class `FileSystem` should be defined, which stores one root directory (`rootDirectory`). This class should implement:

* a default constructor `FileSystem()`
* `void addFile(IFile file)` – adds any file to the root directory
* `long findLargestFile()` – returns the size of the largest regular file within the root directory
* `void sortBySize()` – sorts all files (regular and directories) within the root directory by their sizes in ascending order