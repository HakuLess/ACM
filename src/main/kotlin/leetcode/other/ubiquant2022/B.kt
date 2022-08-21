package leetcode.other.ubiquant2022

import utils.dir8

class SolutionB {
    fun lakeCount(field: Array<String>): Int {
        val seen = HashSet<String>()
        fun dfs(i: Int, j: Int) {
            if (i !in field.indices || j !in field[0].indices) return
            if (field[i][j] == 'W' && "$i,$j" !in seen) {
                seen.add("$i,$j")
                dir8.forEach {
                    dfs(i + it[0], j + it[1])
                }
            }
        }

        var ans = 0
        for (i in field.indices) {
            for (j in field[0].indices) {
                if (field[i][j] == 'W' && "$i,$j" !in seen) {
                    dfs(i, j)
                    ans++
                }
            }
        }
        return ans
    }
}