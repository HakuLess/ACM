package leetcode.contest.c334

class SolutionB {
    fun divisibilityArray(word: String, m: Int): IntArray {
        var cur = 0L
        val ans = ArrayList<Int>()
        for (i in word.indices) {
            cur *= 10
            cur += word[i] - '0'
            cur %= m
            ans.add(if (cur == 0L) 1 else 0)
        }
        return ans.toIntArray()
    }
}