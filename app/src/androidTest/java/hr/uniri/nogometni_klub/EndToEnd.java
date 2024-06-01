package hr.uniri.nogometni_klub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EndToEnd{

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityScenarioRule<MatchActivity> matchRule = new ActivityScenarioRule<>(MatchActivity.class);
    @Rule
    public ActivityScenarioRule<MatchListActivity> matchListRule = new ActivityScenarioRule<>(MatchListActivity.class);


    @Before
    public void setup() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testNavigationDrawer() {
        // Open the navigation drawer
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());

        // Check if the navigation view is displayed
        Espresso.onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()));

        // Close the navigation drawer
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.close());
    }

    @Test
    public void testButtonIgraci() {
        // Click the 'Igraci' button
        Espresso.onView(withId(R.id.btnIgraci))
                .perform(ViewActions.click());

        // Verify that PlayerActivity is started
        intended(hasComponent(PlayerActivity.class.getName()));
    }
    @Test
    public void testButtonUtakmice() {
        // Click the 'Utakmice' button
        Espresso.onView(withId(R.id.btnUtakmice))
                .perform(ViewActions.click());

        // Verify that MatchActivity is started
        intended(hasComponent(MatchActivity.class.getName()));
    }

    @Test
    public void testButtonTablica() {
        // Click the 'Tablica' button
        Espresso.onView(withId(R.id.btnTablica))
                .perform(ViewActions.click());

        // Verify that StandingsActivity is started
        intended(hasComponent(StandingsActivity.class.getName()));
    }
    @Test
    public void testAddMatchButton() {
        // Click the 'Add Match' button
        Espresso.onView(withId(R.id.add_match_button))
                .perform(ViewActions.click());

        // Verify that MatchListActivity is started
        intended(IntentMatchers.hasComponent(MatchListActivity.class.getName()));
    }
    @Test
    public void testMatchList() {
        // Match list test
        Espresso.onView(ViewMatchers.withId(R.id.editDomaciKlub)).perform(ViewActions.replaceText("HomeTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editGostKlub)).perform(ViewActions.replaceText("AwayTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editRezultat)).perform(ViewActions.replaceText("2-1"));
        Espresso.onView(ViewMatchers.withId(R.id.btnSpremiMatch)).perform(ViewActions.click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
