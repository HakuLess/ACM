package kickstart.round2022.practice1

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, m, p) = readLine()!!.trim().split(' ').map { it.toInt() }
        val friends = ArrayList<String>()
        repeat(n) {
            friends.add(readLine()!!)
        }
        val forbid = ArrayList<String>()
        repeat(m) {
            forbid.add(readLine()!!)
        }

        val l = IntArray(p)
        val r = IntArray(p)
        for (i in friends.indices) {
            for (j in friends[i].indices) {
                if (friends[i][j] == '0') {
                    l[j]++
                } else {
                    r[j]++
                }
            }
        }

        fun getAns(cur: String): Int {
            var ans = 0
            for (i in cur.indices) {
                ans += if (cur[i] == '0')
                    r[i]
                else
                    l[i]
            }
            return ans
        }

        val start = StringBuilder()
        for (i in l.indices) {
            if (l[i] > r[i])
                start.append('0')
            else
                start.append('1')
        }

        val seen = HashSet<String>()
        val queue: Queue<Pair<String, Int>> = PriorityQueue<Pair<String, Int>>(compareBy { it.second })

        queue.offer(Pair(start.toString(), getAns(start.toString())))
        seen.add(start.toString())
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.first !in forbid) {
                    println("Case #${it + 1}: ${item.second}")
                    return@repeat
                }
                for (j in item.first.indices) {
                    val sb = StringBuilder(item.first)
                    val lst = sb[j]
                    if (lst == '0') {
                        sb[j] = '1'
                    } else if (lst == '1') {
                        sb[j] = '0'
                    }
                    if (sb.toString() !in seen) {
                        queue.offer(Pair(sb.toString(), getAns(sb.toString())))
                        seen.add(sb.toString())
                    }
                }
            }
        }
    }
}