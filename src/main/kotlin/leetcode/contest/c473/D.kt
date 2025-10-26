package leetcode.contest.c473

import utils.gcd
import utils.print

fun main() {
    val s = SolutionD()
    // 3
    s.numGoodSubarrays(intArrayOf(1, 2, 3), 3).print()
    // 2
    s.numGoodSubarrays(intArrayOf(2, 2, 2, 2, 2, 2), 6).print()
}

class SolutionD {

    fun numGoodSubarrays(nums: IntArray, k: Int): Long {
        val n = nums.size
        val runs = mutableListOf<Pair<Long, Int>>()
        var i = 0
        while (i < n) {
            var j = i
            while (j < n && nums[j] == nums[i]) j++
            val count = j - i
            runs.add(Pair(nums[i].toLong(), count))
            i = j
        }

        val m = runs.size
        val F = LongArray(m + 1)
        for (idx in 0 until m) {
            val (v, c) = runs[idx]
            F[idx + 1] = F[idx] + v * c
        }

        var countType1 = 0L
        for ((v, c) in runs) {
            val g = gcd(v, k.toLong())
            val d = if (g == 0L) 1L else k.toLong() / g
            countType1 += (c / d).toLong()
        }

        var countType2 = 0L
        val M = HashMap<Long, Long>()
        for (j in 0 until m) {
            val (v_j, c_j) = runs[j]
            for (y in 1..c_j) {
                val R = ((-F[j] - y * v_j) % k + k) % k
                countType2 += M.getOrDefault(R, 0L)
            }
            for (x in 1..c_j) {
                val L = ((x * v_j - F[j + 1]) % k + k) % k
                M[L] = M.getOrDefault(L, 0L) + 1
            }
        }

        return countType1 + countType2
    }

//    fun numGoodSubarrays(nums: IntArray, k: Int): Long {
//        val n = nums.size
//        if (n == 0) return 0L
//
//        val vals = ArrayList<Long>()
//        val cnts = ArrayList<Int>()
//        var i = 0
//        while (i < n) {
//            val v = nums[i].toLong()
//            var j = i
//            while (j < n && nums[j] == nums[i]) {
//                j++
//            }
//            vals.add(v)
//            cnts.add(j - i)
//            i = j
//        }
//
//        var ans = 0L
//        val kLong = k.toLong()
//
//        for (t in vals.indices) {
//            val v = vals[t]
//            val c = cnts[t]
//            val g = gcd(v, kLong)
//            val step = if (g == 0L) 1L else (kLong / g)
//            ans += (c / step).toLong()
//        }
//
//        val m = vals.size
//        val prefixMod = LongArray(m + 1)
//        prefixMod[0] = 0L
//        for (idx in 0 until m) {
//            prefixMod[idx + 1] = (prefixMod[idx] + (vals[idx] % kLong + kLong) % kLong) % kLong
//        }
//
//        val cntMap = HashMap<Long, Long>()
//        var crossCount = 0L
//        for (p in prefixMod) {
//            val cur = cntMap.getOrDefault(p, 0L)
//            crossCount += cur
//            cntMap[p] = cur + 1L
//        }
//
//        var singleValsDivK = 0L
//        for (v in vals) if (((v % kLong) + kLong) % kLong == 0L) singleValsDivK++
//
//        val crossOnlyMultiValue = crossCount - singleValsDivK
//
//        return ans + crossOnlyMultiValue
//    }
}