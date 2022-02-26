package atcoder.abc241

import utils.biFirstIndexOf
import utils.biLastIndexOf

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val list = ArrayList<Long>()
    repeat(n) {
        val arr = readLine()!!.split(" ").map { it.toLong() }
        when (arr[0]) {
            1L -> {
                val index = list.binarySearch(arr[1])
                if (index < 0) {
                    list.add(-index - 1, arr[1])
                } else {
                    list.add(index, arr[1])
                }
            }
            2L -> {
                val index = list.biLastIndexOf { it <= arr[1] }
                if (index - arr[2] + 1 in arr.indices) {
                    println(list[index - arr[2].toInt() + 1])
                } else {
                    println(-1)
                }
            }
            3L -> {
                val index = list.biFirstIndexOf { it >= arr[1] }
                if (index + arr[2] - 1 in list.indices) {
                    println(list[index + arr[2].toInt() - 1])
                } else {
                    println(-1)
                }
            }
        }
    }
}