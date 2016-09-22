package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import couponSystemSingelton.CouponSystem;
import facade.CouponClientFacade;
import facade.CustomerFacade;
import web.beans.LogInBean;
import web.beans.Message;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerService {

	private Coupon coupon = new Coupon();

	private CustomerFacade getCustomerFacade(HttpServletRequest req) {
		CustomerFacade facade = ((CustomerFacade) req.getSession().getAttribute("customerFacade"));
		return facade;
	}

	@Path("/login")
	@POST
	public Message customerLogin(LogInBean lgb, @Context HttpServletRequest req) throws CouponSystemException, ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		CouponClientFacade facade = CouponSystem.getInstance().login(lgb.getLogin(), lgb.getPassword(), "Customer");
		HttpSession session = req.getSession(true);
		session.setAttribute("customerFacade", facade);
		return new Message("logged in successfully as: " + lgb.getLogin());
	}

	@Path("/logout")
	@POST
	public Message adminLogout(@Context HttpServletRequest req) throws CouponSystemException {
		HttpSession session = req.getSession(false);
		session.setAttribute("customerFacade", null);
		return new Message("logged out successfully");
	}

	@Path("/purchaseCoupon/{id}")
	@POST
	public Message purchaseCoupon(@PathParam("id") long id, @Context HttpServletRequest req) throws CouponSystemException {
		coupon.setId(id);
		getCustomerFacade(req).purchaseCoupon(coupon);
		return new Message("successfully acquired coupon #" + id);
	}

	@Path("/getCoupons")
	@GET
	public Coupon[] getAllPurchasedCoupons(@Context HttpServletRequest req) throws CouponSystemException {
		return getCustomerFacade(req).getAllPurchasedCoupons().toArray(new Coupon[0]);
	}

	@Path("/getCouponsByType/{type}")
	@GET
	public Coupon[] getAllPurchasedCouponsByType(@PathParam("type") CouponType type, @Context HttpServletRequest req) throws CouponSystemException {
		return getCustomerFacade(req).getAllPurchasedCouponsByType(type).toArray(new Coupon[0]);
	}

	@Path("/getCouponsByPrice/{price}")
	@GET
	public Coupon[] getAllPurchasedCouponsByPrice(@PathParam("price") double price, @Context HttpServletRequest req) throws CouponSystemException {
		return getCustomerFacade(req).getAllPurchasedCouponsByPrice(price).toArray(new Coupon[0]);
	}
}