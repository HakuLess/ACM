package leetcode.contest.b158

import utils.print

fun main() {
    val s = SolutionD()
    s.goodSubtreeSum(intArrayOf(2, 3), intArrayOf(-1, 0)).print()
    s.goodSubtreeSum(intArrayOf(1, 5, 2), intArrayOf(-1, 0, 0)).print()
    s.goodSubtreeSum(intArrayOf(34, 1, 2), intArrayOf(-1, 0, 1)).print()
    s.goodSubtreeSum(intArrayOf(3, 22, 5), intArrayOf(-1, 0, 1)).print()
}

class SolutionD {
    fun goodSubtreeSum(vals: IntArray, par: IntArray): Int {
        val mod = 1_000_000_007L
        val n = vals.size
        val tree = Array(n) { mutableListOf<Int>() }

        for (i in 1 until n) {
            tree[par[i]].add(i)
        }

        fun getMask(num: Int): Int {
            var mask = 0
            var temp = num
            while (temp > 0) {
                val d = temp % 10
                if ((mask shr d) and 1 == 1) return -1
                mask = mask or (1 shl d)
                temp /= 10
            }
            return mask
        }

        val maskMap = Array(n) { HashMap<Int, Long>() }

        fun dfs(u: Int) {
            val currVal = vals[u]
            val currMask = getMask(currVal)

            if (currMask != -1) {
                maskMap[u][currMask] = currVal.toLong()
            }

            for (v in tree[u]) {
                dfs(v)
                val newDp = HashMap<Int, Long>()

                for ((mask1, sum1) in maskMap[u]) {
                    for ((mask2, sum2) in maskMap[v]) {
                        if (mask1 and mask2 == 0) {
                            val combinedMask = mask1 or mask2
                            val combinedSum = sum1 + sum2
                            newDp[combinedMask] = maxOf(newDp.getOrDefault(combinedMask, 0L), combinedSum)
                        }
                    }
                }

                for ((mask, sum) in maskMap[v]) {
                    maskMap[u][mask] = maxOf(maskMap[u].getOrDefault(mask, 0), sum)
                }

                for ((mask, sum) in newDp) {
                    maskMap[u][mask] = maxOf(maskMap[u].getOrDefault(mask, 0), sum)
                }
            }
        }

        dfs(0)

        var ans = 0L
        for (i in 0 until n) {
            val max = maskMap[i].values.maxOrNull() ?: 0
            ans = (ans + max) % mod
        }

        return ans.toInt()
    }
}