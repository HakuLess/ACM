package leetcode.contest.c277

class SolutionC {
    fun findLonely(nums: IntArray): List<Int> {
        val map = IntArray(1000005)
        nums.forEach {
            map[it]++
        }
        val ans = arrayListOf<Int>()
        for (i in map.indices) {
            if (map[i] == 1 &&
                (i + 1 !in map.indices || map[i + 1] == 0) &&
                (i - 1 !in map.indices || map[i - 1] == 0)
            ) {
                ans.add(i)
            }
        }
        return ans
    }
}