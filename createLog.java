/* *****************************************************************************
 *  Name:              Jaya Mukherjee
 *  Last modified:     April 15, 2022
 **************************************************************************** */


/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

For example:
logs1 = [
    ["58523", "user_1", "resource_1"],
    ["62314", "user_2", "resource_2"],
    ["54001", "user_1", "resource_3"],
    ["200", "user_6", "resource_5"],
    ["215", "user_6", "resource_4"],
    ["54060", "user_2", "resource_3"],
    ["53760", "user_3", "resource_3"],
    ["58522", "user_22", "resource_1"],
    ["53651", "user_5", "resource_3"],
    ["2", "user_6", "resource_1"],
    ["100", "user_6", "resource_6"],
    ["400", "user_7", "resource_2"],
    ["100", "user_8", "resource_6"],
    ["54359", "user_1", "resource_3"],
]


We would like to compute user sessions, specifically: write a function that takes the logs and returns a data structure that associates to each user their earliest and latest access times.

Example:
{'user_1': [54001, 58523],
 'user_2': [54060, 62314],
 'user_3': [53760, 53760],
 'user_5': [53651, 53651],
 'user_6': [2, 215],
 'user_7': [400, 400],
 'user_8': [100, 100],
 'user_22': [58522, 58522],
}

Example 2:
logs2 = [
    ["300", "user_1", "resource_3"],
    ["599", "user_1", "resource_3"],
    ["900", "user_1", "resource_3"],
    ["1199", "user_1", "resource_3"],
    ["1200", "user_1", "resource_3"],
    ["1201", "user_1", "resource_3"],
    ["1202", "user_1", "resource_3"]
]

Should return:
{'user_1': [300, 1202]}

Example 3:
logs3 = [
    ["300", "user_10", "resource_5"]
]

Should return:
{'user_10': [300, 300]}

Complexity analysis variables:

n: number of logs in the input
*/

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class createLog {
    public static void main(String[] argv) {
        String[][] logs1 = new String[][] {
                { "58523", "user_1", "resource_1" },
                { "62314", "user_2", "resource_2" },
                { "54001", "user_1", "resource_3" },
                { "200", "user_6", "resource_5" },
                { "215", "user_6", "resource_4" },
                { "54060", "user_2", "resource_3" },
                { "53760", "user_3", "resource_3" },
                { "58522", "user_22", "resource_1" },
                { "53651", "user_5", "resource_3" },
                { "2", "user_6", "resource_1" },
                { "100", "user_6", "resource_6" },
                { "400", "user_7", "resource_2" },
                { "100", "user_8", "resource_6" },
                { "54359", "user_1", "resource_3" },
                };

        String[][] logs2 = new String[][] {
                { "300", "user_1", "resource_3" },
                { "599", "user_1", "resource_3" },
                { "900", "user_1", "resource_3" },
                { "1199", "user_1", "resource_3" },
                { "1200", "user_1", "resource_3" },
                { "1201", "user_1", "resource_3" },
                { "1202", "user_1", "resource_3" }
        };

        String[][] logs3 = new String[][] {
                { "300", "user_10", "resource_5" }
        };


        TreeMap<String, String[]> map = returnLog(logs1);

        for (Map.Entry<String, String[]> e : map.entrySet()) {
            System.out.println(e.getKey() + ": " + Arrays.toString(e.getValue()));
        }


    }

    public static TreeMap<String, String[]> returnLog(String[][] logs) {

        /*Arrays.sort(logs, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String user1 = entry1[1];
                final String user2 = entry2[1];
                return user1.compareTo(user2);
            }
        });

        Arrays.sort(logs, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[0];
                final String time2 = entry2[0];
                return time1.compareTo(time2);
            }
        });
        for (final String[] s : logs) {
            System.out.println(s[0] + " " + s[1]);
        }



  {
    user_1: [earliest, latest],
    user_2: [earliest, latest],
    ...
  }
*/


        TreeMap<String, String[]> map = new TreeMap<>();

        for (String[] log : logs) {
            String user = log[1];
            if (map.containsKey(user)) {
                String[] val = map.get(user);
                if (val[0].equals(val[1])) {
                    int val1 = Integer.parseInt(log[0]);
                    int val2 = Integer.parseInt(val[0]);
                    if (val1 > val2) {
                        val[1] = Integer.toString(val1);
                    }
                    else {
                        int temp = val2;
                        val[0] = Integer.toString(val1);
                        val[1] = Integer.toString(temp);
                    }

                }
                else {
                    int val1 = Integer.parseInt(log[0]);
                    int val2 = Integer.parseInt(val[0]);
                    int val3 = Integer.parseInt(val[1]);
                    if (val1 < val2) {
                        val[0] = Integer.toString(val1);
                    }
                    else if (val1 > val3) {
                        val[1] = Integer.toString(val1);
                    }
                }
                map.put(user, val);
            }
            else {
                String[] arr = new String[2];
                arr[0] = log[0];
                arr[1] = log[0];
                map.put(user, arr);
            }
        }


        return map;
    }
}

