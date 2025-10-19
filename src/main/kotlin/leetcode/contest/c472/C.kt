package leetcode.contest.c472

import utils.print

fun main() {
    val s = SolutionC()
    s.lexGreaterPermutation("abc", "bba").print()
    s.lexGreaterPermutation("leet", "code").print()
    s.lexGreaterPermutation("baba", "bbaa").print()
}

class SolutionC {
    fun lexGreaterPermutation(s: String, target: String): String {

        val n = s.length
        val freq = IntArray(26)
        for (c in s) freq[c - 'a']++

        // 构造递归函数
        // 从左至右当前位数 是否前置位已经大于 剩余数字频率
        fun dfs(pos: Int, greater: Boolean, freq: IntArray): String? {
            if (pos == n) return if (greater) "" else null

            // 轮询开始位，若已大于则直接从头开始，否则从该位字母开始
            val start = if (greater) 0 else target[pos] - 'a'

            for (c in start until 26) {
                if (freq[c] == 0) continue
                val next = greater || (c > target[pos] - 'a')
                freq[c]--
                val suffix = dfs(pos + 1, next, freq)
                if (suffix != null) {
                    return ('a' + c) + suffix
                }
                freq[c]++
            }
            return null
        }

        return dfs(0, false, freq) ?: ""
    }
}