package comp3350.bms.tests.acceptance;

// Purpose: Runs through the bidding process in the app and makes sure the bids are accepted/declined correctly.

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
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
public class TestBidding {

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
    public void testBidding() {
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));

        // Click on the first item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(0).perform(click());

        // Verify the product screen is displayed
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.productTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.withDrawAmount)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.bidButton)).check(matches(isDisplayed())).check(matches(isEnabled()));

        // Type a bid amount and send it
        onView(withId(R.id.withDrawAmount)).perform(typeText("100"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.bidButton)).perform(click());

        // Check if the bid was successful
        onView(withText("Transaction was a success!")).check(matches(isDisplayed()));

        Espresso.pressBack();

        // Trying with exorbitant bid amount
        onView(withId(R.id.withDrawAmount)).perform(clearText(), typeText("99999999999999999999"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.bidButton)).perform(click());

        // Check if the bid was failed
        onView(withText("Can not withdraw that much!")).check(matches(isDisplayed()));

        Espresso.pressBack();

        // Trying with no bid amount
        onView(withId(R.id.withDrawAmount)).perform(clearText());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.bidButton)).perform(click());

        // Check if the bid was failed
        onView(withText("Please enter an amount")).check(matches(isDisplayed()));

        Espresso.pressBack();

        // Trying with negative bid amount
        // The bid should work as the text input field doesn't allow negative values
        onView(withId(R.id.withDrawAmount)).perform(typeText("-5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.bidButton)).perform(click());

        // Check if the bid was successful
        onView(withText("Transaction was a success!")).check(matches(isDisplayed()));
    }


}