package com.ulasgergerli.virtucart.VirtuCart.Milliseconds;

import java.util.Date;

public class Milliseconds {

    public static long fromHours(int hours) {
        return (long) hours * 60 * 60 * 1000;
    }

    public static long fromMinutes(int minutes) {
        return (long) minutes * 60 * 1000;
    }

    public static long fromSeconds(int seconds) {
        return (long) seconds * 1000;
    }

    public static long fromDate(java.util.Date date) {
        return date.getTime();
    }

    public static long fromSpan(Date start, Date end) {
        return end.getTime() - start.getTime();
    }

    public static long fromSpanNow(Date start) {
        return fromSpan(start, new Date());
    }
}
