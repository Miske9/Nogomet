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
public class UnitTest2 {
    @Rule
    public ActivityScenarioRule<AddPlayerActivity> rule = new ActivityScenarioRule<>(AddPlayerActivity.class);

    @Test
    public void azuriranjeIgraca() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextName)).perform(ViewActions.replaceText("Matija"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextSurname)).perform(ViewActions.replaceText("Misan"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextCategory)).perform(ViewActions.replaceText("senior"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextPosition)).perform(ViewActions.replaceText("Napadac"));
        Espresso.onView(ViewMatchers.withId(R.id.editTextNumber)).perform(ViewActions.replaceText("7"));

        Espresso.onView(ViewMatchers.withId(R.id.btnDodajIgraca )).perform(ViewActions.click());
    }
}