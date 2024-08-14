package com.Hritik.JobApplication.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Long id, Company company);
    void createCompany(Company company);
    boolean deleteJobById(long id);
    Company getCompanyById(long id);
}
