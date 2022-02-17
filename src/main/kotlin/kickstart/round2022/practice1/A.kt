package kickstart.round2022.practice1

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, m) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
        val sum = arr.sum()
        println("Case #${it + 1}: ${sum % m}")
    }
}