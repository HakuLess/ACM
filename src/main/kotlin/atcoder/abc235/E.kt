package atcoder.abc235

import utils.*

fun main(args: Array<String>) {
    val (n, m, q) = readLine()!!.trim().split(' ').map { it.toInt() }
    val source = ArrayList<IntArray>()
    repeat(m) {
        val (a, b, c) = readLine()!!.trim().split(' ').map { it.toInt() }
        source.add(intArrayOf(a - 1, b - 1, c))
    }
    val ufs = UFS(n)
    val mapA = HashMap<Int, Int>()
    val mapB = HashMap<Int, Int>()
    source.sortedBy { it[2] }.forEach {
        if (ufs.union(it[0], it[1])) {
            mapA[it[0]] = it[2]
            mapB[it[1]] = it[2]
        }
    }

    repeat(q) {
        val (u, v, w) = readLine()!!.trim().split(' ').map { it.toInt() }
        println("$u $v $w")
        var find = false
        if (u - 1 in mapA.keys && mapA[u - 1]!! > w) {
            find = true
            println("meet 0")
        }
        if (u - 1 in mapB.keys && mapB[u - 1]!! > w) {
            find = true
            println("meet 1")
        }
        if (v - 1 in mapA.keys && mapA[v - 1]!! > w) {
            find = true
            println("meet 2")
        }
        if (v - 1 in mapB.keys && mapB[v - 1]!! > w) {
            find = true
            println("meet 3")
        }
        if (!find)
            println("No")
        else
            println("Yes")
    }
}