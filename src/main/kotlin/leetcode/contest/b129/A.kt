package leetcode.contest.b129

class SolutionA {
    fun canMakeSquare(grid: Array<CharArray>): Boolean {
        for (i in 0..1) {
            for (j in 0..1) {
                var a = 0
                for (m in 0..1) {
                    for (n in 0..1) {
                        if (grid[i + m][j + n] == 'W') {
                            a++
                        }
                    }
                }
                if (a <= 1 || a >= 3) return true
            }
        }
        return false
    }
}