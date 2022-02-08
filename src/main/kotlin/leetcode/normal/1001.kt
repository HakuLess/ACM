package leetcode.normal

import utils.dir9
import utils.print
import utils.toGrid

fun main() {
    val s = Solution1001()
    s.gridIllumination(5, "[[0,0],[4,4]]".toGrid(), "[[1,1],[1,0]]".toGrid()).print()
//    s.gridIllumination(5, "[[0,0],[0,1],[0,4]]".toGrid(), "[[0,0],[0,1],[0,2]]".toGrid()).print()
}

class Solution1001 {
    fun gridIllumination(n: Int, lamps: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val setX = HashMap<Int, Int>()
        val setY = HashMap<Int, Int>()
        val setSum = HashMap<Int, Int>()
        val setDiff = HashMap<Int, Int>()
        val map = HashMap<Int, HashSet<Int>>()
        lamps.toSet().forEach {
            if (it[1] !in map.getOrDefault(it[0], hashSetOf())) {
                setX[it[0]] = setX.getOrDefault(it[0], 0) + 1
                setY[it[1]] = setY.getOrDefault(it[1], 0) + 1
                setSum[it[0] + it[1]] = setSum.getOrDefault(it[0] + it[1], 0) + 1
                setDiff[it[0] - it[1]] = setDiff.getOrDefault(it[0] - it[1], 0) + 1

                map[it[0]] = map.getOrDefault(it[0], hashSetOf())
                map[it[0]]!!.add(it[1])
            }
        }

        val ans = ArrayList<Int>()
        for (i in queries.indices) {
            val it = queries[i]
            if (it[0] in setX.keys ||
                it[1] in setY.keys ||
                (it[0] + it[1]) in setSum.keys ||
                (it[0] - it[1]) in setDiff.keys
            )
                ans.add(1)
            else
                ans.add(0)
            dir9.forEach { dir ->
                val nextX = it[0] + dir[0]
                val nextY = it[1] + dir[1]
                if (nextY in map.getOrDefault(nextX, hashSetOf())) {
                    setX[nextX] = setX.getOrDefault(nextX, 0) - 1
                    if (setX[nextX] == 0) {
                        setX.remove(nextX)
                    }
                    setY[nextY] = setY.getOrDefault(nextY, 0) - 1
                    if (setY[nextY] == 0) {
                        setY.remove(nextY)
                    }
                    setSum[nextX + nextY] = setSum.getOrDefault(nextX + nextY, 0) - 1
                    if (setSum[nextX + nextY] == 0) {
                        setSum.remove(nextX + nextY)
                    }
                    setDiff[nextX - nextY] = setDiff.getOrDefault(nextX - nextY, 0) - 1
                    if (setDiff[nextX - nextY] == 0) {
                        setDiff.remove(nextX - nextY)
                    }
                    map[nextX]!!.remove(nextY)
                }
            }
        }
        return ans.toIntArray()
    }
}