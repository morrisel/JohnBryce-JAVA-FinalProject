package dao.interfaces;

import java.util.Collection;

import beans.Company;
import beans.Coupon;
import beans.CouponSystemException;

//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#createCompany(Company) createCompany}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#removeCompany(Company) removeCompany}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#updateCompany(Company) updateCompany}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#getCompany(Company) getCompany}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#getAllCompanies(Company) getAllCompanies}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO#getCoupons(Company) getCoupons}</a>,
//* <a href="{@docRoot}/dao/db/CompanyDBDAO.java">{@link #createCompany(Company)}</a>

/**
 * The {@code CompanyDAO} interface provides seven basics methods for DML functionality
 * and two additional methods.<br>
 * 
 * The {@code CompanyDAO} interface throws all exceptions to the throwable class
 * <a href="{@docRoot}/beans/CouponSystemException.html">{@link CouponSystemException}</a>.
 * 
 * <dl>
 * 		<dt>All Known Implementing Classes:</dt>
 * 		<dd><a href="{@docRoot}/dao/db/CompanyDBDAO.html">{@link dao.db.CompanyDBDAO}</a></dd>
 * </dl>
 * <hr>
 * @author Morris Elkanaev
 * @version 1.00, 28/07/2016
 */
public interface CompanyDAO {

	void createCompany(Company company) throws CouponSystemException;

	void removeCompany(Company company) throws CouponSystemException;

	void updateCompany(Company company) throws CouponSystemException;

	Company getCompany(long id) throws CouponSystemException;

	Collection<Company> getAllCompanies() throws CouponSystemException;

	Collection<Coupon> getCoupons(Company company) throws CouponSystemException;

	boolean login(String compName, String password) throws CouponSystemException;

	// additional methods
	Company getCompanyByName(String compName, String password) throws CouponSystemException;

	boolean isFindDuplicateName(Company company) throws CouponSystemException;

	public long getNextId(Company company) throws CouponSystemException;

	// public long getIdByCompanyName(Company company) throws CouponSystemException;
}