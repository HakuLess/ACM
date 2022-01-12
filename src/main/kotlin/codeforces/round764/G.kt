package codeforces.round764

import utils.UFS

// 权值取或的最小生成树
fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        // n个点，m条边
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        var ans = 0
        val canSelect = BooleanArray(m) { true }
        val arr = ArrayList<IntArray>()
        repeat(m) {
            val tmp = readLine()!!.split(" ").map { it.toInt() }
            // 两个点及其权值
            arr.add(intArrayOf(tmp[0] - 1, tmp[1] - 1, tmp[2]))
        }
        // 从第29位一直判断到第0位，看是否高位是否可为0
        for (i in 29 downTo 0) {
            val ufs = UFS(n)
            // 假设结果中第i位为0
            for (j in arr.indices) {
                if (canSelect[j] && (arr[j][2] and (1 shl i) == 0)) {
                    ufs.union(arr[j][0], arr[j][1])
                }
            }
            // 是否所有点都联通，n个节点都是一组的
            if (ufs.sz[ufs.find(0)] == n) {
                // 这一位可为0，但一些边状态需要变成不可选
                for (k in arr.indices) {
                    if (arr[k][2] and (1 shl i) != 0) {
                        canSelect[k] = false
                    }
                }
            } else {
                // 这一位必须为1
                ans = ans or (1 shl i)
            }
        }
        println(ans)
    }
}