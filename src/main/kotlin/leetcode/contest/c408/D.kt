package leetcode.contest.c408

import utils.UFS
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SolutionD {
    fun canReachCorner(X: Int, Y: Int, circles: Array<IntArray>): Boolean {

        val n = circles.size
        val ufs = UFS(n)
        for (i in circles.indices) {
            for (j in i + 1 until circles.size) {
                val (x1, y1, r1) = circles[i]
                val (x2, y2, r2) = circles[j]
                // 圆心距离小于 半径距离和 圆相交
                if ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) <= (r2 + r1) * (r2 + r1)) {
                    ufs.union(i, j)
                }
            }
        }
        val map = HashMap<Int, HashSet<Int>>()
        for (i in circles.indices) {
            val key = ufs.find(i)
            val set = map.getOrDefault(key, HashSet<Int>())
            val (x, y, r) = circles[i]
            // 左右下上
            if (0 in x - r..x + r && y in 0..Y) {
                set.add(1)
            }
            if (X in x - r..x + r && y in 0..Y) {
                set.add(2)
            }
            if (0 in y - r..y + r && x in 0..X) {
                set.add(3)
            }
            if (Y in y - r..y + r && x in 0..X) {
                set.add(4)
            }
            map[key] = set
        }

        map.forEach {
            val set = it.value
            val lt = set.contains(1) || set.contains(4)
            val rb = set.contains(2) || set.contains(3)
            if (lt && rb) return false
        }

        return true
    }
}