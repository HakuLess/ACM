package atcoder.abc240

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }
    println(arr.toHashSet().size)
}