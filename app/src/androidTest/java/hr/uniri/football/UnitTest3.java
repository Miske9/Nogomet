package hr.uniri.football;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UnitTest3 {
    @Rule
    public ActivityScenarioRule<AddMatchActivity> rule = new ActivityScenarioRule<>(AddMatchActivity.class);

    @Test
    public void azuriranjeUtakmice() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextHomeTeam)).perform(ViewActions.replaceText("HomeTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextAwayTeam)).perform(ViewActions.replaceText("AwayTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextHomeScore)).perform(ViewActions.replaceText("2"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextAwayScore)).perform(ViewActions.replaceText("3"));

        Espresso.onView(ViewMatchers.withId(R.id.btnDodajUtakmicu)).perform(ViewActions.click());
    }
}

