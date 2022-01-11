package codeforces.round764

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    val ans = ArrayList<Triple<Int, Int, Int>>()
    var meet = false
    fun dfs(target: String, start: Int, arr: ArrayList<String>) {
        if (meet) return
        for (i in target.length downTo start + 2) {
            for (j in arr.indices) {
                val index = arr[j].indexOf(target.substring(start, i))
                if (index != -1) {
                    ans.add(Triple(index + 1, i - start + index, j + 1))
                    if (i == target.length) {
                        meet = true
                        return
                    }
                    dfs(target, i, arr)
                    if (meet) return
                    ans.removeAt(ans.lastIndex)
                }
            }
        }
    }

    repeat(t) {
        readLine()
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val arr = ArrayList<String>()
        repeat(n) {
            val num = readLine()!!
            arr.add(num)
        }
        val target = readLine()!!
        meet = false
        ans.clear()
        dfs(target, 0, arr)
        if (meet) {
            println(ans.size)
            ans.forEach {
                println("${it.first} ${it.second} ${it.third}")
            }
        } else {
            println(-1)
        }
    }
}