package leetcode.contest.b111

import utils.isSubSeqOf

class SolutionB {
    fun canMakeSubsequence(str1: String, str2: String): Boolean {

        var x = 0
        var y = 0
        while (x < str2.length && y < str1.length) {
            if (str2[x] == str1[y] || (str2[x] - 'a' == (str1[y] - 'a' + 1) % 26)) {
                x++
                y++
            } else {
                y++
            }
        }
        return x == str2.length
    }
}