package atcoder.abc222

fun main(args: Array<String>) {
    val (n, p) = readLine()!!.trim().split(' ').map { it.toInt() }
    val arr = readLine()!!.trim().split(' ').map { it.toInt() }
    println(arr.count { it < p })
}