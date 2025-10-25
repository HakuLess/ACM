package atcoder.abc429

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }
    val sum = arr.sum()
    for (i in arr.indices) {
        if (sum - arr[i] == m) {
            println("Yes")
            return
        }
    }
    println("No")
}