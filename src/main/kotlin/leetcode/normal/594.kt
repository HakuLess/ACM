package leetcode.normal

class Solution594 {
    fun findLHS(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        var ans = 0
        map.keys.sorted().forEach {
            val a = map.getOrDefault(it, 0)
            val b = map.getOrDefault(it + 1, 0)
            if (a != 0 && b != 0) {
                ans = maxOf(ans, a + b)
            }
        }
        return ans
    }
}