package leetcode.normal

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution407()
    s.trapRainWater("[[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]".toGrid()).print()
}

class Solution407 {
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        val n = heightMap.size
        val m = heightMap[0].size

        val seen = Array(n) { BooleanArray(m) }
        // 前面是坐标 第三个是value值
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })

        // 矩阵边界入队
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    pq.offer(Triple(i, j, heightMap[i][j]))
                    seen[i][j] = true
                }
            }
        }

        var ans = 0
        while (!pq.isEmpty()) {
            val item = pq.poll()
            dir4.forEach {
                val nx = item.first + it[0]
                val ny = item.second + it[1]
                if (nx in 0 until n && ny in 0 until m && !seen[nx][ny]) {
                    if (item.third > heightMap[nx][ny]) {
                        ans += item.third - heightMap[nx][ny]
                    }
                    pq.offer(Triple(nx, ny, maxOf(heightMap[nx][ny], item.third)))
                    seen[nx][ny] = true
                }
            }
        }
        return ans
    }

//    fun trapRainWater(heightMap: Array<IntArray>): Int {
//        val m = heightMap.size
//        val n = heightMap[0].size
//
//        val visit = Array(m) { BooleanArray(n) }
//        val pq = PriorityQueue<IntArray>(compareBy { it[1] })
//
//        // 矩阵边界入队
//        for (i in 0 until m) {
//            for (j in 0 until n) {
//                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
//                    pq.offer(intArrayOf(i * n + j, heightMap[i][j]))
//                    visit[i][j] = true
//                }
//            }
//        }
//
//        var res = 0
//        // 一维数组代表4个方向
//        val dirs = intArrayOf(-1, 0, 1, 0, -1)
//        while (!pq.isEmpty()) {
//            val item = pq.poll()
//            for (k in 0..3) {
//                val nx = item[0] / n + dirs[k]
//                val ny = item[0] % n + dirs[k + 1]
//                if (nx in 0 until m && ny in 0 until n && !visit[nx][ny]) {
//                    if (item[1] > heightMap[nx][ny]) {
//                        res += item[1] - heightMap[nx][ny]
//                    }
//                    pq.offer(intArrayOf(nx * n + ny, maxOf(heightMap[nx][ny], item[1])))
//                    visit[nx][ny] = true
//                }
//            }
//        }
//        return res
}