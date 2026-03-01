package leetcode.contest.c491

import utils.print

fun main() {
    val s = SolutionD()
    s.countSubarrays(intArrayOf(1, 2, 1, 2, 2), 2, 2).print()
    s.countSubarrays(intArrayOf(3, 1, 2, 4), 2, 1).print()
}

class SolutionD {
    fun countSubarrays(nums: IntArray, k: Int, m: Int): Long {
        var maxVal = 0
        for (x in nums) {
            if (x > maxVal) maxVal = x
        }

        val sizes = IntArray(maxVal + 1)
        for (x in nums) {
            sizes[x]++
        }

        val offsets = IntArray(maxVal + 1)
        var currentOffset = 0
        for (i in 0..maxVal) {
            offsets[i] = currentOffset
            currentOffset += sizes[i]
        }

        val occ = IntArray(nums.size)
        val ptrs = offsets.copyOf()
        for (i in nums.indices) {
            occ[ptrs[nums[i]]++] = i
        }

        var N = 1
        while (N <= maxVal) {
            N *= 2
        }
        val tree = IntArray(2 * N) { Int.MAX_VALUE }

        fun updateST(idx: Int, value: Int) {
            var p = idx + N
            tree[p] = value
            p /= 2
            while (p > 0) {
                tree[p] = minOf(tree[2 * p], tree[2 * p + 1])
                p /= 2
            }
        }

        val seenCount = IntArray(maxVal + 1)
        val pos = IntArray(maxVal + 1) { -1 }
        val freq1 = IntArray(maxVal + 1)
        val freq2 = IntArray(maxVal + 1)

        var left1 = 0
        var left2 = 0
        var distinct1 = 0
        var distinct2 = 0
        var ans: Long = 0

        for (R in nums.indices) {
            val x = nums[R]
            seenCount[x]++

            if (seenCount[x] >= m) {
                pos[x] = occ[offsets[x] + seenCount[x] - m]
            } else {
                pos[x] = -1
            }

            if (freq1[x] == 0) {
                distinct1++
                updateST(x, pos[x])
            } else {
                updateST(x, pos[x])
            }
            freq1[x]++

            if (freq2[x] == 0) {
                distinct2++
            }
            freq2[x]++

            while (distinct1 > k) {
                val y = nums[left1]
                freq1[y]--
                if (freq1[y] == 0) {
                    distinct1--
                    updateST(y, Int.MAX_VALUE)
                }
                left1++
            }

            while (distinct2 > k - 1) {
                val y = nums[left2]
                freq2[y]--
                if (freq2[y] == 0) {
                    distinct2--
                }
                left2++
            }

            if (distinct1 == k) {
                val mMin = tree[1]
                val maxValidL = minOf(left2 - 1, mMin)

                if (maxValidL >= left1) {
                    ans += (maxValidL - left1 + 1).toLong()
                }
            }
        }

        return ans
    }
}