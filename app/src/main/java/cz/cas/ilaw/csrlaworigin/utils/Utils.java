package cz.cas.ilaw.csrlaworigin.utils;

import java.text.Collator;
import java.util.Locale;

public class Utils {

    public static Collator getDefaultCollator() {
        String defaultLocaleString = Locale.getDefault().toString();
        Locale defaultLocale = new Locale(defaultLocaleString);
        return Collator.getInstance(defaultLocale);
    }


}