package leetcode.contest.b152

import utils.print

fun main() {
    val s = SolutionC()
    s.longestCommonPrefix(arrayOf("jump", "run", "run", "jump", "run"), 2).print()
}

class SolutionC {

    fun longestCommonPrefix(words: Array<String>, k: Int): IntArray {
        val n = words.size
        val answer = IntArray(n)
        val wordToCount = mutableMapOf<String, Int>()

        // 统计每个字符串的出现次数
        for (word in words) {
            wordToCount[word] = wordToCount.getOrDefault(word, 0) + 1
        }

        for (i in words.indices) {
            val word = words[i]
            // 移除当前元素
            wordToCount[word] = wordToCount[word]!! - 1
            if (wordToCount[word]!! >= k) {
                // 如果移除后仍有至少k个相同字符串，直接取该字符串的长度
                answer[i] = word.length
            } else {
                // 否则，需要找到其他字符串的最长公共前缀
                val remainingWords = words.filterIndexed { index, _ -> index != i }
                if (remainingWords.size < k) {
                    answer[i] = 0
                } else {
                    // 找到任意k个字符串的最长公共前缀
                    answer[i] = findLongestCommonPrefix(remainingWords, k)
                }
            }
            // 恢复当前元素
            wordToCount[word] = wordToCount[word]!! + 1
        }

        return answer
    }

    private fun findLongestCommonPrefix(words: List<String>, k: Int): Int {
        if (words.isEmpty()) return 0
        var low = 1
        var high = words.minByOrNull { it.length }?.length ?: 0
        var result = 0

        while (low <= high) {
            val mid = (low + high) / 2
            if (isCommonPrefix(words, mid, k)) {
                result = mid
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return result
    }

    private fun isCommonPrefix(words: List<String>, length: Int, k: Int): Boolean {
        val prefixMap = mutableMapOf<String, Int>()
        for (word in words) {
            val prefix = word.substring(0, minOf(length, word.length))
            prefixMap[prefix] = prefixMap.getOrDefault(prefix, 0) + 1
            if (prefixMap[prefix]!! >= k) {
                return true
            }
        }
        return false
    }
}