package leetcode.contest.c390

import utils.Trie
import utils.lcp
import utils.print

fun main() {
    val s = SolutionD()
    s.stringIndices(arrayOf("abcd", "bcd", "xbcd"), arrayOf("cd", "bcd", "xyz")).print()
}

class SolutionD {

    class TrieNode {
        val children = Array<TrieNode?>(26) { null }
        var isEndOfWord = false
    }

    class Trie {
        val root = TrieNode()

        fun insert(word: String) {
            var node = root
            for (i in word.length - 1 downTo 0) {
                val index = word[i] - 'a'
                if (node.children[index] == null) {
                    node.children[index] = TrieNode()
                }
                node = node.children[index]!!
            }
            node.isEndOfWord = true
        }

        fun findLongestCommonSuffix(word: String): String {
            var node = root
            val longestCommonSuffix = StringBuilder()
            for (i in word.length - 1 downTo 0) {
                val index = word[i] - 'a'
                if (node.children[index] != null) {
                    longestCommonSuffix.append(word[i])
                    node = node.children[index]!!
                } else {
                    break
                }
            }
            return longestCommonSuffix.reverse().toString()
        }
    }

    fun stringIndices(wordsContainer: Array<String>, wordsQuery: Array<String>): IntArray {
        val trie = Trie()
        for (word in wordsContainer) {
            trie.insert(word)
        }

        val suffixIndex = IntArray(wordsQuery.size)

        for ((index, query) in wordsQuery.withIndex()) {
            val longestCommonSuffix = trie.findLongestCommonSuffix(query)
            var res = 0
            var curLen = Int.MAX_VALUE
            for (i in wordsContainer.indices) {
                val item = wordsContainer[i]
                if (item.endsWith(longestCommonSuffix)) {
                    if (item.length < curLen) {
                        curLen = item.length
                        res = i
                    }
                }
            }
            suffixIndex[index] = res
        }

        return suffixIndex
    }
}