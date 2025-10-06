// https://www.acmicpc.net/problem/14681

fun main() = with(System.`in`.bufferedReader()) {
    val x = readLine().toInt()
    val y = readLine().toInt()

    val result = when {
        x > 0 && y > 0 -> 1
        x < 0 && y > 0 -> 2
        x < 0 && y < 0 -> 3
        x > 0 && y < 0 -> 4
        else -> -1
    }

    print(result)
    close()
}
