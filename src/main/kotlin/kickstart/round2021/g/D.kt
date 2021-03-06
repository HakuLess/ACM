package kickstart.round2021.g

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, a) = readLine()!!.trim().split(' ').map { it.toInt() }
        when (n) {
            3 -> {
                println("Case #${it + 1}: POSSIBLE")
                println("0 0")
                println("0 1")
                println("$a 0")
            }
            4 -> {
                if (a > 1) {
                    println("Case #${it + 1}: POSSIBLE")
                    println("1 ${a - 1}")
                    println("1 0")
                    println("0 0")
                    println("0 1")
                } else {
                    println("Case #${it + 1}: IMPOSSIBLE")
                }
            }
            5 -> {
                if (a > 2) {
                    println("Case #${it + 1}: POSSIBLE")
                    println("0 0")
                    println("1 0")
                    println("2 2")
                    println("1 1")
                    println("0 ${a - 2}")
                } else {
                    println("Case #${it + 1}: IMPOSSIBLE")
                }
            }
        }
    }
}
