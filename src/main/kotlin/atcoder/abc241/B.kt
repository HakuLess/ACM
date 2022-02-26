package atcoder.abc241

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.trim().split(" ").map { it.toInt() }
    val nList = readLine()!!.trim().split(" ").map { it.toInt() }
    val mList = readLine()!!.trim().split(" ").map { it.toInt() }

    val map = HashMap<Int, Int>()
    nList.forEach {
        map[it] = map.getOrDefault(it, 0) + 1
    }
    mList.forEach {
        map[it] = map.getOrDefault(it, 0) - 1
        if (map[it]!! < 0) {
            println("No")
            return
        }
    }
    println("Yes")
}