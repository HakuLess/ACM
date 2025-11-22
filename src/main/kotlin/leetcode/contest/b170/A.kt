package leetcode.contest.b170

class SolutionA {
    fun minimumFlips(n: Int): Int {
        val s = n.toString(2)
        val rev = s.reversed()

        var ans = 0
        for (i in s.indices) {
            if (s[i] != rev[i]) ans++
        }
        return ans
    }
}