package leetcode.contest.c311

import utils.*

fun main() {
    val s = SolutionD()
    s.sumPrefixScores(arrayOf("abc", "ab", "bc", "b")).print()
}

class SolutionD {
    fun sumPrefixScores(words: Array<String>): IntArray {
        val map = HashMap<Long, Int>()
        val mod = 1000000007L
        val step = 31

        words.forEach {
            var cur = 0L
            it.forEach {
                cur *= step
                cur += it - 'a' + 1
                cur %= mod
                map[cur] = map.getOrDefault(cur, 0) + 1
            }
        }

        val ans = ArrayList<Int>()
        words.forEach {
            var cur = 0L
            var c = 0
            it.forEach {
                cur *= step
                cur += it - 'a' + 1
                cur %= mod
                c += map[cur]!!
            }
            ans.add(c)
        }
        return ans.toIntArray()
    }

//    fun sumPrefixScores(words: Array<String>): IntArray {
//        val trie = Trie<Char>()
//        words.forEach {
//            trie.insert(it.toCharArray().toTypedArray())
//        }
//
//        val ans = ArrayList<Int>()
//        words.forEach {
//            val cur = trie.searchVal(it.toCharArray().toTypedArray(), false)
//            ans.add(cur)
//        }
//        return ans.toIntArray()
//    }
}