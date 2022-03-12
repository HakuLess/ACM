package atcoder.abc243

fun main() {
    val n = readLine()
    val a = readLine()!!.split(" ").map { it.toInt() }
    val b = readLine()!!.split(" ").map { it.toInt() }
    var ans0 = 0
    for (i in a.indices) {
        if (a[i] == b[i]) {
            ans0++
        }
    }
    val set = b.toSet()
    val ans1 = a.count { it in set }
    println(ans0)
    println(ans1 - ans0)
}