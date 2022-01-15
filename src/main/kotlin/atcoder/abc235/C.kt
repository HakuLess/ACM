package atcoder.abc235

fun main(args: Array<String>) {
    val (n, q) = readLine()!!.trim().split(' ').map { it.toInt() }
    val arr = readLine()!!.trim().split(' ').map { it.toInt() }
    val map = HashMap<Int, ArrayList<Int>>()
    for (i in arr.indices) {
        map[arr[i]] = map.getOrDefault(arr[i], arrayListOf())
        map[arr[i]]!!.add(i)
    }
    repeat(q) {
        val (x, k) = readLine()!!.trim().split(' ').map { it.toInt() }
        if (x in map.keys && k - 1 in map[x]!!.indices) {
            println(map[x]!![k - 1] + 1)
        } else {
            println(-1)
        }
    }
}