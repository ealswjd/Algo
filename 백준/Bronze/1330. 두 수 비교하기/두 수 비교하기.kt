// https://www.acmicpc.net/problem/1330

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = readLine().split(" ").map { it.toInt() }
    
    val result = if (a > b) {
        ">"
    } else if (a < b) {
        "<"
    } else {
        "=="
    }

    print(result)
    close()
}