package leetcode.contest.c445

import utils.print
import java.math.BigInteger

fun main() {
    val s = SolutionC()
    s.smallestPalindrome("abba", 2).print()
}

class SolutionC {

    /**
     * 返回 s 的按字典序排列的第 k 小回文排列。
     *
     * @param s 回文字符串，保证 s 本身就是回文
     * @param k 正整数，表示要求第 k 小回文排列（如果不存在则返回空字符串）
     */
    fun smallestPalindrome(s: String, k: Int): String {

        val freq = IntArray(26)
        for (c in s) {
            freq[c - 'a']++
        }

        val n = s.length
        var mid = ""
        if (n % 2 != 0) {
            mid = s[n / 2].toString()
        }

        val halfFreq = IntArray(26)
        var totalHalf = 0
        for (i in 0 until 26) {
            halfFreq[i] = freq[i] / 2
            totalHalf += halfFreq[i]
        }

        // 预计算阶乘（最大到 totalHalf）
        val fact = buildFactorials(totalHalf)

        // 计算总的不同排列数
        val totalPermutations = countPermutation(halfFreq, totalHalf, fact)
        if (totalPermutations < k.toBigInteger()) {
            return ""
        }

        var remaining = k.toBigInteger()
        val halfBuilder = StringBuilder()

        for (pos in 0 until totalHalf) {
            // 枚举字母 a 到 z
            for (letter in 0 until 26) {
                if (halfFreq[letter] == 0) continue

                // 尝试在当前位置放 letter，更新后剩余频率
                halfFreq[letter]--
                val ways = countPermutation(halfFreq, totalHalf - pos - 1, fact)
//                println("ways $ways with ${halfFreq.joinToString(";")}")
//                println("$ways cmp $remaining for $halfBuilder")
                if (ways >= remaining) {
                    // 选择 letter，在当前位置确定下来
                    halfBuilder.append('a' + letter)
                    break
                } else {
                    // 剩余排列数有余，越过当前字符，减去已当前字符为首的排列数组合
                    // 以当前字符为首的 ways 跳过，还需剩余最小 -ways
                    remaining -= ways
                    halfFreq[letter]++
                }
            }
        }

        val rightHalf = halfBuilder.toString().reversed()
        return halfBuilder.toString() + mid + rightHalf
    }
}

// 计算 0 到 n 的阶乘数组，fact[i] = i!
fun buildFactorials(n: Int): Array<BigInteger> {
    val fact = Array(n + 1) { BigInteger.ONE }
    for (i in 1..n) {
        fact[i] = fact[i - 1] * BigInteger.valueOf(i.toLong())
    }
    return fact
}

// 根据当前多重集 frequencies 和剩余字符数 total，计算排列数 = total! / (∏ factorial(freq))
fun countPermutation(freq: IntArray, total: Int, fact: Array<BigInteger>): BigInteger {
    var res = fact[total]
    for (cnt in freq) {
        if (cnt > 0) {
            res /= fact[cnt]
        }
    }
    return res
}