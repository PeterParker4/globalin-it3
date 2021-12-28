package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService 
{
	// 파라미터값으로 전송된 id값을 가지고 상품 정보를 얻어오는 메소드 구현.
	
	public Dog getCartDog(int id)
	{
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		Dog dog = dogDAO.selectDog(id);
		close(con);
		return dog;
	}
	
	public void addCart(HttpServletRequest request, Dog cartDog)
	{
		HttpSession session = request.getSession();
		
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList ==null)
		{
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		
		boolean isNewCart = true;
		// 현재 장바구니에 담는 항목이 새로 추가되는 항목인지 저장하는 변수임.
		
		for(int i = 0; i < cartList.size(); i++)
		{
			if(cartDog.getKind().equals(cartList.get(i).getKind()))
			{
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
			
		}
		
		// 존재하지 않으면 장바구니에 항목을 저장함.
		if(isNewCart)
		{
			Cart cart = new Cart();
			
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}
		
		
		
		
		
	}
	
	
}
