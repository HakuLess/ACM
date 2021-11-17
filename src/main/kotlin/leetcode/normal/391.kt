package leetcode.normal

import utils.print
import utils.toGrid

fun main() {
    val s = Solution391()
//    s.isRectangleCover("[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]".toGrid()).print()
//    s.isRectangleCover("[[0,0,4,1],[0,0,4,1]]".toGrid()).print()
    s.isRectangleCover("[[0,0,4,1],[7,0,8,2],[5,1,6,3],[6,0,7,2],[4,0,5,1],[4,2,5,3],[2,1,4,3],[0,2,2,3],[0,1,2,2],[6,2,8,3],[5,0,6,1],[4,1,5,2]]".toGrid())
        .print()
}

// 扫描线
class Solution391 {
    fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
        val arr = ArrayList<IntArray>()
        var minX = Int.MAX_VALUE / 2
        var maxX = Int.MIN_VALUE / 2
        rectangles.forEach {
            val (x0, y0, x1, y1) = it
            arr.add(intArrayOf(x0, y0, y1, 1))
            arr.add(intArrayOf(x1, y0, y1, -1))
            minX = minOf(minX, x0)
            maxX = maxOf(maxX, x1)
        }

        arr.groupBy { it[0] }.forEach {
            if (it.key == minX) {
                val sorted = it.value.sortedBy { it[1] }
                for (i in 1 until sorted.size) {
                    if (sorted[i][1] != sorted[i - 1][2]) return false
                }
            }
            if (it.key == minX || it.key == maxX) return@forEach
            // 非边界，需要可以抵消
            val left = ArrayList<IntArray>()
            val right = ArrayList<IntArray>()
            it.value.forEach {
                if (it[3] == 1) {
                    // 右侧的左边
                    right.add(intArrayOf(it[1], it[2]))
                } else {
                    // 左侧的右边
                    left.add(intArrayOf(it[1], it[2]))
                }
            }
            left.sortBy { it[0] }
            right.sortBy { it[0] }

            while (left.isNotEmpty() && right.isNotEmpty()) {
                if (left[0][0] != right[0][0]) return false
                if (left[0][1] == right[0][1]) {
                    left.removeAt(0)
                    right.removeAt(0)
                } else if (left[0][1] > right[0][1]) {
                    val lt = left[0][1]
                    left.removeAt(0)
                    left.add(0, intArrayOf(right[0][1], lt))
                    right.removeAt(0)
                } else {
                    val rt = right[0][1]
                    right.removeAt(0)
                    right.add(0, intArrayOf(left[0][1], rt))
                    left.removeAt(0)
                }
            }
            if (left.isNotEmpty() || right.isNotEmpty()) return false
        }
        return true
    }

//    fun isRectangleCover(rectangles: Array<IntArray>): Boolean {
//        var minX = Int.MAX_VALUE / 2
//        var maxX = Int.MIN_VALUE / 2
//        var minY = Int.MAX_VALUE / 2
//        var maxY = Int.MIN_VALUE / 2
//
//        val map = HashMap<Pair<Int, Int>, Int>()
//        var area = 0L
//        rectangles.forEach {
//            val (a, b, c, d) = it
//            minX = minOf(minX, a)
//            minY = minOf(minY, b)
//            maxX = maxOf(maxX, c)
//            maxY = maxOf(maxY, d)
//            map[Pair(a, b)] = map.getOrDefault(Pair(a, b), 0) + 1
//            map[Pair(a, d)] = map.getOrDefault(Pair(a, d), 0) + 1
//            map[Pair(c, b)] = map.getOrDefault(Pair(c, b), 0) + 1
//            map[Pair(c, d)] = map.getOrDefault(Pair(c, d), 0) + 1
//            area += (d - b).toLong() * (c - a)
//        }
//
//        map[Pair(minX, minY)] = map.getOrDefault(Pair(minX, minY), 0) - 1
//        map[Pair(minX, maxY)] = map.getOrDefault(Pair(minX, maxY), 0) - 1
//        map[Pair(maxX, minY)] = map.getOrDefault(Pair(maxX, minY), 0) - 1
//        map[Pair(maxX, maxY)] = map.getOrDefault(Pair(maxX, maxY), 0) - 1
//        val totalArea = (maxY - minY).toLong() * (maxX - minX)
//
//        return (area == totalArea) && map.all { it.value == 2 || it.value == 4 || it.value == 0}
//    }
}