package leetcode.contest.b107

class SolutionA {
    fun maximumNumberOfStringPairs(words: Array<String>): Int {
        val seen = HashSet<Int>()
        var ans = 0
        for (i in words.indices) {
            for (j in words.indices) {
                if (i in seen || j in seen || i == j) continue
                if (words[i] == words[j].reversed()) {
                    ans++
                    seen.add(i)
                    seen.add(j)
                }
            }
        }
        return ans
    }
}