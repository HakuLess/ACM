package leetcode.contest.c278

import utils.TypedUFS
import utils.print

fun main() {
    val s = SolutionD()
//    s.groupStrings(arrayOf("a", "b", "ab", "cde")).print()
    s.groupStrings(arrayOf("qamp", "am", "khdrn")).print()
//    s.groupStrings(arrayOf("web", "a", "te", "hsx", "v", "k", "a", "roh")).print()
//    s.groupStrings(arrayOf("b", "q")).print()
}

class SolutionD {
    fun groupStrings(words: Array<String>): IntArray {
        val map = HashMap<Int, Int>()
        words.forEach {
            var c = 0
            for (i in it.indices) {
                c += 1 shl (it[i] - 'a')
            }
            map[c] = map.getOrDefault(c, 0) + 1
        }

        val ufs = TypedUFS<Int>(words.size)
        map.keys.forEach {
            ufs.union(it, it)
        }

        fun union(i: Int, j: Int) {
            if (i == j) return
            if (map.getOrDefault(j, 0) == 0) return
            ufs.union(i, j)
        }

        // mask 第i位变化 第j位变化
        for (mask in map.keys) {
            for (i in 0 until 26) {
                // 令第i位变化
                union(mask, mask xor (1 shl i))
                for (j in 0 until 26) {
                    // 注意 i位j位的变化，不可以同时为增加 or 减少
                    if (mask shr i and 1 != mask shr j and 1) {
                        union(mask, (mask xor (1 shl i)) xor (1 shl j))
                    }
                }
            }
        }
        val seen = HashSet<Int>()
        var c = 0
        val maxArr = IntArray(map.keys.size)
        for (i in map.keys) {
            val find = ufs.typedFind(i)
            if (find !in seen) {
                seen.add(ufs.typedFind(i))
                c++
            }
            maxArr[find] += map[i]!!
        }
        return intArrayOf(c, maxArr.maxOrNull()!!)
//        return intArrayOf(c, maxArr.max()!!)
    }
}