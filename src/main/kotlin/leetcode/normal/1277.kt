package leetcode.normal

class Solution1277 {
    fun countSquares(matrix: Array<IntArray>): Int {
        val a = Array<IntArray>(matrix.size) { IntArray(matrix[0].size) }
        val b = Array<IntArray>(matrix.size) { IntArray(matrix[0].size) }
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (i == 0) {
                    a[i][j] = matrix[i][j]
                    if (j == 0) {
                        b[i][j] = matrix[i][j]
                    } else {
                        b[i][j] = if (matrix[i][j] == 0) {
                            0
                        } else {
                            matrix[i][j] + b[i][j - 1]
                        }
                    }
                } else {
                    a[i][j] = if (matrix[i][j] == 0) {
                        0
                    } else {
                        a[i - 1][j] + matrix[i][j]
                    }
                    b[i][j] = when {
                        matrix[i][j] == 0 -> {
                            0
                        }
                        j == 0 -> {
                            matrix[i][j]
                        }
                        else -> {
                            matrix[i][j] + b[i][j - 1]
                        }
                    }
                }
            }
        }

        // a.print()
        // b.print()

        var ans = 0
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                var s = 1
                var l = i
                var t = j
                while (l in matrix.indices && t in matrix[0].indices && a[l][t] >= s && b[l][t] >= s) {
                    ans++
                    s++
                    l++
                    t++
                }
            }
        }
        return ans
    }
}