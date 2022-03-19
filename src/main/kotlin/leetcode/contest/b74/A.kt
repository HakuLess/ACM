package leetcode.contest.b74

class SolutionA {
    fun divideArray(nums: IntArray): Boolean {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
        }
        return map.all { it.value % 2 == 0 }
    }
}