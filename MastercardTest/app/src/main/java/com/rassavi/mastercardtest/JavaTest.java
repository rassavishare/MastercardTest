package com.rassavi.mastercardtest;
/*
* Use the following class, JavaTest.
* Complete the functions and add a unit test module.
* A second file, BonusQuestions.java, contains more challenging problems you can try!
*
* For extra credit:
* 1. Take this opportunity to show off what you can do with a mobile app:
* 		- Create an attractive user interface for your app.
* 		- Accept user input to the existing functions, and display the results in a sensible manner.
* 		- Add instrumentation tests.
* 2. Attempt to complete one or more of the functions in BonusQuestions.java.
*
*/


import com.rassavi.mastercardtest.comparators.DistanceComparator;
import com.rassavi.mastercardtest.model.AccessLog;
import com.rassavi.mastercardtest.model.LogType;
import com.rassavi.mastercardtest.model.ResponseCode;
import com.rassavi.mastercardtest.model.User;
import com.rassavi.mastercardtest.model.UserLog;
import com.rassavi.mastercardtest.util.AnalyzeAccessLogUtil;
import com.rassavi.mastercardtest.util.ParseStatLogUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTest {
	/**
	**	PROGRAMMING
	**		- Please complete the following functions.
	**		- Please test your work.
	** 		- Feel free to use different data types than the examples, so long as it makes sense for the question.
	**/

	/**
	 * Format the arguments based on a position.
	 *
	 * @param fmt 	The format string.
	 * @param args 	Additional arguments.
	 * @return 		The formatted string.
	 *
	 * An example:
	 * <pre>
	 *     System.out.println(JavaTest.formatByPosition("Hello {0} {1} times!", "world", 5));
	 *     'Hello world 5 times!'
	 * </pre>
	 */
	public static String formatByPosition(String fmt, Object... args)
	{
		Matcher m = Pattern.compile("\\{(.*?)\\}").matcher(fmt);
		while(m.find()) {
			String index=m.group(1);
			int i=Integer.valueOf(index);
			String regex="\\{"+index+"\\}";
			fmt=fmt.replaceAll(regex,args[i].toString());
		}
		return fmt;
	}

	/**
	 * Find two closest objects by distance.
	 *
	 * @param objects 	The list of objects with the name and coordinates.
	 * @return 			The names of the closest objects.
	 *
	 * An example:
	 * <pre>
	 *     final Map obj1 = new HashMap();
	 *     final Map obj2 = new HashMap();
	 *     final Map obj3 = new HashMap();
	 *     obj1.put("name", "a"); obj1.put("x", 1); obj1.put("y", 1);
	 *     obj2.put("name", "b"); obj2.put("x", 1); obj2.put("y", 2);
	 *     obj3.put("name", "c"); obj3.put("x", 10); obj3.put("y", 10);
	 *     final Map[] map = {obj1, obj2, obj3};
	 *     System.out.println(JavaTest.findClosest(map));
	 *     [a,b]
	 * </pre>
	 */
	public static List findClosest(Map[] objects)
	{
		if (objects==null || objects.length <=1 ){
			return null;
		}

		List<String> result =null;

		List<Map> tempMap = Arrays.asList(objects.clone());
		double minDist= Double.MAX_VALUE;
		for (Map object : objects){
			Collections.sort(tempMap, new DistanceComparator(object));

			double closestPoint=DistanceComparator.calculateDistance(object,tempMap.get(1));
			if (closestPoint<minDist){
				minDist = closestPoint;
				result = Arrays.asList(object.get("name").toString(), tempMap.get(1).get("name").toString());
			}
		}
		return result;
	}

	/**
	 * Analyze the HTTP access log and calculate the percentage of failed requests.
	 *
	 * <p>Please feel free to analyze the string instead of opening a file.
	 * If you do analyze the string, show how you would open files.
	 *
	 * <p>NOTE: assume only 200 and 404 error codes,
	 * but you may want to add support for other codes.
	 *
	 * <p>Sample access log content:
	 * <pre>
	 * 192.168.2.20 - - [28/Jul/2006:10:27:10 -0300] "GET /try/ HTTP/1.0" 200 3395
	 * 127.0.0.1 - - [28/Jul/2006:10:22:04 -0300] "GET / HTTP/1.0" 200 2216
	 * 127.0.0.1 - - [28/Jul/2006:10:27:32 -0300] "GET /hidden/ HTTP/1.0" 404 7218
	 * </pre>
	 *
	 * @param fileName 	The path to the log file.
	 * @return 			The list of IPs with the ratio for each.
	 *
	 * An example:
	 * <pre>
	 *     System.out.println(JavaTest.analyzeAccessLog("/var/log/httpd/access_log"));
	 *     {
	 *       127.0.0.1=0.5,
	 *       192.168.2.20=0,
	 *     }
	 * </pre>
	 */
	public static Map analyzeAccessLog(String fileName)
	{
		if (fileName == null || fileName.length() ==0){
			return null;
		}
		Map<String, AccessLog> map = new HashMap<>();
		Scanner scanner = new Scanner(fileName); // reading from file => new Scanner(new File(.../filename.txt))
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			// get the IP address
			String ip= AnalyzeAccessLogUtil.extractIPAddress(line);
			// get the status code
			ResponseCode responseCode=AnalyzeAccessLogUtil.extractResponseCode(line);
			// add it to the map
			AccessLog accessLog = map.containsKey(ip)? map.get(ip): new AccessLog();
			accessLog.increase(responseCode);
			map.put(ip,accessLog);
		}
		scanner.close();

		return AnalyzeAccessLogUtil.calculateResponseCodePercentage(map, ResponseCode.FAILURE);
	}

	/**
	**	DEBUGGING AND REFACTORING
	**		- Please debug the following function, using the included data set
	**		- Offer some brief thoughts about what was broken.
	**		- Offer some brief thoughts about the quality of the code.
	**		- When you're finished, try refactoring the code to make it better.
	**		- Make a new function to do this with, parseLogStats2 if you like.
	**		- Try to explain why you made some of the changes.
	*/

	/*
	//sample data set
	final String message =
	"[Notice][Todd] has entered the room
	[Notice][Todd] has exited the room
	[Notice][Todd] has entered the room
	[Message][Todd] wrote a 7 character message
	[Message][Ben] wrote a 17 character message
	[Message][Ben] wrote a 2 character message
	[Notice][Todd] has exited the room
	[Notice][Ben] has exited the room
	[Notice][Todd] has entered the room
	[Notice][Todd] has exited the room
	[Notice][Todd] has entered the room";
	 */
	//System.out.println(JavaTest.parseLogStats(message, "Todd"));

	/**
	 * This function parses text from a log file to determine some simple chat stats about a given user.
	 *
	 * @param log 		The string of log text to analyze.
	 * @param userName 	The user for who we want stats from
	 * @return			The stats for that user
	 *
	 * It counts
	 * 		the number of times they have exited the room,
	 * 		the number of times they have entered the room,
	 * 		and how many messages they have sent.
	 *
	 * <p>In addition it attempts to determine whether they are still in the room or not.
	 *
	 * <p>However, the function is very buggy. Most of the values it returns are wrong.
	 *
	 * <p>It may even generate error messages.
	 *
	 * <p>Using the example message, please find the bugs and correct them. Once you've debugged this code, you can also provide a refactored version.
	 *
	 */
	public static Map parseLogStats(String log, String userName) {

		Map UserStats = new HashMap();
		UserStats.put("Entries",(int) 0);
		UserStats.put("Exits",(int) 0);
		UserStats.put("StillHere", (boolean) false);
		UserStats.put("TotalMessages",(int) 0);

		if (!(log instanceof String)) { System.exit(0); }

		String[] LogArray = log.split("\\r\\n|\\n|\\r");


		//Get the number of times username left or entered the room
		// INDEXES ARE INCORRECT -> It generates ArrayIndexOutOfBoundsException (The indexes should be from 0 up to the array length)
		// for (int x = 1; x <= LogArray.length; x++)
		for (int x = 0; x < LogArray.length; x++)
		{
			if (LogArray[x].indexOf(userName) != -1)
			{
				if (LogArray[x].indexOf("entered") != -1)
				{
					UserStats.put("Entries", (int) UserStats.get("Entries") + 1);
				} else {
					// EXIT COUNTS IS INCORRECT (Sample data returns 4 exits for Todd which is wrong  )
					UserStats.put("Exits", (int) UserStats.get("Entries") + 1);
				}

			}
		}


		//Decide if the user is still in the room or not
		// NOT NECESSARILY CORRECT (If user enters first, with below condition, the "StillHere" value should return false)
		if (UserStats.get("Exits") == UserStats.get("Entries"))
		{
			UserStats.put("StillHere", true);
		}


		//Count the number of messages sent by the user
		// RETURNS A WRONG VALUE, for the sample data it returns 11 which is equal to the total number of lines (It does not even uses "LogLine" in the loop!!!)
		for (String LogLine : LogArray)
		{
			if (LogArray[0].indexOf(userName) != -1)
			{
				UserStats.put("TotalMessages", (int)UserStats.get("TotalMessages") + 1);
			}
		}

		return UserStats;

	}

	public static Map parseLogStats2(String log, String userName) {
		if (log == null || log.length() == 0){
			return null;
		}

		if (userName == null || userName.length() == 0){
			return null;
		}

		User user=new User();

		Scanner scanner = new Scanner(log);
		boolean typeReceived;
		String type;
		String userNameExtracted;
		while (scanner.hasNextLine()) {
			String line= scanner.nextLine();

			UserLog userLog= ParseStatLogUtil.geLogInfo(line);

			if (userLog.getUsername().equals(userName)){
				switch (userLog.getLogType()){
					case NOTICE:
						if (line.contains("has entered")){
							user.entered();
						} else if (line.contains("has exited")){
							user.exited();
						}
						break;
					case MESSAGE:
						user.increaseTotalMessages();
						break;
				}
			}

		}
		scanner.close();

		Map userStats = new HashMap();
		userStats.put("Entries", user.getEnterCount());
		userStats.put("Exits", user.getExitCount());
		userStats.put("StillHere", user.isStillHere());
		userStats.put("TotalMessages", user.getTotalMessages());

		return userStats;
	}
}