package com.lawnbuzz.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.lawnbuzz.models.GeoLocation;
import com.lawnbuzz.models.Service;
import com.lawnbuzz.models.ServiceProvider;

public interface ServiceProviderMapper {

	@Select("SELECT * FROM lb.service_provider")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "email", column = "email"),
			@Result(property = "userName", column = "username"),
			@Result(property = "firstName", column = "firstname"),
			@Result(property = "lastName", column = "lastname"),
			@Result(property = "rating", column = "rating"),
			@Result(property = "services", javaType = List.class, column = "service_id", many = @Many(select = "getServices")),
			@Result(property = "loc", javaType = GeoLocation.class, column = "geoloc_id", many = @Many(select = "getGeoLoc")) })
	public List<ServiceProvider> getAllServiceProviders();

	@Select("SELECT service"
			+ " FROM lb.service_provider_service WHERE service_id = #{service_id}")
	public List<Service> getServices(int serviceId);

	@Select("SELECT lng,lat,datetime"
			+ " FROM lb.service_provider_geoloc WHERE geoloc_id = #{geoloc_id}")
	public GeoLocation getGeoLoc(int geoLocId);

	@Insert("INSERT INTO lb.service_provider (email, username, firstname, lastname,service_id, geoloc_id, rating) VALUES"
			+ "(#{email},#{userName}, #{firstName}, #{lastName}, #{id}, #{id}, 0);")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void registerServiceProvider(ServiceProvider sp);
	
	@Update("UPDATE lb.service_provider SET service_id=#{id}, geoloc_id=#{id} WHERE id=#{id}")
	public void finalizeServiceProviderRegistration(@Param("id") int id);

	@Insert("INSERT INTO  lb.service_provider_service (service_id,service) VALUES "
			+ "(#{id},#{service})")
	public void registerServiceProviderService(@Param("id") int id, @Param("service")Service service);

	@Insert("INSERT INTO  lb.service_provider_geoloc (geoloc_id,lat,lng,datetime) VALUES "
			+ "(#{id},#{loc.lat},#{loc.lng},#{loc.dateTime})")
	public void registerServiceProviderGeoLoc(@Param("id") int id, @Param("loc") GeoLocation loc);

}