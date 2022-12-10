package leetcode.contest.b93

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumTotalCost(intArrayOf(1, 2, 2), intArrayOf(2, 1, 2)).print()
}

class SolutionD {
    fun minimumTotalCost(nums1: IntArray, nums2: IntArray): Long {

        val m1 = HashMap<Int, Int>()
        val m2 = HashMap<Int, Int>()
        for (i in nums1.indices) {
            m1[nums1[i]] = m1.getOrDefault(nums1[i], 0) + 1
            m2[nums2[i]] = m2.getOrDefault(nums2[i], 0) + 1
        }
//        val max = m1.keys.maxBy { m1[it]!! }!!
        val max = m1.keys.maxOf { m1[it]!! }
        if (m1[max]!! > nums1.size / 2 && m2.getOrDefault(max, 0) > nums2.size / 2) return -1

        var ans = 0L
        val map = HashMap<Int, Int>()
        var c = 0
        val set = HashSet<Int>()
        for (i in nums1.indices) {
            if (nums1[i] == nums2[i]) {
                ans += i
                c++
                set.add(i)
                map[nums1[i]] = map.getOrDefault(nums1[i], 0) + 1
            }
        }
        val lst = ArrayList<Int>()
        lst.addAll(nums1.mapIndexed { index, i ->
            index
        }.filter { it !in set })

        while (map.values.any { it > c / 2 }) {
            if (lst.isEmpty()) return -1
            val f = lst.removeAt(0)
            val v = nums1[f]
            if (map.getOrDefault(v, 0) <= c / 2) {
                map[v] = map.getOrDefault(v, 0) + 1
                c++
                ans += f
            }
        }
        return ans
    }
}