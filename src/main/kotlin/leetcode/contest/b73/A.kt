package leetcode.contest.b73

class SolutionA {
    fun mostFrequent(nums: IntArray, key: Int): Int {
        val map = HashMap<Int, Int>()
        for (i in 0 until nums.lastIndex) {
            if (nums[i] == key) {
                map[nums[i + 1]] = map.getOrDefault(nums[i + 1], 0) + 1
            }
        }
//        return map.maxBy { it.value }!!.key
        return map.maxByOrNull { it.value }!!.key
    }
}