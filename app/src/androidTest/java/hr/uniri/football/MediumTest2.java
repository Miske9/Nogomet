package hr.uniri.football;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
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
public class MediumTest2{

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

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
}