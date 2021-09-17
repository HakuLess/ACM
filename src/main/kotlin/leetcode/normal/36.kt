package leetcode.normal

class Solution36 {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        fun valid(n: Int, `val`: Int): Int {
            return if (`val` shr n and 1 == 1) -1 else `val` xor (1 shl n)
        }

        for (i in 0..8) {
            // hori, veti, sqre分别表示行、列、小宫
            var hori = 0
            var veti = 0
            var sqre = 0
            for (j in 0..8) {
                val h = board[i][j] - '0'
                val v = board[j][i] - '0'
                val s = board[3 * (i / 3) + j / 3][3 * (i % 3) + j % 3] - '0'
                if (h > 0) {
                    hori = valid(h, hori)
                }
                if (v > 0) {
                    veti = valid(v, veti)
                }
                if (s > 0) {
                    sqre = valid(s, sqre)
                }
                if (hori == -1 || veti == -1 || sqre == -1) {
                    return false
                }
            }
        }
        return true
    }
}