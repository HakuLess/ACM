package leetcode.normal

class Solution2404 {
    fun mostFrequentEven(nums: IntArray): Int {
        return nums.filter { it % 2 == 0 }.groupBy { it }.map { Pair(it.key, it.value.size) }
                   .sortedWith(compareBy({ -it.second }, { it.first })).firstOrNull()?.first ?: -1
    }
}