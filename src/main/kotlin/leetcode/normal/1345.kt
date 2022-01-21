package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = Solution1345()
    s.minJumps(intArrayOf(100, -23, -23, 404, 100, 23, 23, 23, 3, 404)).print()
}

class Solution1345 {
    fun minJumps(arr: IntArray): Int {
        val map = HashMap<Int, HashSet<Int>>()
        for (i in arr.indices) {
            map[arr[i]] = map.getOrDefault(arr[i], hashSetOf())
            map[arr[i]]!!.add(i)
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { -it.first }))
        // index step
        pq.offer(Pair(0, 0))
        val seen = HashSet<Int>()
        seen.add(0)
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            println("meet index ${item.first} val ${arr[item.first]} by step ${item.second}")
            if (item.first == arr.lastIndex) {
                return item.second
            }

            map[arr[item.first]]?.forEach {
                if (it !in seen) {
                    pq.offer(Pair(it, item.second + 1))
                    seen.add(it)
                }
            }
            map.remove(arr[item.first])
            arrayOf(item.first + 1, item.first - 1).forEach {
                if (it in arr.indices && it !in seen) {
                    pq.offer(Pair(it, item.second + 1))
                    seen.add(it)
                }
            }
        }
        return -1
    }
}