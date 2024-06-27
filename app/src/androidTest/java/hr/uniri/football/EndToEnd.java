package hr.uniri.football;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EndToEnd{

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Rule
    public ActivityScenarioRule<MatchesActivity> matchRule = new ActivityScenarioRule<>(MatchesActivity.class);
    @Rule
    public ActivityScenarioRule<AddMatchActivity> matchListRule = new ActivityScenarioRule<>(AddMatchActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testButtonIgraci() {
        Espresso.onView(withId(R.id.btnIgraci))
                .perform(ViewActions.click());

        intended(hasComponent(PlayersActivity.class.getName()));
    }
    @Test
    public void testButtonUtakmice() {
        Espresso.onView(withId(R.id.btnUtakmice))
                .perform(ViewActions.click());

        intended(hasComponent(MatchesActivity.class.getName()));
    }

    @Test
    public void testButtonTablica() {
        Espresso.onView(withId(R.id.btnTable))
                .perform(ViewActions.click());

        intended(hasComponent(TableActivity.class.getName()));
    }
    @Test
    public void testiranjeNavigacijeDoEditiranjaUtakmice() {
        Espresso.onView(withId(R.id.btnUtakmice)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.btnDodajUtakmice))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());

        intended(IntentMatchers.hasComponent(AddMatchActivity.class.getName()));
    }
    @Test
    public void testiranjeNavigacijeIKreiranjeUtakmice() {
        Espresso.onView(ViewMatchers.withId(R.id.btnUtakmice)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btnDodajUtakmice)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.editTextHomeTeam)).perform(ViewActions.replaceText("Domacini"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextAwayTeam)).perform(ViewActions.replaceText("Gosti"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextHomeScore)).perform(ViewActions.replaceText("2"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextAwayScore)).perform(ViewActions.replaceText("2"));
        Espresso.onView(ViewMatchers.withId(R.id.btnDodajUtakmicu)).perform(ViewActions.click());
    }
}