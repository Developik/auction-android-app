package comp3350.bms.tests.acceptance;

// Purpose: Run through the searching process in the application and verify results are shown correctly.

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
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
public class TestSearching {

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
    public void testSearching() {
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));

        // Search for a product
        onView(withId(R.id.search_query)).perform(typeText("Garden Bucket"));
        onView(withId(R.id.search_button)).perform(click());
        Espresso.closeSoftKeyboard();

        // Click on the first item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(0).perform(click());

        // Item selected should be Garden Bucket, verify this is displayed, as well as the entire display.
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.productTitle)).check(matches(withText(containsString("Garden Bucket"))));
        onView(withId(R.id.withDrawAmount)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.bidButton)).check(matches(isDisplayed())).check(matches(isEnabled()));

        // Go back and search for a different product
        Espresso.pressBack();

        // Search for a product
        onView(withId(R.id.search_query)).perform(clearText(), typeText("Rolex Watch"));
        onView(withId(R.id.search_button)).perform(click());
        Espresso.closeSoftKeyboard();

        // Click on the first item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(0).perform(click());

        // Item selected should be Rolex Watch, verify this is displayed
        onView(withId(R.id.productTitle)).check(matches(withText(containsString("Rolex Watch"))));

    }


}