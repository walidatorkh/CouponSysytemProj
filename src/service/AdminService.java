package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.dao.Company;
import f.Facade.AdminFacade;

@Path("admin")
public class AdminService {
	AdminFacade adminFacade= new AdminFacade();
	@PUT
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCompany(Company company) throws Throwable {
		adminFacade.createCompany(company);
	}
	
	@GET
	@Path("get/{company_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Company readCompany(@PathParam("id") long companyId) throws Throwable {
		Company company = adminFacade.getCompany(companyId);
		return company;
	}
}
