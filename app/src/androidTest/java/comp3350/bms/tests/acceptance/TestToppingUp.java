package comp3350.bms.tests.acceptance;

// Purpose: Runs through the Wallet activity and tops up the wallet accordingly and verifies the top up has worked.

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;

import comp3350.bms.R;
import comp3350.bms.presentation.OnboardingActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestToppingUp {

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
    public void testToppingUp() {
        // Verify feed page is displayed
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.search_query)).check(matches(isDisplayed()));
        onView(withId(R.id.search_button)).check(matches(withText(containsString("Search"))));
        onView(withId(R.id.search_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.wallet_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withId(R.id.listItems)).check(matches(isDisplayed()));

        // Click on the Wallet button
        onView(withId(R.id.wallet_button)).perform(click());

        // Verify the wallet page is displayed
        onView(withId(R.id.balance)).check(matches(isDisplayed()));
        onView(withId(R.id.topup_button)).check(matches(isDisplayed())).check(matches(isEnabled()));
        onView(withText("Your Balance")).check(matches(isDisplayed()));

        // Press top up button
        onView(withId(R.id.topup_button)).perform(click());

        // Verify the top up page is displayed
        onView(withId(R.id.textViewDialogue)).check(matches(withText(containsString("Select Card"))));
        onView(withId(R.id.card_spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.topUpAmount)).check(matches(isDisplayed()));

        // Verify the first card is selected in the spinner
        onView(withId(R.id.card_spinner)).check(matches(withSpinnerText(containsString("4005550000000019"))));

        // Enter an amount
        onView(withId(R.id.topUpAmount)).perform(typeText("100"));

        // Press top up button
        onView(withText("Top Up")).perform(click());
    }


}