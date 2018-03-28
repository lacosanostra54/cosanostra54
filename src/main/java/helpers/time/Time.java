package helpers.time;

import java.util.Date;

public class Time
{
    public static Long getUnixTimestamp()
    {
        return System.currentTimeMillis() / 1000L;
    }

    public static Date unixTimestapToDate(long unixTimestamp)
    {
        return new java.util.Date(unixTimestamp * 1000);
    }

    public static Long day = (long)24*60*60;
}
