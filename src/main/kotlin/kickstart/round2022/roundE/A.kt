package kickstart.round2022.roundE

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val ans = (n - 1) / 5 + 1
        println("Case #${it + 1}: $ans")
    }
}