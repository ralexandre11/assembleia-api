package com.ribeiro.assembleiaapi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ribeiro.assembleiaapi.model.dto.CpfApiDTO;
import com.ribeiro.assembleiaapi.service.CpfApiService;

@Service
public class CpfApiServiceImpl implements CpfApiService{

	@Override
	public CpfApiDTO checkCpfApi(Long cpf) {
		//TODO handle cpf that start with 0
		final String url = "https://user-info.herokuapp.com/users/{cpf}";
		
		//TODO handle not found
		RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, CpfApiDTO.class, cpf);
	}
	
}
