package kickstart.round2020.b

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
        var ans = 0L
        for (i in 1 until arr.lastIndex) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) ans++
        }

        println("Case #${it + 1}: $ans")
    }
}