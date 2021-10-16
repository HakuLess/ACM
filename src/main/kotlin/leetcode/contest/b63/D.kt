package leetcode.contest.b63

import utils.print
import java.util.*

fun main() {
    val s = Solution5887()
//    s.kthSmallestProduct(intArrayOf(2, 5), intArrayOf(3, 4), 2).print()
//    s.kthSmallestProduct(intArrayOf(-4, -2, 0, 3), intArrayOf(2, 4), 6).print()
    s.kthSmallestProduct(intArrayOf(-2, -1, 0, 1, 2), intArrayOf(-3, -1, 2, 4, 5), 3).print()
}

// todo 二分法
class Solution5887 {
    fun kthSmallestProduct(nums1: IntArray, nums2: IntArray, k: Long): Long {
        // 正负分开
        val na = arrayListOf<Int>()
        val pa = arrayListOf<Int>()
        val nb = arrayListOf<Int>()
        val pb = arrayListOf<Int>()
        nums1.forEach {
            if (it < 0) na.add(it) else pa.add(it)
        }
        nums2.forEach {
            if (it < 0) nb.add(it) else pb.add(it)
        }

        println("负数 ${pa.size * nb.size + na.size * pb.size}")
        println("非负数 ${pa.size * pb.size + na.size * nb.size}")
        // 一共负数乘积 pa.size * nb.size + na.size * pb.size
        if (k > pa.size * nb.size + na.size * pb.size) {
            // 从正数里找 k - pa.size * nb.size + na.size * pb.size
            val cur = k - (pa.size * nb.size + na.size * pb.size)
            println("找正数 第${cur}小")

            var n = 1L
            // 找到第n个对角线
            while (n * (n + 1) < cur) {
                n++
            }

            val arr = arrayListOf<Long>()
            for (i in pa.indices) {
                val j = (n - i).toInt()
                if (j in pb.indices)
                    arr.add(pa[i].toLong() * pb[j])
            }
            for (i in na.indices) {
                val j = (n - i).toInt()
                if (j in nb.indices)
                    arr.add(na[i].toLong() * nb[j])
            }
            return arr.sorted()[(cur - (n * (n + 1))).toInt()]
        } else {
            // 找负数
            println("找负数 第${k}小")

            var n = 1L
            // 找到第n个对角线
            while (n * (n + 1) < k) {
                n++
            }

            println(n)

            val arr = arrayListOf<Long>()
            for (i in pa.indices) {
                val j = (n - i).toInt()
                if (nb.size - j in nb.indices)
                    arr.add(pa[i].toLong() * nb[nb.size - j])
            }
            for (i in na.indices) {
                val j = (n - i).toInt()
                if (pb.size - j in pb.indices)
                    arr.add(na[i].toLong() * pb[pb.size - j])
            }
            arr.sorted().joinToString().print()
            return arr.sorted()[(k - (n * (n - 1))).toInt()]
        }
    }
}