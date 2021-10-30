package leetcode.contest.b64

import utils.print
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionD()
//    s.countCombinations(arrayOf("rook"), "[[1,1]]".toGrid()).print()
    s.countCombinations(arrayOf("queen", "bishop"), "[[5,7],[3,4]]".toGrid()).print()
}

class SolutionD {
    fun countCombinations(pieces: Array<String>, positions: Array<IntArray>): Int {

        fun check(blacks: ArrayList<Int>): Boolean {
            println(blacks.joinToString())
            val ori = ArrayList<Pair<Int, Int>>()
            for (i in blacks.indices) {
                if ((blacks[i] % 10 - positions[i][0]) == 0 && (blacks[i] / 10 - positions[i][1]) == 0) {
                    ori.add(Pair(0, 0))
                } else if ((blacks[i] % 10 - positions[i][0]) == 0) {
                    ori.add(
                        Pair(
                            0,
                            (blacks[i] / 10 - positions[i][1]) / abs((blacks[i] / 10 - positions[i][1]))
                        )
                    )
                } else if ((blacks[i] / 10 - positions[i][1]) == 0) {
                    ori.add(
                        Pair(
                            (blacks[i] % 10 - positions[i][0]) / abs((blacks[i] % 10 - positions[i][0])),
                            0
                        )
                    )
                } else ori.add(
                    Pair(
                        (blacks[i] % 10 - positions[i][0]) / abs((blacks[i] % 10 - positions[i][0])),
                        (blacks[i] / 10 - positions[i][1]) / abs((blacks[i] / 10 - positions[i][1]))
                    )
                )
            }
            val p = arrayListOf<IntArray>()
            positions.forEach {
                p.add(it.clone())
            }
            while ((0..p.lastIndex).any { p[it][0] + p[it][1] * 10 != blacks[it] }) {
                val set = HashSet<Int>()
                for (i in blacks.indices) {
                    if (p[i][0] + p[i][1] * 10 != blacks[i]) {
                        p[i][0] += ori[i].first
                        p[i][1] += ori[i].second
                    }
                    set.add(p[i][0] + p[i][1] * 10)
                }
                if (set.size != pieces.size) return false
            }
            return true
        }

        fun dfs(index: Int, blacks: ArrayList<Int>): Int {
            if (index !in pieces.indices) return if (check(blacks)) 1 else 0
            val pos = positions[index]
            val type = pieces[index]

            var ans = 0
            for (i in 1..8) {
                for (j in 1..8) {
                    val key = i + j * 10
                    if (key in blacks) continue
                    when (type) {
                        "rook" -> {
                            if (i == pos[0] || j == pos[1]) {
                                blacks.add(key)
                                ans += dfs(index + 1, blacks)
                                blacks.remove(key)
                            }
                        }
                        "bishop" -> {
                            if (abs(i - pos[0]) == abs(j - pos[1])) {
                                blacks.add(key)
                                println("add key $key $i $j ${pos.joinToString()}")
                                ans += dfs(index + 1, blacks)
                                blacks.remove(key)
                            }
                        }
                        "queen" -> {
                            if (i == pos[0] || j == pos[0] || abs(i - pos[0]) == abs(j - pos[1])) {
                                blacks.add(key)
                                ans += dfs(index + 1, blacks)
                                blacks.remove(key)
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