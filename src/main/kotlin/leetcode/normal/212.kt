package leetcode.normal

import utils.dir4

class Solution212 {
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val set = HashSet<String>()
        fun dfs(
            str: String,
            visited: Array<BooleanArray>,
            i: Int,
            j: Int
        ) {
            if (i < 0 || i >= board.size ||
                j < 0 || j >= board[0].size ||
                visited[i][j]
            ) return

            if (str.length >= 10) return

            val next = "$str${board[i][j]}"
            set.add(next)
            visited[i][j] = true
            dir4.forEach {
                dfs(next, visited, i + it[0], j + it[1])
            }
            visited[i][j] = false
        }

        for (i in board.indices)
            for (j in board[0].indices) {
                dfs("", Array(board.size) { BooleanArray(board[0].size) { false } }, i, j)
            }

        return words.intersect(set.toList()).toList()
    }

//    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
//        fun dfs(
//            visited: Array<BooleanArray>,
//            word: String,
//            i: Int,
//            j: Int,
//            start: Int
//        ): Boolean {
//            if (start >= word.length) {
//                return true
//            }
//            if (i < 0 || i >= board.size ||
//                j < 0 || j >= board[0].size ||
//                visited[i][j] ||
//                board[i][j] != word[start]
//            ) return false
//
//            visited[i][j] = true
//            val result = dfs(visited, word, i - 1, j, start + 1) ||
//                    dfs(visited, word, i + 1, j, start + 1) ||
//                    dfs(visited, word, i, j - 1, start + 1) ||
//                    dfs(visited, word, i, j + 1, start + 1)
//            visited[i][j] = false
//            return result
//        }
//
//        fun exist(word: String): Boolean {
//            val r = board.size
//            val c = board[0].size
//            val visited = Array(r) { BooleanArray(c) }
//
//            for (i in 0 until r) {
//                for (j in 0 until c) {
//                    if (dfs(visited, word, i, j, 0)) {
//                        return true
//                    }
//                }
//            }
//            return false
//        }
//
//        val ans = arrayListOf<String>()
//        words.forEach {
//            if (exist(it)) {
//                ans.add(it)
//            }
//        }
//        return ans.distinct()
//    }
}