package kickstart.round2020.a

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, b) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }.sorted()
        var ans = 0
        var cur = 0L
        for (i in arr.indices) {
            if (cur + arr[i] <= b) {
                ans++
                cur += arr[i]
            } else {
                break
            }
        }

        println("Case #${it + 1}: $ans")
    }
}