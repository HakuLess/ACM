package codeforces.kotlinhero.practice9

import utils.print

// 拆分严格递增、递减数组
fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }
    val ans = IntArray(n)
    var increase = Int.MIN_VALUE
    var decrease = Int.MAX_VALUE
    for (i in arr.indices) {
        if (arr[i] < increase && arr[i] < decrease) {
            decrease = arr[i]
            ans[i] = 1
            continue
        } else if (arr[i] > increase && arr[i] > decrease) {
            increase = arr[i]
            continue
        } else if (arr[i] in decrease..increase) {
            println("NO")
            return
        }
        if (i != arr.lastIndex) {
            if (arr[i + 1] > arr[i]) {
                if (increase > arr[i]) {
                    println("NO")
                    return
                }
                increase = arr[i]
            } else if (arr[i + 1] == arr[i]) {
                if (increase < arr[i]) {
                    increase = arr[i]
                } else if (decrease > arr[i]) {
                    decrease = arr[i]
                    ans[i] = 1
                } else {
                    println("NO")
                    return
                }
            } else {
                decrease = arr[i]
                ans[i] = 1
            }
        }
    }
    println("YES")
    println(ans.joinToString(" "))
}