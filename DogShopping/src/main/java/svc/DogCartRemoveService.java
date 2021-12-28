package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartRemoveService {

	public void cartRemove(HttpServletRequest request, String[] kindArray) {
		
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		/* 사용자가 삭제할 대상으로 선택한 항목의 kind 값들을 반복 처리함
		 * 
		 * 삭제할 항목의 kind별로 해당 kind값이 동일한 kind값을 가진 장바구니 항목을 찾아서 삭제함
		 */
		
		for(int i = 0; i < kindArray.length; i++) {
			for(int j = 0; j < cartList.size(); j++) {
				if(cartList.get(j).getKind().equals(kindArray[i])) {
					cartList.remove(cartList.get(j));
				}
			}
		}
		
	}
}
