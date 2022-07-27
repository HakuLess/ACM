package kickstart.round2022.roundD

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine().toString().toInt()
        val a = readLine().toString().split(" ").map { it.toLong() }
        val m = readLine().toString().toInt()
        val b = readLine().toString().split(" ").map { it.toLong() }
        val k = readLine().toString().toInt()

        val minA = LongArray(n + 1) { Long.MAX_VALUE }
        minA[0] = 0L
        for (i in 0 until n) {
            var sum = 0L
            for (j in i until n) {
                sum += a[j]
                minA[j - i + 1] = minOf(minA[j - i + 1], sum)
            }
        }

        val minB = LongArray(m + 1) { Long.MAX_VALUE }
        minB[0] = 0L
        for (i in 0 until m) {
            var sum = 0L
            for (j in i until m) {
                sum += b[j]
                minB[j - i + 1] = minOf(minB[j - i + 1], sum)
            }
        }

        var ans = 0L
        a.forEach {
            ans += it
        }
        b.forEach {
            ans += it
        }
        var minus = Long.MAX_VALUE
        val left = n + m - k
        for (i in 0 until left) {
            if (i in minA.indices && left - i in minB.indices) {
                minus = minOf(minus, minA[i] + minB[left - i])
            }
        }
        if (minus == Long.MAX_VALUE) minus = 0L
        println("Case #${it + 1}: ${ans - minus}")
    }
}