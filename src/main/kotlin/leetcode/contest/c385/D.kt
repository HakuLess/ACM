package leetcode.contest.c385

import utils.*


fun main() {
    val s = SolutionD()
    s.countPrefixSuffixPairs(arrayOf("a", "aba", "ababa", "aa")).print()
    s.countPrefixSuffixPairs(arrayOf("pa", "papa", "ma", "mama")).print()
}

class SolutionD {
    fun countPrefixSuffixPairs(words: Array<String>): Long {
        var ans = 0L
        val cnt: MutableMap<String, Int> = HashMap()
        for (s in words) {
            for (k in cnt.keys) {
                if (s.startsWith(k) && s.endsWith(k)) {
                    ans += cnt[k]!!
                }
            }
            cnt[s] = cnt.getOrDefault(s, 0) + 1
        }
        return ans
    }
}

