package atcoder.abc429

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }

    val freq = IntArray(n + 1)
    for (num in arr) {
        freq[num]++
    }

    var ans = 0L

    for (i in 1..n) {
        if (freq[i] >= 2) {
            val pairs = 1L * freq[i] * (freq[i] - 1) / 2
            val distinct = n - freq[i]
            ans += pairs * distinct
        }
    }

    println(ans)
}
