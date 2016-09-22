package web;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Coupon;
import beans.CouponSystemException;
import beans.CouponType;
import couponSystemSingelton.CouponSystem;
import facade.CompanyFacade;
import facade.CouponClientFacade;
import web.beans.LogInBean;
import web.beans.Message;

@Path("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyService {

	private Coupon coupon = new Coupon();

	private CompanyFacade getCompanyFacade(HttpServletRequest req) {
		CompanyFacade facade = ((CompanyFacade) req.getSession().getAttribute("companyFacade"));
		return facade;
	}

	@Path("/login")
	@POST
	public Message companyLogin(LogInBean lgb, @Context HttpServletRequest req) throws CouponSystemException, ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		CouponClientFacade facade = CouponSystem.getInstance().login(lgb.getLogin(), lgb.getPassword(), "Company");
		HttpSession session = req.getSession(true);
		session.setAttribute("companyFacade", facade);
		return new Message("logged in successfully as: " + lgb.getLogin());
	}

	@Path("/logout")
	@POST
	public Message adminLogout(@Context HttpServletRequest req) throws CouponSystemException {
		HttpSession session = req.getSession(false);
		session.setAttribute("companyFacade", null);
		return new Message("logged out successfully");
	}

	@Path("/createCoupon")
	@POST
	public Message createCoupon(Coupon coupon, @Context HttpServletRequest req) throws CouponSystemException {
		getCompanyFacade(req).createCoupon(coupon);
		return new Message("added new coupon: " + coupon.getTitle());
	}

	@Path("/removeCoupon/{id}")
	@DELETE
	public Message removeCoupon(@PathParam("id") long id, @Context HttpServletRequest req) throws CouponSystemException {
		coupon.setId(id);
		getCompanyFacade(req).removeCoupon(coupon);
		return new Message("coupon #" + id + "was deleted");
	}

	@Path("/updateCoupon")
	@PUT
	public Message updateCoupon(Coupon coupon, @Context HttpServletRequest req) throws CouponSystemException {
		getCompanyFacade(req).updateCoupon(coupon);
		return new Message("coupon with ID: " + coupon.getId() + " successfully updated");
	}

	@Path("/getAllCoupons")
	@GET
	public Coupon[] getAllCoupons(@Context HttpServletRequest req) throws CouponSystemException {
		return getCompanyFacade(req).getAllCoupons().toArray(new Coupon[0]);
	}

	@Path("/getCouponByType/{type}")
	@GET
	public Coupon[] getCouponByType(@PathParam("type") CouponType type, @Context HttpServletRequest req) throws CouponSystemException {
		return getCompanyFacade(req).getCouponByType(type).toArray(new Coupon[0]);
	}

	@Path("/getCouponUpToPrice/{price}")
	@GET
	public Coupon[] getCouponUpToPrice(@PathParam("price") double price, @Context HttpServletRequest req) throws CouponSystemException {
		return getCompanyFacade(req).getCouponUpToPrice(price).toArray(new Coupon[0]);
	}

	@Path("/getCouponUpToDate/{date}")
	@GET
	public Coupon[] getCouponUpToDate(@PathParam("date") Date date, @Context HttpServletRequest req) throws CouponSystemException {
		return getCompanyFacade(req).getCouponUpToDate(date).toArray(new Coupon[0]);
	}
}
