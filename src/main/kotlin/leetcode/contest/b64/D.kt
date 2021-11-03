package leetcode.contest.b64

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = Solution2056()
//    s.countCombinations(arrayOf("rook"), "[[1,1]]".toGrid()).print()
    s.countCombinations(arrayOf("queen", "bishop"), "[[5,7],[3,4]]".toGrid()).print()
}

class Solution2056 {
    fun countCombinations(pieces: Array<String>, positions: Array<IntArray>): Int {

        // 获取方向值
        fun getOri(start: Int, end: Int): Int {
            if (start == end) return 0
            if (start > end) return -1
            return 1
        }

        fun check(targets: ArrayList<Int>): Boolean {
            val ori = ArrayList<Int>()
            for (i in targets.indices) {
                ori.add((getOri(positions[i][0], targets[i] % 10) + getOri(positions[i][1], targets[i] / 10) * 10))
            }
            val pos = arrayListOf<Int>()
            positions.forEach {
                pos.add(it[0] + it[1] * 10)
            }
            repeat(8) {
                if (pos.size != pos.toHashSet().size) return false
                for (i in pos.indices) {
                    if (pos[i] != targets[i]) pos[i] += ori[i]
                }
            }
            if (pos.size != pos.toHashSet().size) return false
            return true
        }

        fun dfs(index: Int, targets: ArrayList<Int>): Int {
            if (index !in pieces.indices) return if (check(targets)) 1 else 0
            val pos = positions[index]
            val type = pieces[index]

            var ans = 0
            for (i in 1..8) {
                for (j in 1..8) {
                    val key = i + j * 10
                    if (key in targets) continue
                    when (type) {
                        "rook" -> {
                            if (i == pos[0] || j == pos[1]) {
                                targets.add(key)
                                ans += dfs(index + 1, targets)
                                targets.remove(key)
                            }
                        }
                        "bishop" -> {
                            if (abs(i - pos[0]) == abs(j - pos[1])) {
                                targets.add(key)
                                ans += dfs(index + 1, targets)
                                targets.remove(key)
                            }
                        }
                        "queen" -> {
                            if (i == pos[0] || j == pos[1] || abs(i - pos[0]) == abs(j - pos[1])) {
                                targets.add(key)
                                ans += dfs(index + 1, targets)
                                targets.remove(key)
                            }
                        }
                    }
                }
            }
            return ans
        }

        return dfs(0, arrayListOf())
    }
}