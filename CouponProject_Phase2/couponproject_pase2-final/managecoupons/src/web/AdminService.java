package web;

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

import beans.Company;
import beans.CouponSystemException;
import beans.Customer;
import couponSystemSingelton.CouponSystem;
import facade.AdminFacade;
import facade.CouponClientFacade;
import web.beans.LogInBean;
import web.beans.Message;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {

	private Customer customer;
	private Company company;

	private AdminFacade getAdminFacade(HttpServletRequest req) {
		AdminFacade facade = ((AdminFacade) req.getSession().getAttribute("adminFacade"));
		return facade;
	}

	@Path("/login")
	@POST
	public Message adminLogin(LogInBean lgb, @Context HttpServletRequest req)
			throws CouponSystemException, ClassNotFoundException {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		CouponClientFacade facade = CouponSystem.getInstance().login(lgb.getLogin(), lgb.getPassword(), "Admin");
		HttpSession session = req.getSession(true);
		session.setAttribute("adminFacade", facade);
		return new Message("logged in successfully as admin");
	}

	// @POST
	// @GET
	@Path("/logout")
	@POST
	public Message adminLogout(@Context HttpServletRequest req) throws CouponSystemException {
		HttpSession session = req.getSession(false);
		session.setAttribute("adminFacade", null);
		return new Message("logged out successfully as admin");
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// COMPANY
	///////////////////////////////////////////////////////////////////////////

	@Path("/createCompany")
	@POST
	public Message createCompany(Company cb, @Context HttpServletRequest req) throws CouponSystemException {
		getAdminFacade(req).createCompany(cb);
		return new Message("created a new company: " + cb.getCompName());
	}

	// NEED to handler for right messaging from facade
	@Path("/removeCompany/{id}")
	@DELETE
	public Message removeCompany(@PathParam("id") long id, @Context HttpServletRequest req)
			throws CouponSystemException {
		this.company = getAdminFacade(req).getCompany(id);
		getAdminFacade(req).removeCompany(this.company);
		return new Message("company: " + this.company.getCompName() + " was successfully removed");
	}

	// NEED to handler for right messaging from facade
	@Path("/updateCompany")
	@PUT
	public Message updateCompany(Company company, @Context HttpServletRequest req) throws CouponSystemException {
		getAdminFacade(req).updateCompany(company);
		return new Message("company: " + company.getCompName() + " was successfilly updated");
	}

	@Path("/getCompany/{id}")
	@GET
	public Company getCompany(@PathParam("id") long id, @Context HttpServletRequest req) throws CouponSystemException {
		return getAdminFacade(req).getCompany(id);
	}

	@Path("/getAllCompanies")
	@GET
	public Company[] getAllCompanies(@Context HttpServletRequest req) throws CouponSystemException {
		return getAdminFacade(req).getAllCompanies().toArray(new Company[0]);
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// CUSTOMER
	///////////////////////////////////////////////////////////////////////////

	@Path("/createCustomer")
	@POST
	public Message createCustomer(Customer customer, @Context HttpServletRequest req) throws CouponSystemException {
		getAdminFacade(req).createCustomer(customer);
		return new Message("The customer " + customer.getCustName() + " created successfully.");
	}

	@Path("/removeCustomer/{id}")
	@DELETE
	public Message removeCustomer(@PathParam("id") long id, @Context HttpServletRequest req)
			throws CouponSystemException {
		this.customer = getAdminFacade(req).getCustomer(id);
		getAdminFacade(req).removeCustomer(this.customer);
		return new Message("The customer " + this.customer.getCustName() + " deleted successfully.");
	}

	@Path("/updateCustomer")
	@PUT
	public Message updateCustomer(Customer customer, @Context HttpServletRequest req) throws CouponSystemException {
		getAdminFacade(req).updateCustomer(customer);
		return new Message("The customer " + customer.getCustName() + " updated successfully.");
	}

	@Path("/getCustomer/{id}")
	@GET
	public Customer getCustomer(@PathParam("id") long id, @Context HttpServletRequest req)
			throws CouponSystemException {
		return getAdminFacade(req).getCustomer(id);
	}

	@Path("/getAllCustomers")
	@GET
	public Customer[] getAllCustomers(@Context HttpServletRequest req) throws CouponSystemException {
		return getAdminFacade(req).getAllCustomers().toArray(new Customer[0]);
	}
}