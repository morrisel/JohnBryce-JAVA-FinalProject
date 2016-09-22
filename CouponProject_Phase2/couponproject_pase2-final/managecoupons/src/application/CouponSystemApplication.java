package application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import exceptionHandler.ExceptionHandler;
import filter.SecurityFilter;
import web.AdminService;
import web.CompanyService;
import web.CustomerService;


@ApplicationPath("/couponsys")
public class CouponSystemApplication extends Application {
	@Override
	public Set<Object> getSingletons() {
		HashSet<Object> set = new HashSet<>();
		set.add(new AdminService());
		set.add(new CustomerService());
		set.add(new CompanyService());
		set.add(new ExceptionHandler());
		set.add(new SecurityFilter());
		return set;
	}
}