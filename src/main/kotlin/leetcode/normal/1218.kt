package leetcode.normal

class Solution1218 {
    fun longestSubsequence(arr: IntArray, difference: Int): Int {
        val map = HashMap<Int, Int>()
        arr.forEach {
            map[it] = maxOf(map.getOrDefault(it, 0), map.getOrDefault(it - difference, 0) + 1)
        }
//        return map.values.max()!!
        return map.values.maxOrNull()!!
    }
}