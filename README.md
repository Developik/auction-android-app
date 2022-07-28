# Bidding Market Simulator
Comp 3350 Android App | Team 2

## Functionality Included:
* Users can login to the simulator from a defined list of users.
* Users can tap on a product to see the product's details.
* Users can bid on a product, deducting the bid amount from their balance.
* Users can see their balance from the wallet page.
* Users can join a live chat on a product's page with other users. (This is not persistent on purpose).
* Users can top up their wallet by tapping on the top up button.
* All the users' data will be persistent across restarts of the app.

## User Stories:
* As a user, I want to be able to login to the simulator so that I can bid on products.
  * Cost: 0.5 days Priority: High
* As a user, I want to be able to see the product details so that I can bid on it and know what I'm bidding on.
  * Cost: 1 day Priority: Medium
* As a user, I want to be able to bid on a product so that I can purchase it.
  * Cost: 2 days Priority: High
* As a user, I want to be able to chat with others on a product's page.
  * Cost: 0.5 days Priority: Medium
* As a user, I want to be able to top up my balance so that I can bid on products.
  * Cost: 2 days Priority: High
* As a user, I would want to have my data be saved and not lost upon my next use of the app so that I do not lose my progress.
  * Cost: 4 days Priority: High
* As a user, I would like to see a picture of what I am buying.
  * Cost: 2 days Priority: Low
* As a user, I would like to search for my products by name.
  * Cost: 0.5 days Priority: Medium

## Developer Tasks
* The Developer Tasks are written in the Log file.

## Architecture

![image](https://user-images.githubusercontent.com/54965144/178541425-a6e18a5d-ba4f-46ac-809c-d32a321ade85.png)

## Requirements
* **Android Gradle Plugin version 7.2.1**
* **Android SDK version 23**
* **Gradle Version 7.3.3**
* We also all used Android Studio Chipmunk as some of us had problems with older version of Android Studio.

## How to Run Tests:

- **For Unit Tests**:
- We are running JUnit 4 across all tests, this should automatically download with the launch of the project.
- This is under `test`
- Right click RunUnitTests and click on "Run 'RunUnitTests'" option
- **For Acceptance Tests**:
- This is under `androidTest`
- Right click RunAcceptanceTests and click on "Run 'RunAcceptanceTests'" option

## How to Run Project 

- Press "Run 'App'"
  - This action will start emulator and app should automatically open 
  - Emulator used was a Nexus 7 on Marshmallow (6.0)

## Dependencies

- Build error: `Cannot resolve class android.support.constraint.ConstraintLayout`
  - In the `build.gradle` file, make sure the dependency is met as follows:
  - `dependencies { implementation 'com.android.support.constraint:constraint-layout:2.0.4 }`

## Potential Problems

- The application crashes.
  - We found that clearing the app_db folder and restarting the emulator fixes the issue.
  - This is under /data/data/comp3350.srsys/app_db/ in the Device File Explorer
  - You shouldn't have to do this, but it might happen very rarely.
  - This only happened to us when the database script was being updated.
- What if there is a project error?
  - There could be a number of potential issues, but try to 'Sync Project with Gradle files'
  - ![image](https://user-images.githubusercontent.com/54965144/173991853-26cb6447-e071-49ce-a86a-fcbd9059cf60.png)

  - If that does not help try the following steps:
    - Go File -> Invalidate Caches and make sure the Gradle requirements are set.
