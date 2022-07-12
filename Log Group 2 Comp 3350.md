

# Iteration 2

Team Meetings, Iteration 2:
June 30th:
* Andrii, Nikhil, Ryan
* 7pm - 8pm
* Reviewed Iteration 1 feedback. Created and assigned developer tasks to group members

July 6th:
* Andrii, Ryan
* 7pm - 9pm
* Fixed a few issues, added more issues and redistributed them between team members.
Reviewed Iteration 1 feedback. Fixed Gradle 

July 9th:
* Andrii, Hamdi, Nikhil, Ryan
* 7pm - 8:30pm
* Fixed a few issues, discussed further work. Closed completed issues.  
Fixed Gradle 

July 12th:
* Andrii, Hamdi, Nikhil, Ryan
* 7pm - 1am
* Fixing issues, restructuring from only objects to SQL interactions.


Personal Logs:


Andrii:
* July 7: 2 pm - 5 pm: 
  * Added User tests / validations
* 9 July: 10 am - 6pm;
  * Database implementation
* 10 July: 9pm - 1 am;
  * Code review, pair programming, discussions, debugging gradle
* 11 July: 12pm - 1am;
  * (SQL redesigning) Redeveloping Wallet feature, redeveloping Auction feature (left for next iteration)
  * Wallet withdrawing money when bid is placed
  * Helping others with connecting objects to database


Developer Tasks, Iteration 2:
- Remove DataAccessStub from OnBoarding Activity (move code to logic) (Cost: 4 hours; Priority: High) - Andrii
- Bot issues (Cost: 1 hour; Priority: High) - Andrii / Hamdi
- Remove/Review "sleep" in Tests (Cost: 4 hours; Priority: High)
- Connect Wallet with User (Cost: 8 hours; Priority: High) - Andrii / Hamdi
- Move Product page (Cost: 8 hours; Priority: High) - Nikhil
- Restructure Persistence Layer for it to work with Database (HSQLDB)e (Cost: 2 days; Priority: High) - Andrii
- Fix Wallet (Cost: 4 hours; Priority: High) - Hamdi
- Rename Project name and folders from SRSYS to BMS (Cost: 2 hours; Priority: High) - Hamdi
- Correct Architecture Image (Cost: 1 hour; Priority: High) - Nikhil
- 

# Iteration 1


Team Meetings, Iteration 1:

May 30th:
* Everyone is present
* 7pm - 8pm
* Created and assigned developer tasks to group members. Set up project repository


May 31st:
* Everyone is present
* 11:45pm-12:15pm
* Setting up an android studio and doing an initial commit. 


June 4nd:
* Ryan, Andrii, and Nikhil were present
* 7:00pm - 7:45pm
* Discussed iteration 0 feedback
* Write up a document containing expected objects in this project


June 7nd:
* Ryan, Andrii, and Brett were present
* 12:00pm - 12:15pm
* Discussed VCS and creating XML layouts


June 12nd:
* Ryan, Andrii
* 7:00pm - 8:20pm
* Discussed iteration 1 tasks, tests and tickets workflow
* Reviewed Pull Request, discussed further development steps, added more tasks for finalizing iteration 1


June 15nd:
* Ryan, Nikhil, Andrii
* 8:00pm - 9:00pm
* Discussed iteration 1 tasks, code clean up and accepted 2 pull requests




________________


Personal Logs:


Andrii:
* 6 pm - 10 pm, June 11: 
  * Added Item, User object. Cleaned up the project
* 3 pm - 7 pm, June 12:
  * Replaced Sample project variable names, added objects to Stub database and created the logic for moving from Welcome page to Feed, then to Item page.
* 8pm - 10pm, June 13:
  * Completed Filters.
* 5pm - 8pm, June 14:
  * Added tests for Item object
* 8pm - 10pm, June 14:
  * Project crashed locally, debugging (pulled .idea files from repo)
* 10pm - 12pm, June 15:
  * Added more tests, fixed project
