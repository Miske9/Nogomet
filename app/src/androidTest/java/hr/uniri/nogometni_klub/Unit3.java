package hr.uniri.nogometni_klub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class Unit3 {
    @Rule
    public ActivityTestRule<MatchListActivity> rule = new ActivityTestRule<>(MatchListActivity.class);

    @Test
    public void test() {
        Espresso.onView(ViewMatchers.withId(R.id.editDomaciKlub)).perform(ViewActions.replaceText("HomeTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editGostKlub)).perform(ViewActions.replaceText("AwayTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editRezultat)).perform(ViewActions.replaceText("2-1"));

        Espresso.onView(ViewMatchers.withId(R.id.btnSpremiMatch)).perform(ViewActions.click());
    }
}
