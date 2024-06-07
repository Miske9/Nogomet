package hr.uniri.nogometni_klub;

import android.content.Intent;
import android.database.Cursor;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.navigation.NavigationView;

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
public class medium2{

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

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
        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open());

        Espresso.onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.close());
    }

    @Test
    public void testButtonIgraci() {
        Espresso.onView(withId(R.id.btnIgraci))
                .perform(ViewActions.click());

        intended(hasComponent(PlayerActivity.class.getName()));
    }
    @Test
    public void testButtonUtakmice() {
        Espresso.onView(withId(R.id.btnUtakmice))
                .perform(ViewActions.click());

        intended(hasComponent(MatchActivity.class.getName()));
    }

    @Test
    public void testButtonTablica() {
        Espresso.onView(withId(R.id.btnTablica))
                .perform(ViewActions.click());
        intended(hasComponent(StandingsActivity.class.getName()));
    }
}
