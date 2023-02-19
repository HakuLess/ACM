package leetcode.contest.c333

class SolutionA {
    fun mergeArrays(nums1: Array<IntArray>, nums2: Array<IntArray>): Array<IntArray> {
        val map = HashMap<Int, Int>()
        nums1.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0) + it[1]
        }
        nums2.forEach {
            map[it[0]] = map.getOrDefault(it[0], 0) + it[1]
        }
        val ans = ArrayList<IntArray>()
        map.forEach { t, u ->
            ans.add(intArrayOf(t, u))
        }
        ans.sortBy { it[0] }
        return ans.toTypedArray()
    }
}