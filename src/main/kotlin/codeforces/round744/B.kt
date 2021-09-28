package codeforces.round744

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        val sorted = arr.sorted()

        val cur = ArrayList(arr)
        var left = 0
        val ans = ArrayList<String>()
        var res = 0
        while (cur.isNotEmpty()) {
            val min = cur.minOrNull()!!
            if (cur.first() == min) {
                cur.removeAt(0)
            } else {
                val index = cur.indexOf(min)
                ans.add("${left + 1} ${index + left + 1} ${index}")
                cur.removeAt(index)
                res++
            }
            left++
        }
        println(res)
        ans.forEach {
            println(it)
        }
    }
}