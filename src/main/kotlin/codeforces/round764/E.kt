package codeforces.round764

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val arr = ArrayList<String>()
        repeat(n) {
            val num = readLine()!!
            arr.add(num)
        }
        val map = HashMap<String, Triple<Int, Int, Int>>()
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                for (k in j + 2..minOf(j + 3, arr[i].length)) {
                    map[arr[i].substring(j, k)] = Triple(j + 1, k, i + 1)
                }
            }
        }
        val target = readLine()!!
        val dp = IntArray(target.length + 1) { -1 }
        dp[0] = 0
        for (i in target.indices) {
            if (i + 2 <= target.length && target.substring(i, i + 2) in map.keys) {
                if (dp[i] != -1) {
                    dp[i + 2] = i
                }
            }
            if (i + 3 <= target.length && target.substring(i, i + 3) in map.keys) {
                if (dp[i] != -1) {
                    dp[i + 3] = i
                }
            }
        }

        if (dp.last() == -1) {
            println(-1)
        } else {
            val ans = ArrayList<Triple<Int, Int, Int>>()
            var left = dp.last()
            var right = target.length
            while (right != 0) {
                ans.add(map[target.substring(left, right)]!!)
                right = left
                left = dp[left]
            }
            println(ans.size)
            // 这里要注意... 输出多行要拼装一起输出，println放在for循环里会超时...
            val output = StringBuilder()
            ans.reversed().forEach {
                output.appendLine("${it.first} ${it.second} ${it.third}")
            }
            print(output)
        }
    }
}