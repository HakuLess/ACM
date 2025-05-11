package leetcode.contest.c449

import utils.UFS
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionC()
    // 130
    s.maxScore(7, "[[0,1],[1,2],[2,0],[3,4],[4,5],[5,6]]".toGrid()).print()
    // 82
    s.maxScore(6, "[[0,3],[4,5],[2,0],[1,3],[2,4],[1,5]]".toGrid()).print()
    // 86
    s.maxScore(8, "[[4,7],[2,1]]".toGrid()).print()

    // 2517
    s.maxScore(
        20,
        "[[18,14],[12,18],[12,9],[1,9],[7,1],[5,7],[0,13],[6,0],[6,16],[15,16],[10,19],[17,4],[2,4],[2,3],[3,8]]".toGrid()
    ).print()
}

class SolutionC {

    fun maxScore(n: Int, edges: Array<IntArray>): Long {

        val ufs = UFS(n)
        val deg = IntArray(n) { 0 }
        for ((u, v) in edges) {
            deg[u]++
            deg[v]++
            ufs.union(u, v)
        }

        val circleMap = HashMap<Int, Boolean>()
        val cntMap = HashMap<Int, Int>()
        for (i in 0 until n) {
            val root = ufs.find(i)
            if (deg[i] == 1) {
                circleMap[root] = false
            } else {
                circleMap[root] = circleMap.getOrDefault(root, true) && true
            }
            cntMap[root] = cntMap.getOrDefault(root, 0) + 1
        }

        var ans = 0L
        var cur = n.toLong()
        val lCircle = ArrayList<Int>()
        val lLine = ArrayList<Int>()
        for (key in circleMap.keys) {
            if (circleMap[key]!!) {
                lCircle.add(cntMap[key]!!)
            } else {
                lLine.add(cntMap[key]!!)
            }
        }

        // 行程环，至少为3
        fun cal(size: Int, isCircle: Boolean): Long {
            if (size == 1) {
                return 0L
            }
            if (size == 2) {
                val tmp = 1L * cur * (cur - 1)
                cur -= 2
                return tmp
            }

            var tmp = 0L
            var leftSize = size
            val pq = PriorityQueue<Long>(compareBy { -it })
            pq.offer(cur - 1)
            pq.offer(cur - 2)

            // 保留最后两个，进行环链接乘积
            val q: Queue<Long> = LinkedList<Long>()
            q.offer(cur - 1)
            q.offer(cur - 2)

            tmp += 1L * cur * (cur - 1) + 1L * cur * (cur - 2)
            leftSize -= 3
            cur -= 3
            while (leftSize > 0) {
                val item = pq.poll()
                tmp += cur * item
//                println("add $cur * $item")
                pq.offer(cur)

                q.poll()
                q.offer(cur)

                cur--
                leftSize--
            }

            val a = q.poll()
            val b = q.poll()
            if (isCircle) {
                tmp += 1L * a * b
            }
            return tmp
        }

        lCircle.sort()
        lLine.sortDescending()

        for (i in lCircle.indices) {
            ans += cal(lCircle[i], true)
        }
        for (i in lLine.indices) {
            ans += cal(lLine[i], false)
        }

        return ans
    }
}