package leetcode.normal

import utils.Bits
import utils.countOne
import utils.print

fun main() {
    val s = Solution805()
//    s.splitArraySameAverage(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)).print()
//    s.splitArraySameAverage(intArrayOf(5, 3, 11, 19, 2)).print()
    s.splitArraySameAverage(intArrayOf(18, 0, 16, 2)).print()
}

// 状态压缩
// 折半法
// size为30的，先左15 右15拆
class Solution805 {
    fun splitArraySameAverage(nums: IntArray): Boolean {
        val sum = nums.sum()

        val n = nums.size / 2 + nums.size % 2
        val m = nums.size - n

        val left = Array<HashSet<Int>>(n + 1) { hashSetOf() }
        val right = Array<HashSet<Int>>(m + 1) { hashSetOf() }

        fun helper(n: Int, offset: Int, arr: Array<HashSet<Int>>) {
            val bits = Bits(n)
            bits.eachMask { mask ->
                var cur = 0
                bits.eachBit(mask) { index, b ->
                    if (b) {
                        cur += nums[offset + index]
                    }
                }
                arr[mask.countOne()].add(cur)
            }
        }

        helper(n, 0, left)
        helper(m, n, right)

        for (i in left.indices) {
            for (j in right.indices) {
                if (i + j == nums.size || i + j == 0) continue
                if (left[i].any { l ->
                        right[j].any { r ->
                            (l + r) * nums.size == (i + j) * sum
                        }
                    }) {
                    return true
                }
            }
        }
        return false
    }
}