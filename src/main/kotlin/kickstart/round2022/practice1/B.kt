package kickstart.round2022.practice1

fun main() {
    val vowels = charArrayOf('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u')
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val s = readLine()!!.trim()
        val ans =
            if (s.last() == 'y' || s.last() == 'Y') "nobody"
            else if (s.last() in vowels) "Alice"
            else "Bob"
        println("Case #${it + 1}: $s is ruled by $ans.")
    }
}