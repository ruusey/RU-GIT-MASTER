package com.lawnbuzz.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lawnbuzz.models.GeoLocation;
import com.lawnbuzz.models.ServiceProvider;

public interface JobMapper {
	
	@Select("SELECT * FROM lb.job")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "service", column = "service"),
			@Result(property = "shortDescription", column = "shortdescription"),
			@Result(property = "longDescription", column = "longdescription"),
			@Result(property = "pay", column = "pay"),
			@Result(property = "complete", column = "complete"),
			@Result(property = "loc", javaType = GeoLocation.class, column = "geoloc_id", many = @Many(select = "getGeoLoc")) })
	public List<ServiceProvider> getAllServiceProviders();
	
	@Insert("INSERT INTO lb.service_provider (email, username, firstname, lastname,service_id, geoloc_id, rating) VALUES"
			+ "(#{email},#{userName}, #{firstName}, #{lastName}, #{id}, #{id}, 0);")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void registerServiceProvider(ServiceProvider sp);
}
