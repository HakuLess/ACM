package leetcode.contest.b116

import utils.print


fun main() {
    val s = SolutionD()
    s.sumCounts(intArrayOf(1, 2, 1)).print()
    s.sumCounts(intArrayOf(2, 2)).print()
    s.sumCounts(intArrayOf(1, 2, 3, 4)).print()
}

// 不会
class SolutionD {
    class ST(var s: Int, var e: Int) {
        var sum: Long = 0
        var stash: Long = 0
        var l: ST? = null
        var r: ST? = null
        fun pushStash(sta: Long) {
            stash += sta
            sum += (e - s + 1) * sta
        }

        fun add(ss: Int, ee: Int) {
            if (ss <= s && ee >= e) {
                sum += (e - s + 1)
                stash++
            } else {
                if (stash > 0) {
                    l!!.pushStash(stash)
                    r!!.pushStash(stash)
                    stash = 0
                }
                val mid = (s + e) / 2
                if (mid >= ss) {
                    l!!.add(ss, ee)
                }
                if (mid < ee) {
                    r!!.add(ss, ee)
                }
                sum = l!!.sum + r!!.sum
            }
        }

        fun getSum(ss: Int, ee: Int): Long {
            return if (ss <= s && ee >= e) {
                sum
            } else {
                var ret: Long = 0
                if (stash > 0) {
                    l!!.pushStash(stash)
                    r!!.pushStash(stash)
                    stash = 0
                }
                val mid = (s + e) / 2
                if (mid >= ss) {
                    ret += l!!.getSum(ss, ee)
                }
                if (mid < ee) {
                    ret += r!!.getSum(ss, ee)
                }
                ret
            }
        }

        init {
            if (s < e) {
                val mid = (s + e) / 2
                l = ST(s, mid)
                r = ST(mid + 1, e)
            }
        }
    }

    fun sumCounts(nums: IntArray): Int {
        val n = nums.size
        val last = IntArray(n)
        val mp: MutableMap<Int, Int> = HashMap()
        for (i in 0 until n) {
            last[i] = mp.getOrDefault(nums[i], -1)
            mp[nums[i]] = i
        }
        val st = ST(0, n - 1)
        val mod: Long = 1000000007
        var ret = 0L
        var cur = 0L
        for (i in 0 until n) {
            val t = last[i]
            val curSum = st.getSum(t + 1, i)
            val cou = i - t
            cur += curSum * 2 + cou
            cur %= mod
            ret += cur
            ret %= mod
            st.add(t + 1, i)
        }
        return ret.toInt()
    }
}