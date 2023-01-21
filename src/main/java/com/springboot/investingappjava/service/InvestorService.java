package com.springboot.investingappjava.service;

import com.springboot.investingappjava.model.Investor;
import com.springboot.investingappjava.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {
    @Autowired
    private InvestorRepository investorRepository;

    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

    public void delete(Investor investor) {
        investorRepository.delete(investor);
    }

    public Investor findById(Long id) {
        return investorRepository.findById(id).orElse(null);
    }

    public List<Investor> findAll() {
        return investorRepository.findAll();
    }
}
