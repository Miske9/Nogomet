package hr.uniri.nogometni_klub;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class medium {
    @Rule
    public ActivityScenarioRule<PlayerListActivity> playerRule = new ActivityScenarioRule<>(PlayerListActivity.class);

    @Rule
    public ActivityScenarioRule<MatchListActivity> matchRule = new ActivityScenarioRule<>(MatchListActivity.class);

    @Test
    public void testPlayerList() {
        // Player list test
        Espresso.onView(ViewMatchers.withId(R.id.editIme)).perform(ViewActions.replaceText("Matija"));
        Espresso.onView(ViewMatchers.withId(R.id.editPrezime)).perform(ViewActions.replaceText("Misan"));
        Espresso.onView(ViewMatchers.withId(R.id.editGodine)).perform(ViewActions.replaceText("25"));
        Espresso.onView(ViewMatchers.withId(R.id.editPozicija)).perform(ViewActions.replaceText("Napadac"));

        // Click on the button
        Espresso.onView(ViewMatchers.withId(R.id.btnSpremi)).perform(ViewActions.click());
    }

    @Test
    public void testMatchList() {
        // Match list test
        Espresso.onView(ViewMatchers.withId(R.id.editDomaciKlub)).perform(ViewActions.replaceText("HomeTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editGostKlub)).perform(ViewActions.replaceText("AwayTeam"));
        Espresso.onView(ViewMatchers.withId(R.id.editRezultat)).perform(ViewActions.replaceText("2-1"));
        Espresso.onView(ViewMatchers.withId(R.id.btnSpremiMatch)).perform(ViewActions.click());

    }
}
