package leetcode.normal

import utils.Trie
import utils.insert
import utils.print
import utils.search

fun main() {
    val s = Solution472()
    s.findAllConcatenatedWordsInADict(
        arrayOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat")
    ).joinToString().print()
}

class Solution472 {
    fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
        words.sortBy { it.length }
        val trie = Trie<Char>()
        val ans = ArrayList<String>()
        words.forEach {
            if (it.isNotEmpty()) {
                if (trie.search(it.toCharArray().toTypedArray(), true)) {
                    ans.add(it)
                } else {
                    trie.insert(it.toCharArray().toTypedArray())
                }
            }
        }
        return ans
    }
}