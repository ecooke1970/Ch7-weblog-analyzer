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

    /**
     * Create an object to analyze hourly web accesses.
     * @param filename file to be used by LogiFileReader
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
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
        System.out.println("Hr: Count");
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
     * Returns the most active hour from the data.     * 
     */
    public int busiestHour() {
        int biggest = 0;
        int busiest = 0;
        for(int i = 0;i < hourCounts.length;i++) {
            if(hourCounts[i] > biggest) {
                biggest = hourCounts[i];
                busiest = i;
            }            
        }
        return busiest;
    }
    
    /**
     * Return the least active hour from the data
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
}
