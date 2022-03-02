package atcoder.abc241

import java.util.*

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val tm = TreeMap<Long, Int>()
    repeat(n) {
        val arr = readLine()!!.split(" ").map { it.toLong() }
        when (arr[0]) {
            1L -> {
                tm[arr[1]] = tm.getOrDefault(arr[1], 0) + 1
            }
            2L -> {
                var left = arr[2]
                var key = arr[1]
                while (left > 0L) {
                    val nextKey = tm.floorKey(key) ?: null
                    if (nextKey == null) {
                        println(-1)
                        break
                    }
                    left -= tm[nextKey]!!
                    key = if (left > 0L) nextKey - 1
                    else nextKey
                }
                if (left <= 0) {
                    println(key)
                }
            }
            3L -> {
                var left = arr[2]
                var key = arr[1]
                while (left > 0L) {
                    val nextKey = tm.ceilingKey(key) ?: null
                    if (nextKey == null) {
                        println(-1)
                        break
                    }
                    left -= tm[nextKey]!!
                    key = if (left > 0L) nextKey + 1
                    else nextKey
                }
                if (left <= 0) {
                    println(key)
                }
            }
        }
    }
}