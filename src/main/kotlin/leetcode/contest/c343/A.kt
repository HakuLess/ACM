package leetcode.contest.c343

class SolutionA {
    fun isWinner(player1: IntArray, player2: IntArray): Int {
        fun cal(c: IntArray): Int {
            var ans = 0
            for (i in c.indices) {
                if ((i - 1 in c.indices && c[i - 1] == 10) || (i - 2 in c.indices && c[i - 2] == 10)) {
                    ans += 2 * c[i]
                } else {
                    ans += c[i]
                }
            }
            return ans
        }

        val a = cal(player1)
        val b = cal(player2)
        if (a > b) return 1
        if (a < b) return 2
        return 0
    }
}