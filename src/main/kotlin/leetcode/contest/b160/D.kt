package leetcode.contest.b160

import utils.biMin
import utils.gcd
import utils.print

fun main() {
    val s = SolutionD()
    // 0
    s.minStable(intArrayOf(6, 5), 2).print()
    // 0
    s.minStable(intArrayOf(1, 3), 1).print()

    // 1
    s.minStable(intArrayOf(3, 5, 10), 1).print()
    // 1
    s.minStable(intArrayOf(2, 6, 8), 2).print()
    // 2
    s.minStable(intArrayOf(2, 4, 9, 6), 1).print()

    // 1
    s.minStable(intArrayOf(14, 2, 22), 1).print()

    // 4
    s.minStable(intArrayOf(169, 33, 168, 208, 184, 142), 0).print()
}

// TODO Not Finished
// TODO 倍增法 预处理ST表 or SegmentTree 区间GCD
// GCD 不是顺序计算，可能存在左侧右侧因子不同情况
class SolutionD {
    fun minStable(nums: IntArray, maxC: Int): Int {

        fun helper(arr: IntArray): Int {
            var curGCD = -1
            var cnt = 0
            val l = ArrayList<Int>()
            for (i in arr.indices) {
                if (arr[i] == 1) {
                    l.add(cnt)
                    curGCD = -1
                    cnt = 0
                } else if (curGCD == -1) {
                    curGCD = arr[i]
                    cnt = 1
                } else {
                    val gcd = gcd(curGCD, arr[i])
                    if (gcd == 1) {
                        l.add(cnt)
                        curGCD = arr[i]
                        cnt = 1
                    } else {
                        cnt++
                        curGCD = gcd
                    }
                }
            }
            l.add(cnt)

            l.joinToString().print()

            return biMin(0L, Long.MAX_VALUE) { max ->
                var total = 0L
                l.forEach {
                    if (it <= max) return@forEach

                    val hasMore = ((it + 1) % (max + 1) != 0L)
                    var cnt = (it + 1) / (max + 1)
                    if (!hasMore) {
                        cnt--
                    }
                    total += cnt
                }
                total <= maxC
            }.toInt()
        }

        val a = helper(arr = nums)
        val b = helper(arr = nums.reversed().toIntArray())
        return maxOf(a, b)
    }
}