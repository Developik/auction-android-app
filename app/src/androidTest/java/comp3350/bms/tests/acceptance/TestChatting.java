package comp3350.bms.tests.acceptance;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.bms.R;
import comp3350.bms.presentation.OnboardingActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestChatting {

    @Rule
    public ActivityTestRule<OnboardingActivity> homeActivity = new ActivityTestRule<>(OnboardingActivity.class);

    @Test
    public void testScreen() {
        // Check screen contents are displayed
        onView(withId(R.id.title)).check(matches(withText(containsString("Bidding Market Simulator"))));
        onView(withId(R.id.onboarding_header_started)).check(matches(withText(containsString("Let's get you started!"))));
        onView(withId(R.id.onboarding_header_welcome)).check(matches(withText(containsString("Welcome to Bidding Market Simulator!"))));
        onView(withId(R.id.login_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.userSpinner)).check(matches(isDisplayed()));
        // Check the first user in the spinner is 'joedoe'
        onView(withId(R.id.userSpinner)).check(matches(withSpinnerText(containsString("joedoe"))));
    }

    @Test
    public void testChatting() {
        // TODO: Update chat messages to be more descriptive
        // TODO: Will currently fail till chat messages work correctly
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));

        // Click on the first item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(0).perform(click());

        // Item should be Garden Bucket, verify it is displayed and all screen
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.productTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.withDrawAmount)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.bidButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.chatLabelTitle)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.chatInput)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.sendChat)).perform(scrollTo()).check(matches(isDisplayed())).check(matches(isEnabled()));

        // Type a chat message and send it.
        onView(withId(R.id.chatInput)).perform(typeText("Hello testing!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sendChat)).perform(click());

        // Verify the chat message is displayed
        // User chats are always on chat 1 and chat 3
        onView(withId(R.id.ChatLog1)).check(matches(withText(containsString("Hello testing!"))));

        // Send another message
        onView(withId(R.id.chatInput)).perform(typeText("Hello again!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.sendChat)).perform(click());

        // Verify that both chat messages are displayed
        onView(withId(R.id.ChatLog3)).check(matches(withText(containsString("Hello testing!"))));
        onView(withId(R.id.ChatLog1)).check(matches(withText(containsString("Hello again!"))));

    }


}