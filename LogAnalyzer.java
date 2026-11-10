
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer

{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    // question 5
     public LogAnalyzer(String filename)
  {
    hourCounts = new int[HOURS_PER_DAY];
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
        // Q4 - while loop
     int hour = 0;
     while (hour < hourCounts.length) {
      System.out.println(hour + ": " + hourCounts[hour]);
    hour++;
    }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    // question 6
    public int numberOfAccesses()
{
    int total = 0;

    for(int hour = 0; hour < hourCounts.length; hour++)
    {
        total += hourCounts[hour];
    }

    return total;
}
// Q8
public int busiestHour()
{
    int busiest = 0;

    for (int hour = 1; hour < hourCounts.length; hour++)
    {
        if (hourCounts[hour] > hourCounts[busiest])
        {
            busiest = hour;
        }
    }

    return busiest;
}
// Q9
public int quietestHour()
{
    int quietest = 0;

    for (int hour = 1; hour < hourCounts.length; hour++)
    {
        if (hourCounts[hour] < hourCounts[quietest])
        {
            quietest = hour;
        }
    }

    return quietest;
}
// Q11
public int busiestTwoHourPeriod()
{
    int busiest = 0;
    int highestCount = hourCounts[0] + hourCounts[1];

    for (int hour = 1; hour < hourCounts.length - 1; hour++)
    {
        int total = hourCounts[hour] + hourCounts[hour + 1];

        if (total > highestCount)
        {
            highestCount = total;
            busiest = hour;
        }
    }

    return busiest;
}
    // Q1: The busiest times of the day are the hours
// that have the highest access counts.

// Q2: hourCounts is used in the constructor,
// analyzeHourlyData(), and printHourlyCounts().

// Q3: Using <= causes an ArrayIndexOutOfBoundsException
// because the loop tries to access index 24.
// 3749 ( question 7)
// Q10: If multiple hours have the same highest number of accesses,
// busiestHour() returns the first (lowest-numbered) hour.
}
