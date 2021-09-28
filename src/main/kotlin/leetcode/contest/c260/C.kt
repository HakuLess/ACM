package leetcode.contest.c260

import utils.print

fun main() {
    val s = Solution5883()
//    s.placeWordInCrossword(
//        arrayOf(
//            charArrayOf(' ', '#', 'a'),
//            charArrayOf(' ', '#', 'c'),
//            charArrayOf(' ', '#', 'a'),
//        ), "ac"
//    ).print()

    s.placeWordInCrossword(
        arrayOf(
            charArrayOf('#', ' ', '#'),
            charArrayOf('#', ' ', '#'),
            charArrayOf('#', ' ', 'c'),
        ), "ca"
    ).print()
}

class Solution5883 {
    fun placeWordInCrossword(board: Array<CharArray>, word: String): Boolean {
        fun check(str: String, i: Int, j: Int, hor: Boolean): Boolean {
            var x = i
            var y = j
            var step = 0
            while (x in board.indices && y in board[0].indices) {
                if (step == str.length) {
                    return board[x][y] == '#'
                }
                if (board[x][y] != ' ' && board[x][y] != str[step]) return false
                step++
                if (hor) y++
                else x++
            }
            return step == str.length
        }
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] != '#') {
                    if (i == 0 || board[i - 1][j] == '#') {
                        if (check(word, i, j, false)) return true
                        if (check(word.reversed(), i, j, false)) return true
                    }
                    if (j == 0 || board[i][j - 1] == '#') {
                        if (check(word, i, j, true)) return true
                        if (check(word.reversed(), i, j, true)) return true
                    }
                }
            }
        }
        return false
    }
}