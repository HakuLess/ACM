package leetcode.contest.c385

class SolutionA {
    fun countPrefixSuffixPairs(words: Array<String>): Int {
        var ans = 0
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                val a = words[i]
                val b = words[j]
                if (b.startsWith(a) && b.endsWith(a)) {
                    ans++
                }
            }
        }
        return ans
    }
}