package atcoder.abc241

fun main(args: Array<String>) {
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }
    var cur = 0
    repeat(3) {
        cur = arr[cur]
    }
    println(cur)
}