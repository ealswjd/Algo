package programmers;

import java.util.HashSet;
/* https://programmers.co.kr/learn/courses/30/lessons/42577
 * Lv.2) 해시 > 전화번호 목록 */
public class PhoneBook {

	public boolean solution(String[] phone_book) {
        	HashSet<String> set = new HashSet<>(); // 전화번호 목록을 담을 set
        	for(String num : phone_book) { // 전화번호 목록 담아주기
        		set.add(num);
        	}
        
        	for(String num : phone_book) {
        		for(int i=1; i<num.length(); i++) { // 접두어인 번호가 있는 경우를 찾아냄.
        			if(set.contains(num.substring(0, i))) return false;
        		}
        	}
        	return true; // 없을 경우 true return
	}//solution
	
}//class
