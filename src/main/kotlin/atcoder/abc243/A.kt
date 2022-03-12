package atcoder.abc243

fun main() {
    val (v, a, b, c) = readLine()!!.split(" ").map { it.toInt() }
    var lst = v % (a + b + c)
    if (lst >= a) {
        lst -= a
        if (lst >= b) {
            lst -= b
            println("T")
        } else {
            println("M")
        }
    } else {
        println("F")
    }
}