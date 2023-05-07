package leetcode.lccup.round2023.spring.team

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.extractMantra(arrayOf("sd", "ep"), "speed").print()
}

class SolutionC {
    fun extractMantra(matrix: Array<String>, mantra: String): Int {
        val set = HashSet<Char>()
        matrix.map { it.toCharArray() }.forEach {
            set.addAll(it.toList())
        }

        if (mantra.any { it !in set }) return -1

        data class Step(val x: Int, val y: Int, val index: Int)

        val seen = HashSet<Step>()

        val queue: Queue<Step> = LinkedList<Step>()
        queue.offer(Step(0, 0, 0))
        var ans = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            ans++
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.index !in mantra.indices) {
                    return ans
                }
                if (matrix[item.x][item.y] == mantra[item.index]) {
                    queue.offer(Step(item.x, item.y, item.index + 1))
                }
                dir4.forEach {
                    val nextX = item.x + it[0]
                    val nextY = item.y + it[1]
                    if (nextX in matrix.indices && nextY in matrix[0].indices) {
                        val next = Step(nextX, nextY, item.index)
                        if (next !in seen) {
                            seen.add(next)
                            queue.offer(next)
                        }
                    }
                }
            }
        }
        return -1
    }

//    fun extractMantra(matrix: Array<String>, mantra: String): Int {
//        val set = HashSet<Char>()
//        val map = HashMap<Char, ArrayList<Pair<Int, Int>>>()
//
//        for (i in matrix.indices) {
//            for (j in matrix[0].indices) {
//                val c = matrix[i][j]
//                map[c] = map.getOrDefault(c, arrayListOf())
//                map[c]!!.add(Pair(i, j))
//                set.add(c)
//            }
//        }
//
//        if (mantra.any { it !in set }) return -1
//
//        val seen = HashMap<String, Int>()
//
//        fun dfs(pos: Pair<Int, Int>, index: Int): Int {
//            val key = "${pos}_$index"
//            if (key in seen) return seen[key]!!
//
//            if (index !in mantra.indices) return 0
//            val target = mantra[index]
//            var tmp = Int.MAX_VALUE
//            map[target]!!.forEach {
//                tmp = minOf(tmp, dfs(it, index + 1) + abs(pos.first - it.first) + abs(pos.second - it.second) + 1)
//            }
//            return tmp.also {
//                seen[key] = it
//            }
//        }
//
//        return dfs(Pair(0, 0), 0)
//    }
}