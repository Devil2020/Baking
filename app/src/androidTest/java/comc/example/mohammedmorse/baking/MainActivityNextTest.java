package comc.example.mohammedmorse.baking;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.core.internal.deps.guava.base.MoreObjects;
import android.support.test.espresso.matcher.ViewMatchers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.EnumSet;

/**
 * Created by Mohammed Morse on 27/08/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityNextTest {
 @Rule
    public ActivityTestRule<MainActivity> rule=new ActivityTestRule<MainActivity>(MainActivity.class);
 @Test
    public void Test(){
        Espresso.onView(ViewMatchers.withId(R.id.Skip)).perform(ViewActions.click());
     Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.AppName), ViewMatchers.isDisplayed())).
             check(ViewAssertions.matches(ViewMatchers.withText(" Food Ready ")));
    /* Espresso.onView(ViewMatchers.withId(R.id.AppName))
                        .check(ViewAssertions.matches(ViewMatchers.withText(" Food Ready ")));
*/
 }

}
