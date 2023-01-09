package leetcode.contest.c324

class SolutionA {
    fun similarPairs(words: Array<String>): Int {
        var ans = 0
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (words[i].toSet().sorted().joinToString() ==
                    words[j].toSet().sorted().joinToString()
                ) ans++
            }
        }
        return ans
    }
}