package kickstart.round2022.roundF

import java.util.*


fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        // 天数，种量，每日可种植数
        val (d, n, x) = readLine()!!.trim().split(" ").map { it.toLong() }
        // 数量 天数 价格
        val seeds = PriorityQueue<Triple<Long, Long, Long>>(compareBy { it.second })
        repeat(n.toInt()) {
            val (q, l, v) = readLine()!!.split(" ").map { it.toLong() }
            seeds.offer(Triple(q, l, v))
        }
        var ans = 0L
        var cur = 0L
        // 天数满足，只考虑价格
        val p = PriorityQueue<Triple<Long, Long, Long>>(compareBy { -it.third })
        while (seeds.isNotEmpty() || p.isNotEmpty()) {
            if (p.isEmpty()) {
                val item = seeds.poll()
                p.offer(item)
                cur = maxOf(cur, item.second)
            }
            while (seeds.isNotEmpty() && seeds.peek().second <= cur) {
                p.offer(seeds.poll())
            }
            if (cur >= d) break

            var left = x
            while (left != 0L && p.isNotEmpty()) {
                val item = p.poll()
                if (item.first <= left) {
                    ans += item.third * item.first
                    left -= item.first
                } else {
                    ans += item.third * left
                    p.offer(Triple(item.first - left, item.second, item.third))
                    left = 0
                }
            }

//            println("add $item with d $cur with v ${item.third}  total is $ans")
            cur++
        }
        println("Case #${it + 1}: $ans")
    }
}