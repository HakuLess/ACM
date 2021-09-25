package leetcode.normal

import utils.lcs

class Solution583 {
    fun minDistance(word1: String, word2: String): Int {
        return word2.length + word1.length - lcs(word1.toList(), word2.toList()) * 2
    }
}