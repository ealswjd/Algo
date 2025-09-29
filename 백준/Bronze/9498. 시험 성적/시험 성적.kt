// https://www.acmicpc.net/problem/9498

fun main() = with(System.`in`.bufferedReader()) {
    val score = readLine().toInt() // 시험 점수

    val result = when (score) {
        in 90..100 -> 'A' // 90 ~ 100점은 A
        in 80..89 -> 'B' // 80 ~ 89점은 B
        in 70..79 -> 'C' // 70 ~ 79점은 C
        in 60..69 -> 'D' // 60 ~ 69점은 D
        else -> 'F' // 나머지 점수는 F를 출력
    }

    print(result)
    close()
}
