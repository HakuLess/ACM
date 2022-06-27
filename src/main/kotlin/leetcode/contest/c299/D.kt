package leetcode.contest.c299

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.minimumScore(
        intArrayOf(1, 5, 5, 4, 11),
        "[[0,1],[1,2],[1,3],[3,4]]".toGrid()
    ).print()
}

// 树拆分为三个子树
// value为每棵树异或值的 最大值 与 最小值的差
// 求value的最小值
class SolutionD {
    fun minimumScore(nums: IntArray, edges: Array<IntArray>): Int {
        val n = nums.size
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in 0 until n) {
            map[i] = arrayListOf()
        }
        edges.forEach {
            val (x, y) = it
            map[x]!!.add(y)
            map[y]!!.add(x)
        }

        val v = IntArray(n)
        val ins = IntArray(n)
        val outs = IntArray(n)
        var clock = 0

        fun dfs(cur: Int, pre: Int) {
            clock++
            ins[cur] = clock
            v[cur] = nums[cur]
            map[cur]?.forEach {
                if (it != pre) {
                    dfs(it, cur)
                    v[cur] = v[cur] xor v[it]
                }
            }
            outs[cur] = clock
        }

        dfs(0, -1)
        var ans = Int.MAX_VALUE
        for (i in 2 until n) {
            for (j in 1 until i) {
                var x = 0
                var y = 0
                var z = 0
                if (ins[j] in ins[i]..outs[i]) {
                    // i是j的祖先
                    x = v[j]
                    y = v[i] xor v[j]
                    z = v[0] xor v[i]
                } else if (ins[i] in ins[j]..outs[j]) {
                    // j是i的祖先
                    x = v[i]
                    y = v[i] xor v[j]
                    z = v[0] xor v[j]
                } else {
                    // i、j不相交
                    x = v[i]
                    y = v[j]
                    z = v[0] xor v[i] xor v[j]
                }
                ans = minOf(ans, arrayOf(x, y, z).let { it.maxOrNull()!! - it.minOrNull()!! })
//                ans = minOf(ans, arrayOf(x, y, z).let { it.max()!! - it.min()!! })
            }
        }
        return ans
    }
}