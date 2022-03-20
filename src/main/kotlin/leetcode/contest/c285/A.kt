package leetcode.contest.c285

class SolutionA {
    fun countHillValley(nums: IntArray): Int {
        val dis = ArrayList<Int>()
        dis.add(nums[0])
        for (i in 1 until nums.size) {
            if (nums[i] == nums[i - 1]) continue
            dis.add(nums[i])
        }
        var ans = 0
        for (i in 1 until dis.lastIndex) {
            if (dis[i] > dis[i - 1] && dis[i] > dis[i + 1]) ans++
            if (dis[i] < dis[i - 1] && dis[i] < dis[i + 1]) ans++
        }
        return ans
    }
}