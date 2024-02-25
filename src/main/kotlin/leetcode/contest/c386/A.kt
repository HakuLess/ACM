package leetcode.contest.c386

class SolutionA {
    fun isPossibleToSplit(nums: IntArray): Boolean {
        val map = HashMap<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        return map.values.all { it <= 2 }
    }
}