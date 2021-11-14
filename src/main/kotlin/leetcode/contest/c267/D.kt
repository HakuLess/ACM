package leetcode.contest.c267

import utils.TypedUFS
import utils.UFS

class SolutionD {
    fun friendRequests(n: Int, restrictions: Array<IntArray>, requests: Array<IntArray>): BooleanArray {
        val ufs = UFS(n)
        return requests.map {
            ufs.union(it[0], it[1])
            restrictions.all {
                (ufs.find(it[0]) != ufs.find(it[1]))
            }.also {
                if (!it) {
                    // 回退至上一状态
                    ufs.reset()
                }
            }
        }.toBooleanArray()
    }
}