package leetcode.contest.c379

import utils.print

fun main() {
    val s = SolutionC()
    // 2
    s.maximumSetSize(intArrayOf(1, 2, 1, 2), intArrayOf(1, 1, 1, 1)).print()
    // 5
    s.maximumSetSize(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(2, 3, 2, 3, 2, 3)).print()
    // 3
    s.maximumSetSize(intArrayOf(1, 1, 1, 1), intArrayOf(12, 23, 41, 9)).print()
    // 4
    s.maximumSetSize(intArrayOf(2, 4, 1, 4), intArrayOf(10, 2, 4, 10)).print()
}

class SolutionC {
    fun maximumSetSize(nums1: IntArray, nums2: IntArray): Int {
        val n = nums1.size
        var a = n / 2
        var b = n / 2
        var ans = 0
        val setA = nums1.toHashSet()
        val setB = nums2.toHashSet()
        setA.forEach {
            if (it !in setB && a > 0) {
                a--
                ans++
            }
        }
        setB.forEach {
            if (it !in setA && b > 0) {
                b--
                ans++
            }
        }

        a = minOf(a, setA.size - (n / 2 - a))
        b = minOf(b, setB.size - (n / 2 - b))

        val set = HashSet<Int>()
        set.addAll(setA)
        set.addAll(setB)
//        println("ans is $ans with $a $b ${set.size}")
        return ans + minOf(a + b, set.size - ans)
    }
}