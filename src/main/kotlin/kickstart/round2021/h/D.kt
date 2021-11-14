package kickstart.round2021.h

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

// TODO 分数处理
fun main() {
    val t = readLine()!!.trim().toInt()
    val mod = 1000000007L
    repeat(t) {
        val (n, q) = readLine()!!.split(" ").map { it.toInt() }
        val p1 = readLine()!!.toLong()

        val arr = ArrayList<Triple<Int, Long, Long>>()
        val map = HashMap<Int, ArrayList<Triple<Int, Long, Long>>>()
        repeat(n - 1) {
            val (par, occ, nocc) = readLine()!!.split(" ").map { it.toLong() }
            val pi = par.toInt()
            arr.add(Triple(pi, occ, nocc))

            map[pi] = map.getOrDefault(pi, arrayListOf())
            map[pi]!!.add(Triple(it + 2, occ, nocc))
        }

        val res = Array<Pair<Long, Long>>(n + 1) { Pair(0, 0) }

        // 点、发生概率、不发生概率
        val pq: Queue<Triple<Int, Long, Long>> = LinkedList<Triple<Int, Long, Long>>()
        pq.offer(Triple(1, p1, 1000000L - p1))
        while (pq.isNotEmpty()) {
            val size = pq.size
            for (i in 0 until size) {
                val item = pq.poll()
                res[item.first] = Pair(item.second, item.third)

                map[item.first]?.forEach {
                    pq.offer(
                        Triple(
                            it.first,
                            (it.second * item.second + it.third * item.third) % mod,
                            (it.second * (1000000L - item.second) + it.third * (1000000L - item.third)) % mod
                        )
                    )
                }
            }
        }

        res.forEach {
            println(it)
        }
        val ans = ArrayList<Long>()
        repeat(q) {
            val (a, b) = readLine()!!.split(" ").map { it.toInt() }
            ans.add(res[a].first * res[b].first)
        }
        println("Case #${it + 1}: ${ans.joinToString(" ")}")
    }
}