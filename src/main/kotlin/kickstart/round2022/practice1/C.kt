package kickstart.round2022.practice1

import kotlin.collections.ArrayList

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
        val ans = ArrayList<Int>()
        ans.add(0)
        val map = HashMap<Int, Int>()
        var sum = 0
        for (i in arr.indices) {
            val v = arr[i]
            val lst = ans.last()
            if (v <= lst) {
                ans.add(lst)
                continue
            }
            map[v] = map.getOrDefault(v, 0) + 1
            sum++
            if (sum - map.getOrDefault(lst, 0) > lst) {
                sum -= map.getOrDefault(lst, 0)
                ans.add(lst + 1)
            } else {
                ans.add(lst)
            }
        }
        ans.removeAt(0)
        println("Case #${it + 1}: ${ans.joinToString(" ")}")
    }
}