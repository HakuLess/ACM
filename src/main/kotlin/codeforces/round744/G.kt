package codeforces.round744

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        var l = 0
        var r = 0
        var min = 0
        var max = 0
        for (i in arr.indices) {
            if (i == 0) {
                l = 0
                r = arr[i]
            } else if (i % 2 == 0) {
                r = l + arr[i]
            } else {
                l = r - arr[i]
            }
            min = minOf(min, l)
            max = maxOf(max, r)
        }
        println(max - min)
    }
}
