Assignment-2
Subject Code : DOT503 - DevOps Tools

The Build Script

The Android build system compiles app resources and source code, and packages them into APKs that
you can test, deploy, sign, and distribute the app. Android Studio uses Gradle which is an advanced
build toolkit, to automate and manage the build process, while allowing flexible custom build configurations.

In Android studio, the build script is inside the build.gradle file. This file is located inside
root project directory. By default, the buildscript block has been used by project level build files
to define the Gradle repository and all dependencies which are commonly use in all module.

Build and deploy a debug APK of Android Application
1.	To build and execute all the tasks of project using the Gradle wrapper command line tool.
    For Windows, batch file (gradlew.bat) will be use and for Linux and Mac system
    shell script (gradle.sh) file will be use.

2.	Open Command line and navigate to the root project directory.
    To make a debug build, follow the below command
    gradlew assembleDebug

    This creates an APK file name as your modul_name-debug.apk under project_name/module_name/build/outputs/apk/:
    For example, this project will create as "app-debug.apk”

3.	Now the file is ready to install in android device.
    To install the APK file in connected android device or running emulator.
    gradlew installDebug


IDE also used to deploy and execute this application
* Android Studio 4.1
link :- https://developer.android.com/studio

Steps to execute application :
1) Install Android Studio from above link.
2) After installation please clone or import application from git repository using
    link : https://github.com/rainitagurjar/Assignment-2.git
3) Once it clone all the files into local system it will show you gradle sync. (As this
    is the main part that help android studio to execute application smoothly.
4) Click on "Run" on tab menu then click on "Run app" to run application. You can run this application
    not only in your android mobile but also in android emulator.


Steps to run or execute unit test case.
1) Check there is a unit test class elucidating some of test case in following path :
    path : app -> src -> test -> java -> com.rainita.androidassignment
    class name : MyUnitTest
2) You can now direct run or execute any test case mentioned there and it will return depending
    result in Run tab at the bottom


Steps to create deploy-able package
1) Click on "Build" tab menu then
2) Click on "Generate Signed Bundle/APK"
3) Choose APK there and move next
4) Now fill all the details required to generate a release build.
5) Please choose build variant you are looking for (debug or release)
6) Also please choose version you want in your deploy-able package (V1 or V2). You can check both version as well.
7) Now deploy-able package (APK) is ready for release.
