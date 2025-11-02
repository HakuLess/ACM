package leetcode.contest.c474

import utils.nextPermutation

class SolutionD {
    fun lexPalindromicPermutation(s: String, target: String): String {

        val n = s.length
        val freq = IntArray(26)
        for (c in s) freq[c - 'a']++

        val oddChars = freq.count { it % 2 == 1 }
        if (oddChars > 1) return ""

        val mid = if (n % 2 == 1) {
            ('a'..'z').firstOrNull { freq[it - 'a'] % 2 == 1 }?.toString() ?: ""
        } else ""

        val half = mutableListOf<Char>()
        for (i in 0 until 26) {
            repeat(freq[i] / 2) { half.add(('a' + i)) }
        }
        fun build(half: CharArray): String {
            val left = half.joinToString("")
            return left + mid + left.reversed()
        }

        // 先排序为最小排列
        half.sort()

        // 尝试找下一个排列
        val arr = half.toCharArray()

        val start = build(arr)
        if (start > target) return start

        while (arr.nextPermutation()) {
            val candidate = build(arr)
            if (candidate > target) return candidate
        }

        return ""
    }
}