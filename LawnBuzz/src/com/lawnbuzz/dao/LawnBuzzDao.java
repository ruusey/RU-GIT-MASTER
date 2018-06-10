package com.lawnbuzz.dao;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.fps.service.impl.FPMemberServiceImpl;
import com.lawnbuzz.serviceimpl.ClientServiceImpl;
import com.lawnbuzz.serviceimpl.JobServiceImpl;
import com.lawnbuzz.serviceimpl.ServiceProviderServiceImpl;
import com.lawnbuzz.util.GeoLocServices;
import com.lawnbuzz.util.Util;

@Component
public class LawnBuzzDao {
	public static final boolean DBG = true;
	static Logger LOGGER = Logger.getLogger(LawnBuzzDao.class.getName());
	public static ServiceProviderServiceImpl serviceProviderService;
	public static JobServiceImpl jobService;
	public static GeoLocServices geoService;
	public static ClientServiceImpl clientService;
	public static FPMemberServiceImpl fpUserService;

	static {
		long startTime = System.currentTimeMillis();
		ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:springConfig.xml");
		LOGGER.info(
				"Initialized LawnBuzzDao class " + cxt.getApplicationName() + " in " + Util.getTimeSince(startTime));
		if(DBG)LOGGER.warn("DEBUG Mode Enabled... Testing FPS Services");
		LOGGER.info("Registering LawnBuzz services...");

		// *********************************
		// REGISTER SERVICEPROVIDER SERVICES
		// *********************************
		if (!DBG) {
			startTime = System.currentTimeMillis();
			serviceProviderService = (ServiceProviderServiceImpl) cxt.getBean("serviceProviderService");
			LOGGER.info("Successfully registered serviceProviderService in " + Util.getTimeSince(startTime));
		}

		// *********************************
		// REGISTER JOBSERVICES
		// *********************************
		if (!DBG) {
			startTime = System.currentTimeMillis();
			jobService = (JobServiceImpl) cxt.getBean("jobService");
			LOGGER.info("Successfully registered jobService in " + Util.getTimeSince(startTime));
		}

		// *********************************
		// REGISTER GEOSERVICES
		// *********************************
		if (!DBG) {
			startTime = System.currentTimeMillis();
			geoService = new GeoLocServices();
			LOGGER.info("Successfully registered geoService in " + Util.getTimeSince(startTime));
		}

		// *********************************
		// REGISTER CLIENTSERVICE
		// *********************************
		if (!DBG) {
			startTime = System.currentTimeMillis();
			clientService = (ClientServiceImpl) cxt.getBean("clientService");
			LOGGER.info("Successfully registered clientService in " + Util.getTimeSince(startTime));
		}

		startTime = System.currentTimeMillis();
		fpUserService = (FPMemberServiceImpl) cxt.getBean("fpUserService");
		LOGGER.info("Successfully registered fpUserService in " + Util.getTimeSince(startTime));

		LOGGER.info("Successfully Created LawnBuzz DAO");
		cxt.close();
	}

}
