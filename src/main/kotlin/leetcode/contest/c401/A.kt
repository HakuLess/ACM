package leetcode.contest.c401

class SolutionA {
    fun numberOfChild(n: Int, k: Int): Int {
        var position = 0
        var direction = 1

        for (i in 1..k) {
            position += direction
            if (position == 0 || position == n - 1) {
                direction = -direction
            }
        }

        return position
    }
}