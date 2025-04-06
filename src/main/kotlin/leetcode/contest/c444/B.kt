package leetcode.contest.c444

import utils.SortedList
import utils.print
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val router = Router(3)
    router.addPacket(1, 4, 90).print(); // 数据包被添加，返回 True。
    router.addPacket(2, 5, 90).print(); // 数据包被添加，返回 True。
    router.addPacket(1, 4, 90).print(); // 这是一个重复数据包，返回 False。
    router.addPacket(3, 5, 95).print(); // 数据包被添加，返回 True。
    router.addPacket(4, 5, 105).print(); // 数据包被添加，[1, 4, 90] 被移除，因为数据包数量超过限制，返回 True。
    router.forwardPacket().print(); // 转发数据包 [2, 5, 90] 并将其从路由器中移除。
    router.addPacket(5, 2, 110).print(); // 数据包被添加，返回 True。
    router.getCount(5, 100, 110).print(); // 唯一目标地址为 5 且时间在 [100, 1
}

class Router(val memoryLimit: Int) {

    private data class Key(val s: Int, val d: Int, val t: Int) : Comparable<Key> {
        override fun compareTo(other: Key): Int {
            if (t > other.t) return 1
            if (t < other.t) return -1
            return 0
        }
    }

    private val queue = ArrayDeque<Key>()
    private val setTriple = HashSet<Key>()
    private val destMap = HashMap<Int, SortedList<Key>>()

    fun addPacket(source: Int, destination: Int, timestamp: Int): Boolean {

        val key = Key(source, destination, timestamp)

        if (!setTriple.add(key)) return false

        if (queue.size == memoryLimit) {
            val old = queue.removeFirst()
            setTriple.remove(Key(old.s, old.d, old.t))
            destMap[old.d]?.remove(old)
        }

        destMap.getOrPut(destination) { SortedList<Key>() }.insert(key)
        queue.addLast(key)
        return true
    }

    fun forwardPacket(): IntArray {
        val p = queue.removeFirstOrNull() ?: return intArrayOf()
        setTriple.remove(Key(p.s, p.d, p.t))
        destMap[p.d]?.remove(p)
        return intArrayOf(p.s, p.d, p.t)
    }

    fun getCount(destination: Int, startTime: Int, endTime: Int): Int {
        val start = Key(0, 0, startTime - 1)
        val end = Key(0, 0, endTime)
        return destMap[destination]!!.run {
            smallerThanAndEqual(end) - smallerThanAndEqual(start)
        }
    }

}

/**
 * Your Router object will be instantiated and called as such:
 * var obj = Router(memoryLimit)
 * var param_1 = obj.addPacket(source,destination,timestamp)
 * var param_2 = obj.forwardPacket()
 * var param_3 = obj.getCount(destination,startTime,endTime)
 */