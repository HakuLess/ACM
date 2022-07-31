package kickstart.round2022.roundD

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, m) = readLine().toString().split(" ").map { it.toInt() }
        val arr = readLine().toString().split(" ").map { it.toInt() }.sortedDescending()
        var ans = 0.0
        val left = arrayListOf<Int>()
        for (i in arr.indices) {
            if (i < m - 1)
                ans += arr[i]
            else {
                left.add(arr[i])
            }
        }
        if (left.size % 2 == 0) {
            ans += (left[left.size / 2 - 1] + left[left.size / 2]).toDouble() / 2
        } else {
            ans += left[left.size / 2]
        }
        println("Case #${it + 1}: $ans")
    }
}