package leetcode.contest.c422

import utils.permute
import utils.permuteUnique
import utils.print

fun main() {
    val s = SolutionD()
    s.countBalancedPermutations("123").print()
    s.countBalancedPermutations("112").print()
    s.countBalancedPermutations("12345").print()
    s.countBalancedPermutations("7160").print()
}

class SolutionD {
    private companion object {
        const val MOD = 1_000_000_007
    }

    private class InternalData(num: String) {
        val dp: Array<Array<Array<Int?>>>
        val combine: Array<LongArray>
        val zeroSum: Int
        val nums: IntArray

        init {
            nums = IntArray(10)
            for (char in num) {
                nums[char - '0'] += 1
            }
            zeroSum = 10 * num.length
            dp = Array(2 * num.length * 10) { Array(10) { arrayOfNulls<Int>(num.length / 2 + num.length % 2 + 1) } }
            combine = calculateCombine(num.length / 2 + 1)
        }

        private fun calculateCombine(max: Int): Array<LongArray> {
            val res = Array(max + 1) { LongArray(max + 1) }
            res[0][0] = 1
            for (i in 1..max) {
                for (j in 0..i) {
                    res[i][j] = when {
                        j == 0 || j == i -> 1
                        j == 1 -> i.toLong()
                        else -> (res[i - 1][j - 1] + res[i - 1][j]) % MOD
                    }
                }
            }
            return res
        }
    }

    fun countBalancedPermutations(num: String): Int {
        val data = InternalData(num)
        return dfs(0, 0, num.length / 2 + num.length % 2, num.length / 2, data)
    }

    private fun dfs(sum: Int, pos: Int, oddRemain: Int, evenRemain: Int, data: InternalData): Int {
        if (pos == 10) {
            return if (oddRemain == 0 && evenRemain == 0 && sum == 0) 1 else 0
        } else if (data.dp[sum + data.zeroSum][pos][oddRemain] == null) {
            data.dp[sum + data.zeroSum][pos][oddRemain] = 0
            for (i in 0..minOf(oddRemain, data.nums[pos])) {
                if (evenRemain >= data.nums[pos] - i) {
                    val nextSum = sum + i * pos - (data.nums[pos] - i) * pos
                    val nextOddRemain = oddRemain - i
                    val nextEvenRemain = evenRemain - (data.nums[pos] - i)
                    var result = dfs(nextSum, pos + 1, nextOddRemain, nextEvenRemain, data)
                    if (result > 0) {
                        result = (result * data.combine[oddRemain][i] % MOD * data.combine[evenRemain][data.nums[pos] - i] % MOD).toInt()
                        data.dp[sum + data.zeroSum][pos][oddRemain] = (data.dp[sum + data.zeroSum][pos][oddRemain]!! + result) % MOD
                    }
                }
            }
        }
        return data.dp[sum + data.zeroSum][pos][oddRemain] ?: 0
    }
}