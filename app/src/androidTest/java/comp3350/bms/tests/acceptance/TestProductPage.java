package comp3350.bms.tests.acceptance;

// Purpose: Test the product page correctly in the app

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
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
public class TestProductPage {

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
    public void testProductPage() {
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));

        // Click on the first item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(0).perform(click());

        // Item selected should be Garden Bucket, verify this is displayed, as well as the entire display.
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.productTitle)).check(matches(withText(containsString("Garden Bucket"))));
        onView(withId(R.id.withDrawAmount)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.bidButton)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.chatLabelTitle)).check(matches(isDisplayed()));

        // Go back and select a different product
        Espresso.pressBack();

        // Click on the second item in the list
        onData(anything()).inAdapterView(withId(R.id.listItems)).atPosition(1).perform(click());
        Espresso.closeSoftKeyboard();

        // Item selected should be Rolex Watch, verify this is displayed
        onView(withId(R.id.productTitle)).check(matches(withText(containsString("Rolex Watch"))));

    }


}