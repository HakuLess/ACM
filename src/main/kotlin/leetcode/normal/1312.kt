package leetcode.normal

import utils.lcs

class Solution1312 {
    fun minInsertions(s: String): Int {
        val t = s.reversed()
        val n = s.length
        val lcs = lcs(s.toCharArray().toList(), t.toCharArray().toList())
        return n - lcs
    }
}