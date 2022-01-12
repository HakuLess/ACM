package codeforces.round764

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        val arr = readLine()!!.split(" ").map { it.toInt() }
        val n = arr.size
        val seen = BooleanArray(n + 1)
        seen[0] = true
        arr.forEach {
            var cur = it
            while (cur > 0) {
                if (cur <= n && !seen[cur]) {
                    seen[cur] = true
                    break
                }
                cur /= 2
            }
        }
        println(if (seen.all { it }) "YES" else "NO")
    }
}