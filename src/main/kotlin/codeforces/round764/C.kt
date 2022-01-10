package codeforces.round764

import java.util.*

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }
        val n = arr.size
        val pq = PriorityQueue<Int>(compareBy { -it })
        arr.forEach {
            pq.offer(it)
        }
        val seen = BooleanArray(n + 1)
        seen[0] = true
        while (pq.isNotEmpty()) {
            val cur = pq.poll()
            if (cur > n) {
                pq.offer(cur / 2)
            } else if (seen[cur] && cur != 0) {
                pq.offer(cur / 2)
            } else {
                seen[cur] = true
            }
        }
        println(if (seen.all { it }) "YES" else "NO")
    }
}