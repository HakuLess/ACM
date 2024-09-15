package leetcode.contest.c415

import utils.Trie
import utils.insert
import utils.print
import utils.search
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.minValidStrings(arrayOf("abc", "aaaaa", "bcdef"), "aabcdabc").print()
//    s.minValidStrings(arrayOf("abc"), "a").print()
//    s.minValidStrings(arrayOf("ea", "a"), "eaeaa").print()
    s.minValidStrings(arrayOf("ba", "cabccabaaacabc"), "babcc").print()
}

class SolutionC {
    fun minValidStrings(words: Array<String>, target: String): Int {

        val trie = Trie<Char>()
        words.forEach {
            trie.insert(it.toCharArray().toTypedArray())
        }

        val n = target.length
        val dp = Array<BooleanArray>(n) { BooleanArray(n) { false } }
        for (i in target.indices) {
            val sub = StringBuilder()
            for (j in i until target.length) {
                sub.append(target[j])
//                println(sub)
                if (trie.search(sub.toString().toCharArray().toTypedArray(), needEnd = false)) {
                    dp[i][j] = true
                }
            }
        }
        return -1
    }
}