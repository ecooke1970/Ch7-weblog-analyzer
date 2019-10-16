/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Erik Cooke
 * @2019.10.14
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    //Where to calculate the daily access counts.
    private int[] dayCounts;
    //Where to calculate the monthly access counts.
    private int[] monthCounts;

    /**
     * Create an object to analyze hourly web accesses.
     * @param filename file to be used by LogiFileReader
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // hourly access counts.
        hourCounts = new int[24];
        //daily access counts
        dayCounts = new int[29];
        //monthly access counts
        monthCounts = new int[13];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Run all of the analyze methods.
     * analyzeHourlyData, analyzeDailyData, analyzeMonthyData
     */
    public void analyzeAll()
    {
        analyzeHourlyData();
        analyzeDailyData();
        //analyzeMonthlyData();
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        reader.reset();
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("\nHr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public int numberOfAccessess() {
        int total = 0;
        for(int i = 0;i < hourCounts.length;i++) {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * Returns the most active hour from the data.     
     * If there is more than one hour with the same amount it will return the first found
     */
    public int busiestHour() {
        int biggest = 0;
        int busiest = 0;
        for(int hour = 0;hour < hourCounts.length;hour++) {
            if(hourCounts[hour] > biggest) {
                biggest = hourCounts[hour];
                busiest = hour;
            }            
        }
        return busiest;
    }
    
    /**
     * Return the least active hour from the data
     * Will return the first found if multiple hours are the lowest
     */
    public int quietestHour() {
        int leastVisits = hourCounts[0];
        int quietest = 0;
        for(int i = 1;i < hourCounts.length;i++) {
            if(hourCounts[i] < leastVisits) {
                leastVisits = hourCounts[i];
                quietest = i;
            }
        }
        return quietest;
    }
    
    /**
     * Returns the busiest 2 hour period from the data
     */
    public int busiestTwoHour() {
        int busiest = 0;
        int biggest = 0;
        for(int i = 0;i < hourCounts.length;i++) {
            if(i == hourCounts.length - 1) {
                if(hourCounts[i] + hourCounts[0] > biggest) {
                    biggest = hourCounts[i] + hourCounts[0];
                    busiest = i;
                }
            }                    
            else if(hourCounts[i] + hourCounts[i + 1] > biggest) {
                    biggest = hourCounts[i] + hourCounts[i + 1];
                    busiest = i;
            }
        }
        return busiest;
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        reader.reset();
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Print daily counts
     */
    public void printDailyCounts()
    {
        System.out.println("\nDay: Count");
        for(int day = 1;day < dayCounts.length;day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    /**
     * Returns the quietest day.  If there are more than one day with same value,
     *  returns first found.
     */
    public int quietestDay()
    {
        int leastVisits = dayCounts[1];
        int quietest = 0;
        for(int day = 1;day < dayCounts.length;day++) {
            if(dayCounts[day] < leastVisits) {
                leastVisits = dayCounts[day];
                quietest = day;
            }
        }
        return quietest;
    }
    
    /**
     * Returns the busiest day. If there are more than one day with same value,
     * returns first found.
     */
    public int busiestDay()
    {
        int mostVisits = 0;
        int busiest = 0;
        for(int day = 1;day < dayCounts.length;day++) {
            if(dayCounts[day] > mostVisits) {
                mostVisits = dayCounts[day];
                busiest = day;
            }
        }
        return busiest;
    }

    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        reader.reset();
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    
    /**
     * Print monthly counts
     */
    public void printMonthlyCounts()
    {
        System.out.println("\nMonth: Count");
        for(int month = 1;month < monthCounts.length;month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
}
