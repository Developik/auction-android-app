# Bidding Market Simulator
Comp 3350 Android App | Team 2

## Architecture

![image](https://user-images.githubusercontent.com/54965144/174135143-8b5d4798-4a37-46e2-80c7-78fe659305af.png)

## Requirements
* **Gradle version 7.2.1**

## How to Run Tests:

- Go to ../tests directory
- Right click AllTests and click on "Run 'AllTests'" option
- ![image](https://user-images.githubusercontent.com/54965144/173991106-a248b1aa-91f6-4f12-b60d-09938458ef2e.png)

## How to Run Project 

- Press "Run 'App'"
  - This action will start emulator and app should automatically open 
  - Emulator used was a Nexus 7 on Marshmallow (6.0)

## Potential Problems

- Build error: `Cannot resolve class android.support.constraint.ConstraintLayout`
  - In the `build.gradle` file, make sure the dependency is met as follows:
  - `dependencies { implementation 'com.android.support.constraint:constraint-layout:2.0.4 }`
- What if there is a project error?
  - There could be a number of potential issues, but try to 'Sync Project with Gradle files'
  - ![image](https://user-images.githubusercontent.com/54965144/173991853-26cb6447-e071-49ce-a86a-fcbd9059cf60.png)

  - If that does not help try the following steps:
    - Switch gradle version a few times and keep installing them until it works 
    - Go to "build.gradle" file in project root folder and keep changing version of " classpath 'com.android.tools.build:gradle:4.0.2' "
    - Try to install 4.0.2 then 7.0.3, then change version to 4.0.2 again and after installing again App should run
