

# Iteration 3

July 23th:
* Andrii, Hamdi, Ryan
* 7pm - 8pm
* Discuss iteration 3 and split up the work, started work on the retrospective activity.

July 24th:
* Andrii, Ryan
* 7pm - 8pm
* Completed retrospective activity

July 27-28th:
* Andrii, Ryan, Hamdi
* 6pm - 1am
* Completing iteration and fixing bugs

## Personal Logs:

Andrii:
* 24 July: 4pm - 6pm:
  * Features review, preparing new DB design for auction system 
* 27-28 July: 12pm - 1am:
  * Bidding feature
  * Multiple users 
  * Fixes to allow correct redirection to products

Hamdi:
* July 25: 12:00 pm - 3:00 pm
  * Studied Espresso and ran the sample project to know how to work with it.
  * Started adding the dependencies to the build.gradle file.
* July 26: 3:00 pm - 8:00 pm
  * Couldn't get the project to run, Espresso dependency was not working.
  * Spent a lot of time figuring out what was wrong with the project.
  * Fixed the dependencies and the project ran.
* July 27: 1:00 pm - 11:59 pm
  * Written Espresso tests for all user stories. Added new user stories and removed some that were redundant.
* July 28: 12:00 am - 3:00 am
  * Updated Espresso tests to work with new UI changes. Fixed inconsistent search.
* July 28: 3:00 am - 4:00 am
  * Fixed inconsistency with the chat feature. Will now always respond to messages.

Nikhil:
* July 26: 3pm - 6pm
  * Worked on AccessPaymentcardsTest
* July 27: 1pm - 7pm
  * Worked on AccessUsersTest
* July 27: 9pm - 4am
  * Worked on AccessPaymentcardsTest & AccessUsersTest
  * Reviewed PRs
* July 28: 10am - 6pm
  * Edited architecture diagram
  
Ryan:
* 7:00pm - 8:00pm, July 23
  * worked on completing the iteration 2 retrospective
* 7:00pm - 8:00pm, July 24
  * completed the iteratiob 2 retrospective
* 3:00pm - 5:00pm, July 25
  * reviewed and researched integration testing
* 2:00pm - 4:00pm, July 26
  * worked on creating integration tests for ProductLogic, AccessProduct, AccessWallet, PingChat
* 3:30pm - 8:00pm, 9:30pm - 10:50pm, July 27
  * completed integration tests from previous day
* 10:20am - 12:05am, 3:00pm - 7:30pm, 8:30pm - 10:00 July 28
  * ensured that tests were working and cleaned up code
  * ensured formating and code standards

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
________________

Personal Logs:

Andrii:
* July 7: 2 pm - 5 pm: 
  * Added User tests / validations
* 9 July: 10 am - 6pm:
  * Database implementation
* 10 July: 9pm - 1 am:
  * Code review, pair programming, discussions, debugging gradle
* 11 July: 12pm - 1am:
  * (SQL redesigning) Redeveloping Wallet feature, redeveloping Auction feature (left for next iteration)
  * Wallet withdrawing money when bid is placed
  * Helping others with connecting objects to database

Ryan:
* 8:30am - 9:00am, 10:15am - 10:50am, July 7:
  * Cleaned up code and implementations
* 4:00pm - 7:00pm, July 8:
  * researched how to pass objects between activities and how to save them
  * implemented users passed to other pages using serializable and started work on onSavedInstance override
* 3:00pm - 7:00pm, July 9:
  * Moved product view page from onboarding page to product page (under feed page) and adjusted user implementation based on comments in pull request
* 10:00pm - 12:00pm, 12:30 - 3:00pm, July 10:
  * rewrote user passing imlpementation based on different the groups decision to change how objects are acquired
* 11:00pm - 12:00pm, 1:00pm - 2:45pm, 7:00pm - 10:30pm, July 11:
  * finalized user passing and chat updates with the tests for each new method and confirmed that the main branch was running correctly with and without my changes.

Hamdi:
* July 09: 7 pm - 8:30 pm 
  * Worked on Wallet activity further, had a meeting to discuss potential solutions for issues
* July 10: 5 pm - 12 am 
  * Worked on Wallet object, fixed top up, link Wallet with user, will implement transactions in the future 
  * Fixed Gradle even further, added the wrapper files (which we needed apparently) to prevent markers from being unable to run the project right away.
* July 11: 3 pm - 7 pm
  * Updated package naming, fixed layouts and sizes for items and made stuff clearer
* July 11: 7 pm - 7:30 pm 
  * Worked on moving the testing framework to JUnit 4 and added new tests for several objects
* July 11: 8 pm -10 pm
  * Created new wallet tests for the wallet object.
* July 11: 11:30 pm - 12:40 am
  * Did code review and solve conflicts
* July 12: 1:00 pm - 2:00 pm
  * Resolved dependencies and fixed imports
* July 12: 2:00 pm - 3:00 pm
  * Updated user stories and fixed tests, updated README as well.
  * Added new tests.

Nikhil
* July 8: 1 pm - 6 pm: 
  * Fixing my work from iteration 1 (Product)
* 9 July: 2pm - 6pm:
  * Logic for a sealed bid auction
  * Testing for stub db
* 11 July: 4pm - 2am:
  * Finished testing for stub db
  * Lots of bug fixes, pair programming, discussions
  * Fixed issue in chat
  * Created SealedBidAuction, Bid, Product classes
________________

Developer Tasks, Iteration 2:

- Remove DataAccessStub from OnBoarding Activity (move code to logic) (Cost: 4 hours; Priority: High) - Andrii
- Bot issues (Cost: 1 hour; Priority: High) - Andrii / Hamdi
- Remove/Review "sleep" in Tests (Cost: 4 hours; Priority: High) - Hamdi
- Connect Wallet with User (Cost: 8 hours; Priority: High) - Andrii / Hamdi
- Move Product page (Cost: 8 hours; Priority: High) - Ryan
- Restructure Persistence Layer for it to work with Database (HSQLDB)e (Cost: 2 days; Priority: High) - Andrii
- Fix Wallet (Cost: 4 hours; Priority: High) - Hamdi
- Rename Project name and folders from SRSYS to BMS (Cost: 2 hours; Priority: High) - Hamdi
- Correct Architecture Image (Cost: 1 hour; Priority: High) - Nikhil
- Auction System (Cost: 8 hours; Priority: High) // partially completed - Nikhil
- Persistence storage for chat (Cost: 8 hours; Priority: High) // partially completed - Ryan
- Create exception handling PingChat test (Cost: 4 hour; Priority: High)- Ryan 
- Add new Tests for communication between Presentation and DB layers (Cost: 8 hours; Priority: High) - Ryan / Nikhil / Hamdi

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


