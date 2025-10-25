package atcoder.abc429

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    repeat(n) {
        if (it + 1 <= m) {
            println("OK")
        } else {
            println("Too Many Requests")
        }
    }
}