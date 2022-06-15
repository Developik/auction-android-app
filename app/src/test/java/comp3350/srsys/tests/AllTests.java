package comp3350.srsys.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.srsys.tests.business.PingChatTest;
import comp3350.srsys.tests.objects.ChatMessagesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ChatMessagesTest.class, PingChatTest.class})

public class AllTests {
}
