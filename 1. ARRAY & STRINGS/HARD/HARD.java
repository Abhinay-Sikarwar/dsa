//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 1. ARRAYS & STRINGS
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 76: MINIMUM WINDOW SUBSTRING
// SLIDING WINDOW, FREQUENCY TRACKING
    class Solution {
        public String minWindow(String s, String t) {
            int m = s.length();
            int n = t.length();

            if (m < n) return "";

            int[] targetFreq = new int[128];
            for (char c : t.toCharArray()) {
                targetFreq[c]++;
            }

            int[] windowFreq = new int[128];
            int left = 0, right = 0;
            int start = 0, minLen = Integer.MAX_VALUE;
            int required = 0; // Total characters matched

            while (right < m) {
                char rChar = s.charAt(right);
                windowFreq[rChar]++;

                if (targetFreq[rChar] > 0 && windowFreq[rChar] <= targetFreq[rChar]) {
                    required++;
                }

                while (required == n) {
                    // Update minimum window
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        start = left;
                    }

                    char lChar = s.charAt(left);
                    windowFreq[lChar]--;

                    if (targetFreq[lChar] > 0 && windowFreq[lChar] < targetFreq[lChar]) {
                        required--;
                    }

                    left++;
                }

                right++;
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
        }
    } 
    
    // ⏱️ Time Complexity:  O(m + n)      Where   m = length of s
    // 🧠 Space Complexity:  O(1)                 n = length of t

    // 57: INSERT INTERVAL
// COMPARISION OF INTERVALS, MERGE THEM IF NEEDED
    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> result = new ArrayList<>();

            for (int[] interval : intervals) {
                if (newInterval[1] < interval[0]) {
                    result.add(newInterval);
                    newInterval = interval;
                } else if (newInterval[0] > interval[1]) {
                    result.add(interval);
                } else {
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                }
            }

            result.add(newInterval);
            return result.toArray(new int[result.size()][]);
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)     Where n is the number of intervals.

    // 42: TRAPPING RAIN WATER
// OPTIMIZED TWO POINTER WITH CONSTANT SPACE
    class Solution {
        public int trap(int[] height) {
            if (height == null || height.length < 3) return 0;

            int left = 0, right = height.length - 1;
            int leftMax = height[left], rightMax = height[right];
            int waterTrapped = 0;

            while (left < right) {
                if (height[left] < height[right]) {
                    left++;
                    leftMax = Math.max(leftMax, height[left]);
                    waterTrapped += leftMax - height[left];
                } else {
                    right--;
                    rightMax = Math.max(rightMax, height[right]);
                    waterTrapped += rightMax - height[right];
                }
            }

            return waterTrapped;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)
    
    // 224: BASIC CALCULATOR
// RECURSION
    class Solution {
        public int calculate(String s) {
            return helper(s.toCharArray(), new int[]{0});
        }

        private int helper(char[] s, int[] index) {
            int num = 0, result = 0, sign = 1;

            while (index[0] < s.length) {
                char ch = s[index[0]];

                if (Character.isDigit(ch)) {
                    num = 0;
                    while (index[0] < s.length && Character.isDigit(s[index[0]])) {
                        num = num * 10 + (s[index[0]] - '0');
                        index[0]++;
                    }
                    result += sign * num;
                } else if (ch == '+') {
                    sign = 1;
                    index[0]++;
                } else if (ch == '-') {
                    sign = -1;
                    index[0]++;
                } else if (ch == '(') {
                    index[0]++;
                    num = helper(s, index);
                    result += sign * num;
                } else if (ch == ')') {
                    index[0]++;
                    return result;
                } else if (ch == ' ') {
                    index[0]++;
                } else {
                    index[0]++;
                }
            }

            return result;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)