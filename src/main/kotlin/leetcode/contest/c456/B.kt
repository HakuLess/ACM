package leetcode.contest.c456

import utils.lcp
import utils.print

fun main() {
    val s = SolutionB()
    s.longestCommonPrefix(arrayOf("jump", "run", "run", "jump", "run")).print()
    s.longestCommonPrefix(arrayOf("jump")).print()
    s.longestCommonPrefix(arrayOf("jump", "run")).print()
    s.longestCommonPrefix(arrayOf("fec", "fef", "acaa", "adfa", "afc", "fdbda")).print()
}

class SolutionB {
    fun longestCommonPrefix(words: Array<String>): IntArray {
        val n = words.size
        if (n == 1) return intArrayOf(0)
        if (n == 2) return intArrayOf(0, 0)

        val prefix = IntArray(n - 1) { j ->
            lcp(arrayOf(words[j], words[j + 1])).length
        }

//        prefix.print()

        val prefixMax = IntArray(n - 1)
        val suffixMax = IntArray(n - 1)

        prefixMax[0] = prefix[0]
        for (j in 1 until n - 1) {
            prefixMax[j] = maxOf(prefixMax[j - 1], prefix[j])
        }

        suffixMax[n - 2] = prefix[n - 2]
        for (j in n - 3 downTo 0) {
            suffixMax[j] = maxOf(suffixMax[j + 1], prefix[j])
        }

//        prefixMax.print()
//        suffixMax.print()

        val ans = IntArray(n)
        for (i in 0 until n) {
            var maxLen = 0

            when (i) {
                0 -> {
                    maxLen = suffixMax[1]
                }
                n - 1 -> {
                    maxLen = prefixMax[n - 3]
                }
                else -> {
                    val left = prefixMax.getOrElse(i - 2) { 0 }
                    val mid = lcp(arrayOf(words[i - 1], words[i + 1])).length
                    val right = suffixMax.getOrElse(i + 1) { 0 }

                    maxLen = maxOf(left, mid, right)
                }
            }

            ans[i] = maxLen
        }

        return ans
    }
}