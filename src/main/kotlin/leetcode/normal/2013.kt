package leetcode.normal

import kotlin.math.abs

class DetectSquares() {

    private val mapX = HashMap<Int, HashMap<Int, Int>>()
    private val mapY = HashMap<Int, HashMap<Int, Int>>()

    fun add(point: IntArray) {
        val (x, y) = point
        mapX[x] = mapX.getOrDefault(x, hashMapOf())
        mapX[x]!![y] = mapX[x]!!.getOrDefault(y, 0) + 1
        mapY[y] = mapY.getOrDefault(y, hashMapOf())
        mapY[y]!![x] = mapY[y]!!.getOrDefault(x, 0) + 1
    }

    fun count(point: IntArray): Int {
        var ans = 0
        val (x, y) = point
        // 选择X相同，Y不同的点
        for (ny in mapX.getOrDefault(x, hashMapOf())) {
            if (ny.key == y) continue
            // 边长diff
            val diff = abs(ny.key - y)
            ans += mapX.getOrDefault(x + diff, hashMapOf()).let {
                ny.value * it.getOrDefault(y, 0) * it.getOrDefault(ny.key, 0)
            }
            ans += mapX.getOrDefault(x - diff, hashMapOf()).let {
                ny.value * it.getOrDefault(y, 0) * it.getOrDefault(ny.key, 0)
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