package leetcode.contest.b161

// TODO Not Finished
class SolutionD {

    // 预先计算 0-63 范围内各个数的 popcount-深度
    private val depth = intArrayOf(
        0, 1, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 3,
        2, 3, 3, 4, 3, 4, 4, 3, 3, 4, 4, 3, 4, 3, 3, 4,
        2, 3, 3, 4, 3, 4, 4, 3, 3, 4, 4, 3, 4, 3, 3, 4,
        3, 4, 4, 3, 4, 3, 3, 4, 4, 3, 3, 4, 3, 4, 4, 4
    )

    // 组合数表，combine[n][k] = C(n, k)
    private val combine = Array(64) { LongArray(64) }

    init {
        combine[0][0] = 1
        for (i in 1..63) {
            for (j in 0..i) {
                if (j == 0 || j == i) {
                    combine[i][j] = 1
                } else {
                    combine[i][j] = combine[i - 1][j] + combine[i - 1][j - 1]
                }
            }
        }
    }

    fun popcountDepth(n: Long, k: Int): Long {
        // 特殊情况：k=0 时，只有 x=1 可以直接满足
        if (k == 0) return 1
        // mem[i] 用于记录 popcount-depth 中间结果（可选，此处未使用）
        val mem = IntArray(64)
        mem[1] = 1
        for (i in 2..63) {
            mem[i] = mem[java.lang.Long.bitCount(i.toLong())] + 1
        }
        var res: Long = 0
        // 将 n 转为二进制字符串
        val max = java.lang.Long.toBinaryString(n)
        // 枚举第一次 popcount 结果 i，深度为 k 的
        for (i in 1..63) {
            if (depth[i] == k) {
                res += cal(max, i)
            }
        }
        return res
    }

    // 计算不超过二进制上界 max 的，且 popcount = digit 的正整数个数
    private fun cal(max: String, digit: Int): Long {
        var res = currentValid(max, digit).toLong()
        // 统计位数短于 max.length() 的全组合数
        for (i in 2 until max.length) {
            if (i - 1 >= digit - 1) {
                res += combine[i - 1][digit - 1]
            }
        }
        // 处理与 max 前缀相同的情况
        var remainDigit = digit - 1
        for (i in 1 until max.length) {
            if (max[i] == '1') {
                val remain = max.length - i - 1
                if (remain >= remainDigit) {
                    res += combine[remain][remainDigit]
                }
                remainDigit--
                if (remainDigit < 0) break
            }
        }
        return res
    }

    // 判断二进制字符串 max 本身是否符合 popcount = digit
    private fun currentValid(max: String, digit: Int): Int {
        var digit = digit
        if ("1" == max && digit != 0) return 0
        for (c in max.toCharArray()) {
            digit -= c - '0'
        }
        return if (digit == 0) 1 else 0
    }
}