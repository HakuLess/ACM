package atcoder.abc235

import java.util.*
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val (a, n) = readLine()!!.trim().split(' ').map { it.toInt() }
    val target = n.toString().length
    val queue: Queue<Int> = LinkedList<Int>()
    queue.offer(1)
    val seen = HashSet<Int>()
    seen.add(1)
    var step = -1
    while (queue.isNotEmpty()) {
        val size = queue.size
        step++
        for (i in 0 until size) {
            val item = queue.poll()
            if (item == n) {
                println(step)
                return
            }
            if (step > 200) {
                println(-1)
                return
            }
            if (item * a !in seen) {
                queue.offer(item * a)
                seen.add(item * a)
            }
            if (item > 10 && item % 10 != 0 && item < 1e6) {
                val next = item.toString().let { "${it.last()}${it.substring(0, it.length - 1)}".toInt() }
                if (next !in seen && next.toString().length <= target ) {
                    queue.offer(next)
                    seen.add(next)
                }
            }
        }
    }
    println(-1)
}