package leetcode.contest.c485

class SolutionA {
    fun vowelConsonantScore(s: String): Int {
        var vowels = 0
        var cnt = 0
        val vowelSet = setOf('a', 'e', 'i', 'o', 'u')

        for (ch in s) {
            if (ch in 'a'..'z') {
                if (ch in vowelSet) {
                    vowels++
                } else {
                    cnt++
                }
            }
        }

        return if (cnt > 0) vowels / cnt else 0
    }
}