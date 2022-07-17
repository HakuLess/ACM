package kickstart.round2022.roundB

fun main() {
    val pai = 3.1415926535897932
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        var (r, a, b) = readLine().toString().split(' ').map { it.toLong() }
        var ans = r * r
        while (r != 0L) {
            r *= a
            ans += r * r
            r /= b
            ans += r * r
        }
        println("Case #${it + 1}: ${ans * pai}")
    }
}