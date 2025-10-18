package atcoder.abc428

fun main() {
    val (s, a, b, x) = readLine()!!.split(" ").map { it.toInt() }
    val cyc = a + b
    val times = x / cyc
    val last = x % cyc
    val ans = s * times * a + s * minOf(last, a)
    println(ans)
}