* 12:15pm - 2:pm, June 16:
  * Finalized files




Ryan:
* 11:00am - 1:00pm, June 8:
  * Setup Android Studio and Nexus 7 device
* 9am - 10:30am, June 12:
  * Researched organisation of Android studio and coding format
* 11:30am - 1:30pm, June 13:
  * Worked on Chat visual presentation and implemented ChatMessages
* 8am - 9am, &&
* 10:20am - 10:50am, && 
* 12:30pm - 3:00pm, June 14:
  * Worked on DataAccessStub ChatMessages and communication between PingChat and ItemActivity. Implemented the first version of the chat widget in the Item page.
* 3:30pm - 6:00pm, June 15:
  * Implemented test classes for ChatMessages and PingChat and merged conflicts.
* 8:00am - 9:30am, June 16:
  * Cleaned up test classes and code standards. Tested Main, failed locally with debug java with Javac error. Origin was tested and declared fine.


Brett:
* 6pm - 8pm, June 7:
   * Installed Android Studio and gained familiarity with the environment.
   * Setup the correct nexus 7 device for simulation.
* 4pm - 5pm, June 12:
   * Setup Github account to work with the team project.
* 2:30pm - 3:30pm, June 13:
   * Setup Android Studio to sync with team Git.
* 2pm - 7pm, June 14:
   * Created Bid and AuctionManager Objects.
   * Completed functionality for both Bid and AuctionManager Objects.
   * Updated User Object to be compatible with Bid and AuctionManager.
   * Reviewed multiple pull requests
* 7pm - 1:30 am June 15, 16:
   * Created tests for Bid.
   * Created tests for AuctionManager.
   * Revised AuctionManager to pass all test cases.
   * Created coding standards file
   * Reviewed multiple pull requests


Hamdi:
* 4pm - 6pm, June 11:
   * Finished setting up Android Studio and learned how to use its tools
   * Setup the emulator
* 11am - 2pm, June 12:
   * Added onboarding activity
* 5pm - 7pm, June 14
   * Added spinner (drop down) to select username on the onboarding page
   * Spinner receives data from the stub database 
* 8pm - 10pm, June 14
   * The next activity after onboarding (feed) now knows which user is logged in, which will become useful in later iterations, via the User objects.
* 5pm - 7pm, June 15
   * Began working on the wallet activity
* 9:30pm - 11pm, June 15
   * Wallet activity is complete, and began working on the top up functionality after resolving a few gradle issues from merge conflicts
* 12am - 1:30am, June 16
   * Cleaned unused code, and the top up functionality now works.
* 11:30am - 1:30pm, June 16
   * Tasks were completed, so I did code review and resolving issues and conflicts with group members
* 2pm - 2:30pm, June 16
   * Done code review and bug fixing
* 2:30pm - 3pm, June 16
   * Major code cleanup, scariest merge of my life.


Nikhil
* 3pm - 6pm, June 12:
   * Created branch and learned basic android studio functionality
   * Researched layouts and activities
* 1pm - 2pm, June 13:
   * Created layout for a product page
* 1pm - 6:30pm, June 14:
   * Created layouts for review page and profile page
   * Created activities for each layout
* 2:30pm - 5:30pm, June 15:
   * Linked activities to each other
* 8:30am - 2pm, June 16:
   * Linked pages to main app and tested functionality
   * Added pages to manifest.xml

________________

Developer Tasks, Iteration 1:


- Simulating bids for AI bot (Simple) - Andrii
- Wallet feature - Hamdi
- OnBoarding page - Hamdi
- Feed of products - Andrii
- Display Product Reviews / Add area for creating reviews - Nikhil
- Display Product Seller - Nikhil
- Chat widget Logic (Display chat function) - Ryan
- Chat widget UI (Display chat function) - Ryan
- Have a bidding tool (logic) - Brett
- Have a bidding tool (UI) - Brett
- Display product description and image - Nikhil


