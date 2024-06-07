package hr.uniri.nogometni_klub;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UnitTest1 {

    @Before
    public void setUp() {
        ActivityScenario.launch(PlayerActivity.class);
    }

    @Test
    public void testiranjeVidljivostiAddPlayerButtona() {
        onView(withId(R.id.add_player_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testiranjeNavigacijePomocuAddPlayerButtona() {
        onView(withId(R.id.add_player_button)).perform(click());
    }
}

