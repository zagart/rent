package rent.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class with methods for parsing data.
 *
 * @author zagart
 */
public class ParseUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    private static final int FORMAT_POSITION = 0;

    public static Date parseStringToDate(final String pDate, final String... pFormat) {
        final DateFormat dateFormat;
        if (pFormat.length == 0) {
            dateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        } else {
            dateFormat = new SimpleDateFormat(pFormat[FORMAT_POSITION]);
        }
        final Date parsedDate;
        try {
            parsedDate = dateFormat.parse(pDate);
        } catch (ParseException pEx) {
            return null;
        }
        return parsedDate;
    }
}
