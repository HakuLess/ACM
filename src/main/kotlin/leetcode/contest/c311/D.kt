package leetcode.contest.c311

import utils.Trie
import utils.insert
import utils.print
import utils.searchVal

fun main() {
    val s = SolutionD()
    s.sumPrefixScores(arrayOf("abc", "ab", "bc", "b")).print()
}

class SolutionD {
    fun sumPrefixScores(words: Array<String>): IntArray {
        val trie = Trie<Char>()
        words.forEach {
            trie.insert(it.toCharArray().toTypedArray())
        }

        val ans = ArrayList<Int>()
        words.forEach {
            val cur = trie.searchVal(it.toCharArray().toTypedArray(), false, false)
            ans.add(cur)
        }
        return ans.toIntArray()
    }
}