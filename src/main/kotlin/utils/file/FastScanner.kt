package utils.file

import java.io.BufferedInputStream

class FastScanner {
    private val input = BufferedInputStream(System.`in`)
    private val buffer = ByteArray(1 shl 16)
    private var len = 0
    private var ptr = 0

    private fun readByte(): Int {
        if (ptr >= len) {
            len = input.read(buffer)
            ptr = 0
            if (len <= 0) return -1
        }
        return buffer[ptr++].toInt()
    }

    fun nextLong(): Long {
        var c = readByte()
        while (c <= 32 && c >= 0) c = readByte()
        var sign = 1
        if (c == '-'.code) {
            sign = -1
            c = readByte()
        }
        var res = 0L
        while (c > 32) {
            res = res * 10 + (c - '0'.code)
            c = readByte()
        }
        return res * sign
    }

    fun nextInt(): Int = nextLong().toInt()

    fun nextString(): String {
        var c = readByte()
        while (c <= 32 && c >= 0) c = readByte()
        val sb = StringBuilder()
        while (c > 32) {
            sb.append(c.toChar())
            c = readByte()
        }
        return sb.toString()
    }
}