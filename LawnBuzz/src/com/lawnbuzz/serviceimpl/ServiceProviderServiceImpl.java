package com.lawnbuzz.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawnbuzz.mappers.ServiceProviderMapper;
import com.lawnbuzz.models.GeoLocation;
import com.lawnbuzz.models.ServiceProvider;
import com.lawnbuzz.service.ServiceProviderService;

@Service("serviceProviderService")
public class ServiceProviderServiceImpl implements ServiceProviderService{
	@Autowired
	private ServiceProviderMapper mapper;
	@Override
	public List<ServiceProvider> getServiceProviders() {
		// TODO Auto-generated method stub
		return mapper.getAllServiceProviders();
	}
	@Override
	public void registerServiceProvider(ServiceProvider sp) {
		//CREATE SERVICE PROVIDER ENTRY FOR INFO AND GEO/SERVICE DATA
		mapper.registerServiceProvider(sp);
		//UPDATE THE POINTERS TO GEOLOC AND SERVICE
		mapper.finalizeServiceProviderRegistration(sp.getId());
		for(com.lawnbuzz.models.Service s:sp.getServices()){
			registerServiceProviderService(sp.getId(),s);
		}
		
		registerServiceProviderGeoLoc(sp.getId(),sp.getLoc());
		
	}
	@Override
	public void registerServiceProviderService(int id,
			com.lawnbuzz.models.Service service) {
		mapper.registerServiceProviderService(id, service);
		
	}
	@Override
	public void registerServiceProviderGeoLoc(int id,GeoLocation loc) {
		mapper.registerServiceProviderGeoLoc(id, loc);
		
	}

}
