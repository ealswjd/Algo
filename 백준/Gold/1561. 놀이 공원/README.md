# [Gold I] 놀이 공원 - 1561 

[문제 링크](https://www.acmicpc.net/problem/1561) 

### 성능 요약

메모리: 13368 KB, 시간: 120 ms

### 분류

이분 탐색, 매개 변수 탐색

### 제출 일자

2025년 7월 15일 00:01:34

### 문제 설명

<p>N명의 아이들이 한 줄로 줄을 서서 놀이공원에서 1인승 놀이기구를 기다리고 있다. 이 놀이공원에는 총 M종류의 1인승 놀이기구가 있으며, 1번부터 M번까지 번호가 매겨져 있다.</p>

<p>모든 놀이기구는 각각 운행 시간이 정해져 있어서, 운행 시간이 지나면 탑승하고 있던 아이는 내리게 된다. 놀이 기구가 비어 있으면 현재 줄에서 가장 앞에 서 있는 아이가 빈 놀이기구에 탑승한다. 만일 여러 개의 놀이기구가 동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승한다고 한다.</p>

<p>놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때, 줄의 마지막 아이가 타게 되는 놀이기구의 번호를 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 N(1 ≤ N ≤ 2,000,000,000)과 M(1 ≤ M ≤ 10,000)이 빈칸을 사이에 두고 주어진다. 둘째 줄에는 각 놀이기구의 운행 시간을 나타내는 M개의 자연수가 순서대로 주어진다. 운행 시간은 1 이상 30 이하의 자연수이며, 단위는 분이다.</p>

### 출력 

 <p>첫째 줄에 마지막 아이가 타게 되는 놀이기구의 번호를 출력한다.</p>

