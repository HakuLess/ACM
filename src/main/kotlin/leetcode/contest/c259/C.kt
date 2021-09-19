package leetcode.contest.c259

import utils.print
import kotlin.math.abs

fun main() {
    val s = DetectSquares()
    s.add(intArrayOf(8, 0))
    s.add(intArrayOf(8, 5))
    s.add(intArrayOf(9, 0))
    s.add(intArrayOf(9, 8))
    s.add(intArrayOf(1, 8))
    s.add(intArrayOf(0, 0))
    s.add(intArrayOf(8, 0))
    s.add(intArrayOf(8, 8))
    s.count(intArrayOf(0, 8)).print()
}

class DetectSquares() {

    val mapX = HashMap<Int, ArrayList<Int>>()
    val mapY = HashMap<Int, ArrayList<Int>>()

    fun add(point: IntArray) {
        val x = point[0]
        val y = point[1]
        mapX[x] = mapX.getOrDefault(x, arrayListOf())
        mapX[x]!!.add(y)
        mapY[y] = mapY.getOrDefault(y, arrayListOf())
        mapY[y]!!.add(x)
    }

    fun count(point: IntArray): Int {
        var ans = 0
        val x = point[0]
        val y = point[1]
        // 选择X相同，Y不同的点
        for (ny in mapX.getOrDefault(x, arrayListOf())) {
            if (ny == y) continue
            // 边长diff
            val diff = abs(ny - y)
            ans += mapX.getOrDefault(x + diff, arrayListOf()).let {
                it.count { it == y } * it.count { it == ny }
            }
            ans += mapX.getOrDefault(x - diff, arrayListOf()).let {
                it.count { it == y } * it.count { it == ny }
            }
        }
        return ans
    }

}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * var obj = DetectSquares()
 * obj.add(point)
 * var param_2 = obj.count(point)
 */