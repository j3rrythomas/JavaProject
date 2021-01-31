# Google Drive Manager
</br>

This was made to access the recorded class lectures in google meet.  
The recorded google meet must be shared through google drive.  

Even though this application was made to access the gmeet links, it has expanded  
to access all sortes of files in google dirve.

##### Currently Supported Operating system
* Linux

## Requirements for building from source
1. Gradle

#### Installation Process for Gradle
</br>

1. Download (the binaries only) gradle from this [link (Version 6.7.1) ][gradle-6.7.1_dl]  
   If you are on a linux-based operating system then follow along, else you can find the required installation guid for your OS [here][installation-guides] 
   (The guides to build tool management software like sdkman is in the link)

2. Extract the `.zip` file to `/opt/gradle/` dirctory in the linux system(or whereever you prefer to keep this file)  

3. For most distributions adding the following line to the `.profile` in the `$HOME` directory should work   
   `PATH=$PATH:/opt/gradle/gradle-6.7.1/bin/` (if you chose your own file path for saving the unziped files then make changes accordingly) `gradle-6.7.1` may change depending on the time you read this file.
4. You may want to restart your computer for it to recogonize the new `PATH` variable.
   
#### Building the source
1. Clone the git repository.
2. In your Terminal, get inside the file directory
3. Run the command `gradle run` to tun the program


[gradle-6.7.1_dl]: https://gradle.org/next-steps/?version=6.7.1&format=bin "Download gradle version 6.7.1"
[installation-guides]: https://gradle.org/install/
