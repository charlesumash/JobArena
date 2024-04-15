package com.springtech.jobarena.company.Impl;

import com.springtech.jobarena.company.Company;
import com.springtech.jobarena.company.CompanyRepository;
import com.springtech.jobarena.company.CompanyService;
import com.springtech.jobarena.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Long companyId, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Boolean deleteCompany(Long companyId) {
        if (companyRepository.existsById(companyId)){
            companyRepository.deleteById(companyId);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
}
