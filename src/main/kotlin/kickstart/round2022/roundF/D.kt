package kickstart.round2022.roundF

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        // 总Leader，见Leader，会议时长，总日时长
        val (n, k, x, d) = readLine()!!.trim().split(" ").map { it.toInt() }
        val m = readLine()!!.trim().toInt()
        val arr = ArrayList<Triple<Int, Int, Int>>()
        repeat(m) {
            val (p, l, r) = readLine()!!.split(" ").map { it.toInt() }
            arr.add(Triple(p, l, r))
        }
        val ins = arr.sortedBy { it.first }
        val outs = arr.sortedBy { it.second }

        val cnt = IntArray(d + 1) { n }
        for (i in cnt.indices) {
            
        }
        var ans = 0
        println("Case #${it + 1}: $ans")
    }
}