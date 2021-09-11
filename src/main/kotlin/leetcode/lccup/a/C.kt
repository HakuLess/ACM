package leetcode.lccup.a

import utils.print

fun main() {
    val s = SolutionC()
    s.flipChess(
        arrayOf(
            "....X.",
            "....X.",
            "XOOO..",
            "......",
            "......"
        )
    ).print()
    s.flipChess(arrayOf(".X.", ".O.", "XO.")).print()
}

class SolutionC {
    fun flipChess(chessboard: Array<String>): Int {

        val n = chessboard.size
        val m = chessboard[0].length

        val dir8 = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 1),
            intArrayOf(-1, 1),
            intArrayOf(0, -1),
            intArrayOf(1, -1),
            intArrayOf(-1, -1),
            intArrayOf(1, 0),
            intArrayOf(-1, 0)
        )

        fun check(matrix: Array<IntArray>): Int {
            var ans = 0
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (matrix[i][j] == 1) {
                        for (k in dir8.indices) {
                            var curI = i
                            var curJ = j
                            var cnt = 0
                            val tmp = ArrayList<IntArray>()
                            while (curI in 0 until n && curJ in 0 until m) {
                                curI += dir8[k][0]
                                curJ += dir8[k][1]
                                if (curI in 0 until n && curJ in 0 until m && matrix[curI][curJ] == 0) {
                                    cnt++
                                    tmp.add(intArrayOf(curI, curJ))
                                } else {
                                    break
                                }
                            }
                            if (curI in 0 until n && curJ in 0 until m && matrix[curI][curJ] == 1) {
                                ans += cnt
                                tmp.forEach {
                                    matrix[it[0]][it[1]] = 1
                                }
                            }
                        }
                    }
                }
            }
            return ans
        }

        fun flip(i: Int, j: Int): Int {
            val matrix = Array<IntArray>(n) { IntArray(m) }
            for (a in chessboard.indices) {
                for (b in chessboard[0].indices) {
                    when (chessboard[a][b]) {
                        '.' -> matrix[a][b] = -1
                        'X' -> matrix[a][b] = 1
                        'O' -> matrix[a][b] = 0
                    }
                }
            }
            matrix[i][j] = 1
            var ans = 0
            while (true) {
                ans += check(matrix).also {
                    if (it == 0) return ans
                }
            }
        }

        var ans = 0
        for (i in chessboard.indices) {
            for (j in chessboard[0].indices) {
                if (chessboard[i][j] == '.') {
                    ans = maxOf(ans, flip(i, j))
                }
            }
        }
        return ans
    }
}