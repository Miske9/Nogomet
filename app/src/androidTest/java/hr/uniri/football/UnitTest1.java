package hr.uniri.football;


import androidx.test.core.app.ActivityScenario;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class UnitTest1 {

    @Before
    public void setUp() {
        ActivityScenario.launch(PlayersActivity.class);
    }

    @Test
    public void testiranjeVidljivostiAddPlayerButtona() {
        onView(withId(R.id.btnDodajIgrace)).check(matches(isDisplayed()));
    }

    @Test
    public void testiranjeNavigacijePomocuAddPlayerButtona() {
        onView(withId(R.id.btnDodajIgrace)).perform(click());
    }
}

