package atcoder.abc235

fun main(args: Array<String>) {
    readLine()
    val arr = readLine()!!.trim().split(' ').map { it.toInt() }
    var cur = 0
    while (cur + 1 in arr.indices && arr[cur + 1] > arr[cur]) {
        cur++
    }
    println(arr[cur])
}