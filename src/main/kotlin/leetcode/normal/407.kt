package leetcode.normal

import java.util.*


class Solution407 {
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val m = heightMap.size
        val n = heightMap[0].size

        val visit = Array(m) { BooleanArray(n) }
        val pq = PriorityQueue<IntArray>(compareBy { it[1] })

        // 矩阵边界入队
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(intArrayOf(i * n + j, heightMap[i][j]))
                    visit[i][j] = true
                }
            }
        }

        var res = 0
        // 一维数组代表4个方向
        val dirs = intArrayOf(-1, 0, 1, 0, -1)
        while (!pq.isEmpty()) {
            val item = pq.poll()
            for (k in 0..3) {
                val nx = item[0] / n + dirs[k]
                val ny = item[0] % n + dirs[k + 1]
                if (nx in 0 until m && ny in 0 until n && !visit[nx][ny]) {
                    if (item[1] > heightMap[nx][ny]) {
                        res += item[1] - heightMap[nx][ny]
                    }
                    pq.offer(intArrayOf(nx * n + ny, maxOf(heightMap[nx][ny], item[1])))
                    visit[nx][ny] = true
                }
            }
        }
        return res
    }
}