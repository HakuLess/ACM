package leetcode.contest.b177

import utils.print

fun main() {
    val s = SolutionC()
    // [1, 1]
    s.makeParityAlternating(intArrayOf(-1, 0, 0)).print()
    // [2, 1]
    s.makeParityAlternating(intArrayOf(-4, -4, -3, -5, -4)).print()
    // [2, 16]
    s.makeParityAlternating(intArrayOf(9, -8, -2, 9)).print()
    // [3, 1]
    s.makeParityAlternating(intArrayOf(1, 1, 2, 2, 1, 1)).print()
}

class SolutionC {
    fun makeParityAlternating(nums: IntArray): IntArray {
        val n = nums.size

        fun solve(startEven: Boolean): Pair<Int, Long> {
            val mustFlip = BooleanArray(n)
            var ops = 0

            for (i in 0 until n) {
                val shouldBeEven = if (i % 2 == 0) startEven else !startEven
                val isEven = nums[i] % 2 == 0
                if (isEven != shouldBeEven) {
                    mustFlip[i] = true
                    ops++
                }
            }

            var min0 = Long.MAX_VALUE
            var max0 = Long.MIN_VALUE
            for (v in nums) {
                min0 = minOf(min0, v.toLong())
                max0 = maxOf(max0, v.toLong())
            }

            var best = Long.MAX_VALUE

            for (dMin in -1..1) {
                for (dMax in -1..1) {

                    val L = min0 + dMin
                    val R = max0 + dMax
                    if (L > R) continue

                    var valid = true

                    for (i in 0 until n) {
                        if (!mustFlip[i]) {
                            if (nums[i] < L || nums[i] > R) {
                                valid = false
                                break
                            }
                        } else {
                            val a = nums[i] - 1L
                            val b = nums[i] + 1L
                            if (!(a in L..R || b in L..R)) {
                                valid = false
                                break
                            }
                        }
                    }

                    if (valid) {
                        best = minOf(best, R - L)
                    }
                }
            }

            return Pair(ops, best)
        }

        val (opsA, rangeA) = solve(true)
        val (opsB, rangeB) = solve(false)

        return when {
            opsA < opsB -> intArrayOf(opsA, rangeA.toInt())
            opsB < opsA -> intArrayOf(opsB, rangeB.toInt())
            else -> intArrayOf(opsA, minOf(rangeA, rangeB).toInt())
        }
    }
}