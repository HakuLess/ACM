package leetcode.contest.b170

class SolutionB {
    fun totalWaviness(num1: Int, num2: Int): Int {

        fun count(x: Int): Int {
            val s = x.toString()
            val n = s.length

            var w = 0
            for (i in 1 until n - 1) {
                val a = s[i - 1] - '0'
                val b = s[i] - '0'
                val c = s[i + 1] - '0'
                if (b > a && b > c) w++
                else if (b < a && b < c) w++
            }
            return w
        }

        var ans = 0
        for (x in num1..num2) {
            ans += count(x)
        }
        return ans
    }
}