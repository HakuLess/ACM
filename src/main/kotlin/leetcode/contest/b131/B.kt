package leetcode.contest.b131

class SolutionB {
    fun occurrencesOfElement(nums: IntArray, queries: IntArray, x: Int): IntArray {
        val map = HashMap<Int, Int>()
        var c = 1
        for (i in nums.indices) {
            if (nums[i] == x) {
                map[c] = i
                c++
            }
        }
        return queries.map {
            map.getOrDefault(it, -1)
        }.toIntArray()
    }
}