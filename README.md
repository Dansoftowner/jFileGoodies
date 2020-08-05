# jFileGoodies
<p><b>Special file utilities for java</b></p>

A set of classes and utilities for files that are probably not available in other utility-libraries and might help you in your project.

This library includes:
 * a `FileGoodies` class that provides a lot of special utilities 
 * a `FormattedFile` class that adds more functionality to the base `java.io.File` class
 * a `FileExplorer` API that lets you to call the native GUI file explorer from java on Windows, Mac and Linux.
 * ...and more to come!

If you have become interested let's jump in to the [User Guide](#user-guide)!

## <a name="user-guide"></a> User Guide
1. [Installation](#installation)
   * [Using maven](#installation-maven)
   * [Using gradle](#installation-gradle)
2. [Using jFileGoodies](#using-library)
   * [The FileGoodies class](#FileGoodies-class)
      * [Executable file-extensions](#executable-file-ext)
      * [Shorting file-paths](#file-path-short)
   * [The FormattedFile class](#FormattedFile-class)
      *
      *
      *
   * [The FileExplorer API](#FileExplorer-API)
     *
     
### <a name="installation"></a> Installation
##### <a name="installation-maven"></a> Using maven
Coming soon...
##### <a name="installation-gradle"></a> Using gradle
Coming soon...

### <a name="using-library"></a> Using jFileGoodies
In this section we will cover some features of this library.

#### The `FileGoodies` class

The `com.jfilegoodies.FileGoodies` class provides a lot of 'helper' methods for you.
Let's look at a few!

##### <a name="executable-file-ext"></a> Executable file-extensions
Sometimes, we want to determine that a file is an executable file on the current OS.
Usually, this can be checked based on the file's extension.
On Windows there are executable file-extensions like `exe`, `bat` or `msi`.
On Linux, the focus is not on the file-extensions and any file can be executed, unlike Windows but there are "executable" extensions like `sh` or `bin` etc...

If you want to list these extensions you can use the `FileGoodies.listExecutableExtensions()` method.
```java
   List<String> extensions = FileGoodies.listExecutableExtensions();
   extensions.forEach(System.out::println);
```
On Windows the code above will print this:
```
exe
bat
bin
cmd
com
... (+ 29)
```

If you want to check that a file is an executable file you can use the `FileGoodies.isOSExecutable(File)` method.
```java
File file = new File("myProgram.exe");
boolean osExecutable = FileGoodies.isOSExecutable(file);
System.out.println("isOsExecutable: " + osExecutable);
``` 
On Windows the code above will print this:
```
isOsExecutable: true
```

Of course, we have also a `FileGoodies.isNotOSExecutable(File)` method.

##### <a name="file-path-short"></a> Shorting file-paths
If you have a file that has a too long absolute-path, and you want to short it, the `FileGoodies.shortenedFilePath` method
is for you!

Let's take an example:<br>
 You have a file: `user/home/documents/work/plan/Example.docx`<br/>
 You want to show only the corresponding directory of the file, and you want to hide the others like this: `.../plan/Example.docx`

In code:
```java
File file = new File("user\\home\\documents\\work\\plan\\Example.docx");
String shortenedPath = FileGoodies.shortenedFilePath(file, 1 /*=> how many parent directories should be visible*/);
System.out.println(shortenedPath);
```
Output:
```
...\plan\Example.docx
```

You can also specify a custom `prefix` which was `...` in the previous example.
For more information just go to the [javadoc]().

