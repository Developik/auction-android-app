package comp3350.bms.tests.acceptance;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
public class TestLoggingIn {

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
    public void testLoggingIn() {
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));
    }


}