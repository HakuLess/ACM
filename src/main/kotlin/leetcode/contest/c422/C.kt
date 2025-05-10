package leetcode.contest.c422

import utils.biMax
import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    s.minTimeToReach("[[21,77],[22,11]]".toGrid()).print()

}

fun query(): Int {
    val list = ArrayList<Int>()
    list.add(10)
    list.add(100)
    list.add(50)
    val target = 55
    var left = 1
    var right = list.lastIndex
    while (left < right) {
        val mid = (left + right) / 2
        if (list[mid] < target) {
            left = mid
        } else if (list[mid] > target) {
            right = mid
        } else {
            return list[mid]
        }
    }
    return list[left]
}

class SolutionC {
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val seen = HashMap<Pair<Int, Int>, Int>()
        // 时间 + 坐标
        val queue = PriorityQueue<Item>(compareBy({ it.t }, { it.cost }))
        queue.offer(Item(0, 1, 0, 0))
        while (queue.isNotEmpty()) {
            val (t, cost, x, y) = queue.poll()
            if (x == moveTime.lastIndex && y == moveTime[0].lastIndex) {
                return t
            }
            dir4.forEach {
                val nextX = x + it[0]
                val nextY = y + it[1]
                val key = Pair(nextX, nextY)

//                println("$key with ${seen[key]}")
                if (nextX in moveTime.indices && nextY in moveTime[0].indices) {
                    val nextCost = maxOf(t, moveTime[nextX][nextY]) + cost
                    if (seen.getOrDefault(key, Int.MAX_VALUE) > nextCost) {
                        seen[key] = nextCost
                        queue.offer(Item(nextCost, 3 - cost, nextX, nextY))
//                        println("offer ${Item(nextCost, 3 - cost, nextX, nextY)}")
                    }
                }
            }
        }

        return -1
    }
}

data class Item(val t: Int, val cost: Int, val x: Int, val y: Int)