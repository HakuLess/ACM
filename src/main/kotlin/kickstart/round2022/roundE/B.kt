package kickstart.round2022.roundE

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        val ts = TreeSet<Int>()
        val map = HashMap<Int, Int>()
        arr.forEach {
            ts.add(it)
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val ans = ArrayList<Int>()
        arr.forEach {
            if (map[it] == 1)
                ts.remove(it)
            ans.add(ts.floor(it * 2) ?: -1)
            ts.add(it)
        }
        println("Case #${it + 1}: ${ans.joinToString(" ")}")
    }
}