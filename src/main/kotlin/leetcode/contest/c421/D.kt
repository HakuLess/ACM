package leetcode.contest.c421

import utils.print

fun main() {
    val s = SolutionD()
    s.lengthAfterTransformations(
        "abcyy",
        2,
        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2)
    ).print()
}

// https://leetcode.cn/contest/weekly-contest-421/problems/total-characters-in-string-after-transformations-ii/
// 倍增法 矩阵
class SolutionD {
    fun lengthAfterTransformations(s: String, t: Int, nums: List<Int>): Int {
        val mod = 1_000_000_007L

        // 创建一个 26x26 的转换矩阵
        val transformationMatrix = Array(26) { LongArray(26) }

        // 填充转换矩阵
        for (i in 0 until 26) {
            for (j in 0 until nums[i]) {
                transformationMatrix[i][(i + j + 1) % 26]++
            }
        }

        // 快速幂计算矩阵的 t 次幂
        val resultMatrix = matrixExponentiation(transformationMatrix, t, mod)

        // 计算输入字符串中每个字符的频率
        val initialCounts = LongArray(26)
        for (char in s) {
            initialCounts[char - 'a']++
        }

        // 计算最终字符的出现次数
        var finalLength = 0L
        for (i in 0 until 26) {
            for (j in 0 until 26) {
                finalLength = (finalLength + initialCounts[i] * resultMatrix[i][j]) % mod
            }
        }

        return finalLength.toInt()
    }
}

// 矩阵乘法
fun multiplyMatrices(a: Array<LongArray>, b: Array<LongArray>, mod: Long): Array<LongArray> {
    val size = a.size
    val result = Array(size) { LongArray(size) }
    for (i in 0 until size) {
        for (j in 0 until size) {
            for (k in 0 until size) {
                result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % mod
            }
        }
    }
    return result
}

// 矩阵快速幂
fun matrixExponentiation(matrix: Array<LongArray>, power: Int, mod: Long): Array<LongArray> {
    val size = matrix.size
    var result = Array(size) { LongArray(size) }
    // 初始化结果为单位矩阵
    for (i in 0 until size) {
        result[i][i] = 1
    }

    var base = matrix
    var exp = power

    while (exp > 0) {
        if (exp % 2 == 1) {
            result = multiplyMatrices(result, base, mod)
        }
        base = multiplyMatrices(base, base, mod)
        exp /= 2
    }

    return result
}