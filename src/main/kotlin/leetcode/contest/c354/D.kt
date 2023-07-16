package leetcode.contest.c354

import utils.Trie
import utils.insert
import utils.print
import utils.search

fun main() {
    val s = SolutionD()
    s.longestValidSubstring("cbaaaabc", listOf("aaa", "cb")).print()
}

class SolutionD {
    fun longestValidSubstring(word: String, forbidden: List<String>): Int {
        val trie = Trie<Char>()
        val set = HashSet<Int>()
        forbidden.forEach {
            set.add(it.length)
            trie.insert(it.toCharArray().toTypedArray())
        }

        fun check(l: ArrayList<Char>): Boolean {
            return set.any {
                it <= l.size && trie.search(l.takeLast(it).toTypedArray())
            }
        }

        var ans = 0
        val cur = ArrayList<Char>()
        for (i in word.indices) {
            cur.add(word[i])
//            println("in cur ${cur.joinToString("")}")
            while (cur.isNotEmpty() && check(cur)) {
                cur.removeAt(0)
            }
//            println("out cur ${cur.joinToString("")}")
            ans = maxOf(ans, cur.size)
        }
        return ans
    }
}