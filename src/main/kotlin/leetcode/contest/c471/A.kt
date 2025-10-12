package leetcode.contest.c471

class SolutionA {
    fun sumDivisibleByK(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        var ans = 0
        map.forEach { key, v ->
            if (v % k == 0) {
                ans += key * v
            }
        }
        return ans
    }
}