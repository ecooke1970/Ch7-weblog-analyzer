import java.io.*;
import java.util.*;

/**
 * A class for creating log files of random data.
 * 
 * @author Erik Cooke
 * @version 2019.10.16
 * Updated creatEntry-multiple years, calculate days based on month
 *                    added status codes.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version    2016.02.29
 */
public class LogfileCreator
{
    private Random rand;

    /**
     * Create log files.
     */
    public LogfileCreator()
    {
        rand = new Random();
    }
    
    /**
     * Create a file of random log entries.
     * @param filename The file to write.
     * @param numEntries How many entries.
     * @return true if successful, false otherwise.
     */
    public boolean createFile(String filename, int numEntries)
    {
        boolean success = false;
        
        if(numEntries > 0) {
            try (FileWriter writer = new FileWriter(filename)) {
                LogEntry[] entries = new LogEntry[numEntries];
                for(int i = 0; i < numEntries; i++) {
                    entries[i] = createEntry();
                }
                Arrays.sort(entries);
                for(int i = 0; i < numEntries; i++) {
                    writer.write(entries[i].toString());
                    writer.write('\n');
                }
                
                success = true;
            }
            catch(IOException e) {
                System.err.println("There was a problem writing to " + filename);
            }
                
        }
        return success;
    }
    
    /**
     * Create a single (random) entry for a log file.
     * Creates random year, month, day, and status code.
     * @return A log entry containing random data.
     */
    public LogEntry createEntry()
    {
        int year = 2015 + rand.nextInt(5);
        int month = 1 + rand.nextInt(12);
        // Set day of month based on month
        int day = 0;
        if(month == 2) {
            day = 1 + rand.nextInt(28);
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11) {
            day = 1 + rand.nextInt(30);
        }
        else {
            day = 1 + rand.nextInt(31);
        }
        int hour = rand.nextInt(24);
        int minute = rand.nextInt(60);
        int statusCode = 0;
        int percent = rand.nextInt(101);
        //95% of codes should be 200
        if(percent < 95) {
            statusCode = 200;
        }
        //3% of codes should be 403
        else if(percent > 97) {
            statusCode = 403;
        }
        //2% of codes should be 404
        else {
            statusCode = 404;
        }
        return new LogEntry(year, month, day, hour, minute, statusCode);
    }

}
