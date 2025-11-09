package leetcode.normal

class Solution1578 {
    fun minCost(colors: String, neededTime: IntArray): Int {
        var ans = 0
        for (i in 1 until (neededTime.size)) {
            if (colors[i] == colors[i - 1]) {
                ans += minOf(neededTime[i], neededTime[i - 1])
                neededTime[i] = maxOf(neededTime[i], neededTime[i - 1])
            }
        }
        return ans
    }
}