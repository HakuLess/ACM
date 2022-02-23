package codeforces.kotlinhero.practice9

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = ArrayList<Int>()
    arr.addAll(readLine()!!.trim().split(" ").map { it.toInt() }.sorted())
    val lst = ArrayList<Int>()
    for (i in arr.lastIndex - 1 downTo 0) {
        if (arr[i] == arr[i + 1]) {
            if (lst.isNotEmpty() && lst.last() == arr[i]) {
                println("No")
                return
            }
            lst.add(arr[i])
            arr.removeAt(i)
        }
    }
    println("Yes")
    println(arr.size)
    println(arr.joinToString(" "))
    println(lst.size)
    println(lst.joinToString(" "))
}