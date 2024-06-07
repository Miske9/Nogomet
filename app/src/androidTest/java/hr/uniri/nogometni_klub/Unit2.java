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
public class Unit2 {
    @Rule
    public ActivityScenarioRule<PlayerListActivity> rule = new ActivityScenarioRule<>(PlayerListActivity.class);

    @Test
    public void test() {
        Espresso.onView(ViewMatchers.withId(R.id.editIme)).perform(ViewActions.replaceText("Matija"));
        Espresso.onView(ViewMatchers.withId(R.id.editPrezime)).perform(ViewActions.replaceText("Misan"));
        Espresso.onView(ViewMatchers.withId(R.id.editGodine)).perform(ViewActions.replaceText("25"));
        Espresso.onView(ViewMatchers.withId(R.id.editPozicija)).perform(ViewActions.replaceText("Napadac"));

        Espresso.onView(ViewMatchers.withId(R.id.btnSpremi)).perform(ViewActions.click());
    }
}
