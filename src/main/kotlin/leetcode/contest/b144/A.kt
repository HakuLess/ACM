package leetcode.contest.b144

class SolutionA {
    fun canAliceWin(n: Int): Boolean {
        if (n < 10) return false

        var stonesLeft = n
        var lastMove = 10
        var isAliceTurn = true

        while (stonesLeft > 0) {
            stonesLeft -= lastMove
            if (stonesLeft < 0) break

            lastMove--
            if (lastMove <= 0) break

            isAliceTurn = !isAliceTurn
        }

        return !isAliceTurn
    }
}