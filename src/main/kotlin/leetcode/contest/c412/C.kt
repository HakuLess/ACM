package leetcode.contest.c412

import utils.print
import utils.quickPower
import java.util.*

fun main() {
    val s = SolutionC()
    s.getFinalState(intArrayOf(2, 1, 3, 5, 6), 5, 2).print()
//    s.getFinalState(intArrayOf(1), 3, 10).print()
//    s.getFinalState(intArrayOf(2, 5), 5, 4).print()
    // [198168519]
//    s.getFinalState(intArrayOf(161209470), 56851412, 39846).print()
    // [664480092,763599523,886046925,47878852]
//    s.getFinalState(intArrayOf(66307295, 441787703, 589039035, 322281864), 900900704, 641725).print()
    // [940635593,321154185,859014034,48349682,391180073]
    s.getFinalState(intArrayOf(889458628, 338743558, 875422936, 684907163, 233489834), 246181588, 313380).print()
}

class SolutionC {
    fun getFinalState(nums: IntArray, k: Int, multiplier: Int): IntArray {
        if (multiplier == 1) return nums

        val mod = 1000000007L
        val pq = PriorityQueue<Triple<Long, Long, Int>>(compareBy({ it.first }, { it.second }, { it.third }))
        for (i in nums.indices) {
            pq.offer(Triple(0L, nums[i].toLong(), i))
        }

        // 初始状态最大值
        val max = nums.maxOrNull()!!

        fun triggerOnce() {
            val minItem = pq.poll()
            var sec = minItem.second * multiplier
            var fir = minItem.first
            if (sec >= mod) {
                fir += sec / mod
                sec %= mod
            }
            pq.offer(Triple(fir, sec, minItem.third))
        }

        var leftK = k

        while (pq.peek().second * multiplier <= max && leftK > 0) {
            triggerOnce()
            leftK--
        }

        // 已改变整体最大值
        val powTimes = leftK / nums.size
        leftK %= (nums.size)

        val pow = quickPower(base = multiplier.toLong(), pow = powTimes.toLong(), m = mod)

        repeat(leftK) {
            triggerOnce()
        }

        val ans = IntArray(nums.size)
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            ans[item.third] = ((item.second.toInt() * pow) % mod).toInt()
        }
        return ans
    }
}