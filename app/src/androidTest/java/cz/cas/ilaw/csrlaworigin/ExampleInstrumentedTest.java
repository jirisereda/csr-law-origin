package cz.cas.ilaw.csrlaworigin;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import android.util.Log;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cz.cas.ilaw.csrlaworigin", appContext.getPackageName());


    }

    @Test
    public void useTestFormat() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cz.cas.ilaw.csrlaworigin", appContext.getPackageName());


        String aaa = String.format("INSERT OR IGNORE INTO %s (%s" +  repeatString( ", %s", 12 ) + ") VALUES (?" + repeatString( ",?", 12) + ")",

            "recipes_lecker",

            "Id",
            "DateCreated",
            "DateChanged",
            "fieldNameBrand",
            "fieldNameCreator",

            "fieldNameTitle",
            "fieldNameTeaser",
            "ImageTitle",
            "fieldNameImageId",
            "fieldNameImageCredit",

            "Course",
            "PublicationMeta",
            "fieldNameFavorite"
        );

        Log.d("TEST", "aaa");


    }

    private static String repeatString( String pattern, int repeatTimes ){

        String output = pattern;
        for( int cnt = 1; cnt < repeatTimes; cnt++ ){
            output += pattern;
        }
        return output;
    }


}
