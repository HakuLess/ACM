package codeforces.round744

import java.util.*

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -it.second })
        readLine()!!.trim().split(" ").map { it.toInt() }.forEachIndexed { index, value ->
            if (value != 0)
                pq.offer(Pair(index + 1, value))
        }
        val ans = ArrayList<String>()
        while (pq.size >= 2) {
            val a = pq.poll()
            val b = pq.poll()
            ans.add("${a.first} ${b.first}")
            if (a.second - 1 != 0) pq.offer(Pair(a.first, a.second - 1))
            if (b.second - 1 != 0) pq.offer(Pair(b.first, b.second - 1))
        }
        println(ans.size)
        ans.forEach {
            println(it)
        }
    }
}