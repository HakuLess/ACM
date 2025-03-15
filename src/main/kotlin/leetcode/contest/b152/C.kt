package leetcode.contest.b152

class SolutionC {
    fun longestCommonPrefix(words: Array<String>, k: Int): IntArray {
        val n = words.size
        val answer = IntArray(n)

        for (i in words.indices) {
            val reducedWords = words.filterIndexed { index, _ -> index != i }

            if (reducedWords.size < k) {
                answer[i] = 0
                continue
            }

            val prefixLength = findMaxPrefix(reducedWords, k)
            answer[i] = prefixLength
        }

        return answer
    }

    private fun findMaxPrefix(words: List<String>, k: Int): Int {
        val sortedWords = words.sorted()
        var maxPrefix = 0

        for (i in 0 until words.size - k + 1) {
            val prefix = commonPrefix(sortedWords[i], sortedWords[i + k - 1])
            maxPrefix = maxOf(maxPrefix, prefix)
        }

        return maxPrefix
    }

    private fun commonPrefix(s1: String, s2: String): Int {
        var i = 0
        while (i < s1.length && i < s2.length && s1[i] == s2[i]) {
            i++
        }
        return i
    }
}