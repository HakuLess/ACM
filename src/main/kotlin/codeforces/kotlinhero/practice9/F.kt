package codeforces.kotlinhero.practice9

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }.sorted()
    val seen = HashSet<Int>()
    for (i in arr.indices) {
        if (arr[i] - 1 !in seen && arr[i] - 1 > 0) {
            seen.add(arr[i] - 1)
        } else if (arr[i] !in seen) {
            seen.add(arr[i])
        } else {
            seen.add(arr[i] + 1)
        }
    }
    println(seen.size)
}