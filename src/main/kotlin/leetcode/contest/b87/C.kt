package leetcode.contest.b87

import utils.print

fun main() {
    val s = SolutionC()
    s.smallestSubarrays(intArrayOf(1, 0, 2, 1, 3)).print()
    s.smallestSubarrays(intArrayOf(1, 2)).print()
    s.smallestSubarrays(intArrayOf(0)).print()
}

class SolutionC {
    fun smallestSubarrays(nums: IntArray): IntArray {
        val ors = IntArray(nums.size)
        for (i in ors.indices.reversed()) {
            if (i == ors.lastIndex) {
                ors[i] = nums[i]
            } else {
                ors[i] = nums[i] or ors[i + 1]
            }
        }
        val ans = ArrayList<Int>()
        var l = 0
        var r = 0
        val cur = IntArray(40)
        while (l in nums.indices) {
            while (r in nums.indices && cur.biToInt() != ors[l]) {
                nums[r].toString(2).reversed().forEachIndexed { index, c ->
                    if (c == '1') {
                        cur[index]++
                    }
                }
                r++
            }
            nums[l].toString(2).reversed().forEachIndexed { index, c ->
                if (c == '1') {
                    cur[index]--
                }
            }
            l++
            r = maxOf(r, l)
            ans.add(r - l + 1)
        }
        return ans.toIntArray()
    }

    private fun IntArray.biToInt(): Int {
        var ans = 0
        var step = 1
        forEach {
            if (it != 0) {
                ans += step
            }
            step *= 2
        }
        return ans
    }
}