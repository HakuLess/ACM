package leetcode.normal

import utils.isSubSeqOf

class Solution524 {
    fun findLongestWord(s: String, dictionary: List<String>): String {
        val sorted = dictionary.sortedWith(compareBy({ -it.length }, { it }))
        for (it in sorted) {
            if (it.isSubSeqOf(s)) {
                return it
            }
        }
        return ""
    }
}