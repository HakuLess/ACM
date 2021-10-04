package leetcode.normal

class Solution482 {
    fun licenseKeyFormatting(s: String, k: Int): String {
        val sb = StringBuilder()
        sb.append('-')
        s.toUpperCase().filter { it != '-' }.reversed().forEach {
            sb.append(it)
            if (sb.length % (k + 1) == 0) {
                sb.append('-')
            }
        }
        return sb.toString().reversed().trim('-')
    }
}