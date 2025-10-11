package leetcode.normal

import utils.print
import utils.printLong
import java.util.TreeMap

fun main() {
    val s = Solution3186()
    s.maximumTotalDamage(intArrayOf(7, 1, 6, 6)).print()
    // 31
    s.maximumTotalDamage(intArrayOf(5, 9, 2, 10, 2, 7, 10, 9, 3, 8)).print()
}

class Solution3186 {
    fun maximumTotalDamage(power: IntArray): Long {
        val map = HashMap<Int, Long>()
        for (i in power.indices) {
            val item = power[i]
            map[item] = map.getOrDefault(item, 0) + item
        }
//        map.printLong()
        val tm = TreeMap<Int, Long>()
        var max = 0L
        map.keys.sorted().forEach { key ->
//            println("key $key with key - 3 ${tm.floorKey(key - 3)}")
            val last = tm.floorEntry(key - 3)?.value ?: 0L
            tm[key] = maxOf(last + map[key]!!, max)
            max = tm[key]!!
        }
        return tm.values.max()
    }
}