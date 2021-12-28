package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

public class DogCartQtyDownService 
{
	public void downCartQty(String kind, HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		for(int i = 0; i < cartList.size(); i++)
		{
			if(cartList.get(i).getKind().equals(kind))
			{
				// 수량을 증가시킬 대상 장바구니 항목 객체를 kind값으로
				// 비교하여 검색한 후 해당 객체의 수량을 감소시킴.
				cartList.get(i).setQty(cartList.get(i).getQty()-1);
			}
		}
		
	}
}
