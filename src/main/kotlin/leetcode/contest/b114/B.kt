package leetcode.contest.b114

class SolutionB {
    fun minOperations(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        var ans = 0
        map.values.forEach {
            if (it == 1) return -1
            ans += (it + 2) / 3
        }
        return ans
    }
}