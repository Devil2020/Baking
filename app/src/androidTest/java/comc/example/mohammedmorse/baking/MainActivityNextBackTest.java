package comc.example.mohammedmorse.baking;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.Menu;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import comc.example.mohammedmorse.baking.Retrofit.TotalJsonDataModel;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Mohammed Morse on 28/08/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityNextBackTest {
    @Rule
    public ActivityTestRule<MainActivity> rule=new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void Test(){
        Espresso.onView(ViewMatchers.withId(R.id.Skip)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.Back)).perform(ViewActions.click());
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.AppName), ViewMatchers.isDisplayed())).
                check(ViewAssertions.matches(ViewMatchers.withText(" Baking App ")));
    }


}
