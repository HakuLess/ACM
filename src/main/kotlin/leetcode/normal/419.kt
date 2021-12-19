package leetcode.normal

class Solution419 {
    fun countBattleships(board: Array<CharArray>): Int {
        var ans = 0
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (i - 1 in board.indices && board[i - 1][j] == 'X') continue
                if (j - 1 in board[0].indices && board[i][j - 1] == 'X') continue
                if (board[i][j] == 'X') {
                   ans++
                }
            }
        }
        return ans
    }
}