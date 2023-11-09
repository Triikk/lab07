package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new Month.SortByDate();
    private static final Comparator<String> BY_MONTHS_ORDER = new Month.SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_MONTHS_ORDER;
    }

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER( 30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        private Month(final int days){
            this.days = days;
        }

        public static Month fromString(final String name){
            /* Ho cambiato la mia soluzione utilizzando i metodi values() e toString() solamente  dopo aver visto la soluzione. */
            Month match = null;
            for(final Month month : Month.values()){
                if(month.toString().startsWith(name.toUpperCase())){
                    if(match != null){
                        throw new IllegalArgumentException(
                            "Name " + name + " is ambiguous (match both " + match + " and " + month
                        );
                    }
                    match = month;
                }
            }
            if(match == null){
                throw new IllegalArgumentException("Name " + name + " does not match any month");
            }
            return match;
        }

        /* I metodi successivi avevo pensato di implementarli entrambi a mano,
         * senza ricordarmi dei metodi già forniti.
         * Per SortByMonthOrder, in particolare, avevo in mente di convertire Month.values() in un ArrayList,
         * utilizzare il metodo indexOf di Month.fromString() su entrambe le stringhe e confrontare gli indici.
         */

        private static class SortByMonthOrder implements Comparator<String> {
            @Override
            public int compare(String month1, String month2) {
                return Month.fromString(month1).compareTo(Month.fromString(month2));
            }
        }
        
        private static class SortByDate implements Comparator<String> {
            @Override
            public int compare(String month1, String month2) {
                return Integer.compare(Month.fromString(month1).days, Month.fromString(month2).days);
            }
        }
    }
}
