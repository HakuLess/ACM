package atcoder.abc235

fun main(args: Array<String>) {
    val (a, b, c) = readLine()!!.trim().toCharArray()
    val result = "$a$b$c".toInt() + "$b$c$a".toInt() + "$c$a$b".toInt()
    println(result)
}