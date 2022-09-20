package kickstart.round2022.roundF

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet


fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, q) = readLine()!!.trim().split(" ").map { it.toInt() }
        val map = HashMap<Int, ArrayList<Int>>()
        repeat(n - 1) {
            val (a, b) = readLine()!!.trim().split(" ").map { it.toInt() }
            map[a] = map.getOrDefault(a, arrayListOf())
            map[b] = map.getOrDefault(b, arrayListOf())
            map[a]!!.add(b)
            map[b]!!.add(a)
        }
        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(1)
        val arr = ArrayList<Int>()
        val seen = HashSet<Int>()
        seen.add(1)
        while (queue.isNotEmpty()) {
            val size = queue.size
            arr.add(size)
            for (i in 0 until size) {
                val item = queue.poll()
                map[item]?.forEach {
                    if (it !in seen) {
                        queue.offer(it)
                        seen.add(it)
                    }
                }
            }
        }
//        arr.joinToString().print()
        repeat(q) {
            readLine()
        }
        var ans = 0
        var cur = q
        var index = 0
        while (cur > 0) {
            if (index in arr.indices && cur >= arr[index]) {
                cur -= arr[index]
                ans += arr[index]
                index++
            } else {
                break
            }
        }
        println("Case #${it + 1}: $ans")
    }
}