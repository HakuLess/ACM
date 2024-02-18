package leetcode.contest.c385

import utils.*

fun main() {
    val s = SolutionC()
//    s.mostFrequentPrime("[[1,1],[9,9],[1,1]]".toGrid()).print()
    s.mostFrequentPrime("[[8,9,3],[3,5,6],[1,2,5]]".toGrid()).print()
}

class SolutionC {
    fun mostFrequentPrime(mat: Array<IntArray>): Int {
        val n = mat.size
        val m = mat[0].size
        val map = HashMap<Int, Int>()

        fun dfs(i: Int, j: Int, dir: IntArray) {
            var cur = 0
            for (step in 0 until 10) {
                val x = i + dir[0] * step
                val y = j + dir[1] * step
                if (x in 0 until n && y in 0 until m) {
                    cur += mat[x][y]
//                    println("$i$j${dir.joinToString()} enter $x $y: $cur")
                    map[cur] = map.getOrDefault(cur, 0) + 1
                    cur *= 10
                }
            }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                // 作为起点
                dir8.forEach {
                    dfs(i, j, it)
                }
            }
        }

        map.printInt()

        var ans = -1
        var times = 0

        map.forEach { k, v ->
            if (isPrime(k) && k > 10) {
                println("try $k $v with $ans $times")
                if (v >= times) {
                    if (v == times) {
                        ans = maxOf(ans, k)
                    } else {
                        ans = k
                        times = v
                    }
                }
            }
        }
        return ans
    }
}