package leetcode.contest.c336

class SolutionA {
    fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
        val s = hashSetOf('a', 'e', 'i', 'o', 'u')
        var ans = 0
        for (i in left..right) {
            if (words[i].first() in s && words[i].last() in s) {
                ans++
            }
        }
        return ans
    }
}