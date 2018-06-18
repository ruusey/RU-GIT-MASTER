

package com.fps.constants;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public interface Constants {
	
	public static final int DEFAULT_DATA_REQUEST_PAGE_NUMBER = 1;
	public static final int DEFAULT_DATA_REQUEST_ITEMS_PER_PAGE = 50;
	public static final short DEFAULT_DATA_REQUEST_UTC_OFFSET = 0;
	public static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$#,##0.00;($#,##0.00)");
	public static final DecimalFormat DOLLAR_FORMAT = new DecimalFormat("$#,##0;($#,##0)");
	
	//	=============
	//	Configuration
	//	=============
	
	//	------------------------------------------
	//	Set to System-Specific Database Properties
	//	------------------------------------------
	public static final String CONFIGURATION_PREFIX = "TOTUM-PLATFORM.";
	
	public static final String CONFIGURATION_DB_DRIVER_CLASS_NAME = CONFIGURATION_PREFIX + "DB.DRIVER_CLASS_NAME";
	public static final String CONFIGURATION_DB_CONNECT_STRING = CONFIGURATION_PREFIX + "DB.CONNECT_STRING";
	public static final String CONFIGURATION_DB_USERNAME = CONFIGURATION_PREFIX + "DB.USERNAME";
	public static final String CONFIGURATION_DB_PASSWORD = CONFIGURATION_PREFIX + "DB.PASSWORD";
	public static final String CONFIGURATION_DB_MAX_CONNECTIONS = CONFIGURATION_PREFIX + "DB.MAX_CONNECTIONS";

	public static final String CONFIGURATION_CACHE_MEMCACHED_CONNECT_STRING = CONFIGURATION_PREFIX + "CACHE.MEMCACHED_CONNECT_STRING";
	public static final String CONFIGURATION_CACHE_SESSION_TTL = CONFIGURATION_PREFIX + "CACHE.SESSION.TTL";
	public static final String CONFIGURATION_ENVIRONMENT = CONFIGURATION_PREFIX + "ENVIRONMENT";
	public static final String CONFIGURATION_LOGGING_LOG_API_REQUESTS = CONFIGURATION_PREFIX + "LOGGING.LOG_API_REQUESTS";
	public static final String CONFIGURATION_LOGGING_MAX_PAYLOAD_LENGTH = CONFIGURATION_PREFIX + "LOGGING.MAX_PAYLOAD_LENGTH";
	
	public static final String CONFIGURATION_EMAIL_FROM = CONFIGURATION_PREFIX + "EMAIL.FROM";
	public static final String CONFIGURATION_EMAIL_SERVER = CONFIGURATION_PREFIX + "EMAIL.SERVER";
	public static final String CONFIGURATION_EMAIL_PORT = CONFIGURATION_PREFIX + "EMAIL.PORT";
	public static final String CONFIGURATION_EMAIL_AUTH_REQUIRED = CONFIGURATION_PREFIX + "EMAIL.AUTH_REQUIRED";
	public static final String CONFIGURATION_EMAIL_USE_SSL = CONFIGURATION_PREFIX + "EMAIL.USE_SSL";
	public static final String CONFIGURATION_EMAIL_USER = CONFIGURATION_PREFIX + "EMAIL.USER";
	public static final String CONFIGURATION_EMAIL_PASSWORD = CONFIGURATION_PREFIX + "EMAIL.PASSWORD";
	
	public static final String CONFIGURATION_MAXMIND_LICENSE_KEY = "N2.MAXMIND.LICENSE_KEY";
	public static final String CONFIGURATION_MAXMIND_USER_ID = "N2.MAXMIND.USER_ID";
	
	//	======================================================================
	//	Defaults - Should only be used if nimb.io unavailable or misconfigured
	//	======================================================================

	//	---------------------------
	//	Defaults - DB Configuration
	//	---------------------------
	public static final String DEFAULT_DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
//	public static final String DEFAULT_DB_CONNECT_STRING = "jdbc:mysql://totum-platform-cluster.cluster-cevjq7a97ac2.us-west-2.rds.amazonaws.com:3306/totum_platform_dev?rewriteBatchedStatements=true";
	public static final String DEFAULT_DB_CONNECT_STRING = "jdbc:mysql://totum-platform-us-east-1a.c4ku1bvr5tuv.us-east-1.rds.amazonaws.com:3306/totum?rewriteBatchedStatements=true";
	public static final String DEFAULT_DB_USERNAME = "totum";
	public static final String DEFAULT_DB_PASSWORD = "ZzQYZxB!hBw4waYE";
	public static final Integer DEFAULT_DB_MAX_CONNECTIONS = 5;

	//	------------------------------
	//	Defaults - Email Configuration
	//	------------------------------
	public static final Boolean DEFAULT_EMAIL_AUITH_REQUIRED = true;
	public static final String DEFAULT_EMAIL_SERVER = "email-smtp.us-east-1.amazonaws.com";
	public static final Integer DEFAULT_EMAIL_PORT = 465;
	public static final String DEFAULT_EMAIL_USER = "AKIAJMTZST44EKMIE7UQ";
	public static final String DEFAULT_EMAIL_PASSWORD = "Aj7Nbv7fwwovoT22zeeZBGX2j1db07YT/Gd+U8qK56Sh";
	public static final Boolean DEFAULT_EMAIL_USE_SSL = true;
	public static final String DEFAULT_EMAIL_FROM = "support@totumrisk.com";

	//	------------------------------
	//	Defaults - Cache Configuration
	//	------------------------------
//	public static final String DEFAULT_CACHE_MEMCACHED_CONNECT_STRING = "totum-platform-dev-1.4x5ctb.0001.usw2.cache.amazonaws.com:11211";
	public static final String DEFAULT_CACHE_MEMCACHED_CONNECT_STRING = "totum-platform-cuse1.mmilxe.0001.use1.cache.amazonaws.com:11211";
	public static final Integer DEFAULT_CACHE_SESSION_TTL = 0;		//	seconds unit / 0 = don't expire
	public static final String DEFAULT_ENVIRONMENT = "DEV";
	public static final String	DEFAULT_SYSTEM_IDENTIFIER = com.fps.util.Config.UUID_EMPTY.toString();
	public static final Boolean DEFAULT_LOGGING_LOG_API_REQUESTS = false;
	public static final Integer DEFAULT_LOGGING_MAX_PAYLOAD_LENGTH = 512;

	public static final String DEFAULT_MAXMIND_LICENSE_KEY = "oRzPNkWLUxei";
	public static final String DEFAULT_MAXMIND_USER_ID = "109672";
	
	//	----------
	//	DEV System
	//	----------
	public static final String				CONFIGURATION_DEV_HOSTNAME_PATTERN								=	CONFIGURATION_PREFIX + "DEV.HOSTNAME_PATTERN";
	public static final String				CONFIGURATION_DEV_SYSTEM_IDENTIFIER								=	CONFIGURATION_PREFIX + "DEV.SYSTEM_IDENTIFIER";
	public static final String				DEFAULT_DEV_HOSTNAME_PATTERN									=	"dev-api*,localhost,127.0.0.1";
	public static final String				DEFAULT_DEV_SYSTEM_IDENTIFIER									=	"545853e7-1eab-48bb-a978-332ddfd8be76";
	
	//	---------
	//	QA System
	//	---------
	public static final String				CONFIGURATION_QA_HOSTNAME_PATTERN								=	CONFIGURATION_PREFIX + "QA.HOSTNAME_PATTERN";
	public static final String				CONFIGURATION_QA_SYSTEM_IDENTIFIER								=	CONFIGURATION_PREFIX + "QA.SYSTEM_IDENTIFIER";
	public static final String				DEFAULT_QA_HOSTNAME_PATTERN										=	"qa-api*";
	public static final String				DEFAULT_QA_SYSTEM_IDENTIFIER									=	"8aefe176-cadb-4c59-802a-a1eb407118d7";

	//	-----------
	//	PROD System
	//	-----------
	public static final String				CONFIGURATION_PROD_HOSTNAME_PATTERN								=	CONFIGURATION_PREFIX + "PROD.HOSTNAME_PATTERN";
	public static final String				CONFIGURATION_PROD_SYSTEM_IDENTIFIER							=	CONFIGURATION_PREFIX + "PROD.SYSTEM_IDENTIFIER";
	public static final String				DEFAULT_PROD_HOSTNAME_PATTERN									=	"api*";
	public static final String				DEFAULT_PROD_SYSTEM_IDENTIFIER									=	"3d3dc2f6-95a0-4921-b94c-71a848f3cd6f";

	//	============
	//	HTTP Headers
	//	============
	
	public static final String HTTP_HEADER_TOKEN = "token";
	public static final String HTTP_HEADER_APP_KEY = "app-key";
	public static final String HTTP_HEADER_APP_SECRET = "app-secret";

	public static final String HTTP_HEADER_X_FORWARDED_FOR = "X-Forwarded-For";
	public static final String HTTP_HEADER_X_FORWARDED_PORT = "X-Forwarded-Port";
	public static final String HTTP_HEADER_X_FORWARDED_PROTO = "X-Forwarded-Proto";
	
	public static final String HTTP_HEADER_X_CLIENT_IP = "X-client-ip";
	public static final String HTTP_HEADER_X_EMAIL = "X-email";
	public static final String HTTP_HEADER_X_USER_ID = "X-user-id";
	public static final String HTTP_HEADER_X_PRIVILEGES = "X-privileges";
	public static final String HTTP_HEADER_X_APPLICATION_ID = "X-application-id";
	public static final String HTTP_HEADER_X_ADVISOR_ID = "X-advisor-id";
	public static final String HTTP_HEADER_X_SYSTEM_ADMIN = "X-system-admin";
	public static final String HTTP_HEADER_X_SYSTEM_IDENTIFIER = "X-system-identifier";
	
	//	============
	//	HTTP Filters
	//	============
	public static final String HTTP_FILTER_RESOURCE_USERS = "/users";
	public static final String HTTP_FILTER_RESOURCE_USERS_INVITE = "/users/invite";
	public static final String HTTP_FILTER_RESOURCE_USERS_RESET_PASSWORD = "/users/reset-password";
	public static final String HTTP_FILTER_RESOURCE_PING = "/ping";
	public static final String HTTP_FILTER_RESOURCE_SESSIONS = "/sessions";
	public static final String HTTP_FILTER_RESOURCE_QUESTIONNAIRES = "/questionnaires";
	public static final String HTTP_FILTER_RESOURCE_SWAGGER = "/swagger";
	public static final String HTTP_FILTER_RESOURCE_PUBLIC = "/public";
	
	//	=====
	//	Cache
	//	=====
	public static final String CACHE_KEY_SESSIONS = "sessions/";
	public static final String CACHE_KEY_PROCESSED_IP_PROFILES = "processed-ip-profiles/";
	public static final String CACHE_KEY_USER_ROLES = "user-roles/";
	
	//	=====================
	//	Error / Info Messages
	//	=====================
	public static final String MSG_CACHE_UNAVAILABLE = "Cache is Unavailable.  Confirm Cache service is online and configuration settings are correct.";
	public static final String MSG_INITIALIZING_CACHE_CLIENT_CONNECTION = "Initializing Cache Client Connection";
	
	//	==============
	//	Schedule Tasks
	//	==============
	public static final int USER_ROLE_CACHE_SCHEDULE_INIT_DELAY = 0;
	public static final int USER_ROLE_CACHE_SCHEDULE_PERIOD = 30;
	public static final int USER_ROLE_CACHE_TTL = 60;
	
	//	========
	//	Security
	//	========
	public static final int DEFAULT_BCRYPT_LOG_ROUNDS = 7;
	public static final int MIN_BCRYPT_LOG_ROUNDS = 4;
	public static final int MAX_BCRYPT_LOG_ROUNDS = 12;
	
	//	===========
	//	Application
	//	===========
	
	public static final String CACHE_KEY_PREFIX_APPLICATION = "applications/";
	public static final int CACHE_TTL_APPLICATION = 300;
	
	//	========
	//	Sessions
	//	========
	public static final int CACHE_TTL_SESSIONS = 300;
	
	//	=============
	//	Black Diamond
	//	=============

	public static final int BLACK_DIAMOND_BATCH_SIZE = 10000;
	public static final SimpleDateFormat BLACK_DIAMOND_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public static final String BLACK_DIAMOND_API_CLIENT_ID = "Totum_Sandbox_Api";
	public static final String BLACK_DIMAOND_API_CLIENT_SECRET = "DzaFCM5BGt4i7GRFuc0VGLKYKQaTMqg8Wdx8yz5Yg6xLJ5+neLHlzarFJ5cjDa3o14vEQA9/o2FyY50NQq2pvQ==";
	
	public static final String BLACK_DIAMOND_API_TEST_USERNAME = "totum_apiuser";
	public static final String BLACK_DIAMOND_API_TEST_PASSWORD = "qF5%TUo#";
	
	//	============
	//	Certificates
	//	============
	
    public static final String PUBLIC_KEY_FILE = "/certs/totum-platform.public.der";
    public static final String PRIVATE_KEY_FILE = "/certs/totum-platform.private.der";
	
    //	=============
    //	Questionnaire
    //	=============

    public static final String QUESTIONNAIRE_WEB_URL = "https://platform.totumrisk.com/web/Questionnaire.html?directive=";
    public static final int DEFAULT_QUESTIONNAIRE_ID = 0;
    
    //	========
    //	Advisors
    //	========
    
    public static final String ADVISOR_DEFAULT_LOGO_URL = "httpS://platform.totumrisk.com/web/img/totum.svg";
    
}

