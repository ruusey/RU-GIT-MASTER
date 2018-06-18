//------------------------------------------------------------------------------------------------------------------------------------------
//
// N2ANALYTICS, LLC ("COMPANY") CONFIDENTIAL
// Unpublished Copyright (c) 2013-2016 N2ANALYTICS, LLC, All Rights Reserved.
//
// NOTICE:  All information contained herein is, and remains the property of COMPANY. The intellectual and technical concepts contained
// herein are proprietary to COMPANY and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
// Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
// from COMPANY.  Access to the source code contained herein is hereby forbidden to anyone except current COMPANY employees, managers or contractors who have executed
// Confidentiality and Non-disclosure agreements explicitly covering such access.
//
// The copyright notice above does not evidence any actual or intended publication or disclosure of this source code, which includes  
// information that is confidential and/or proprietary, and is a trade secret, of COMPANY.  ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
// OR PUBLIC DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS WRITTEN CONSENT OF COMPANY IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
// LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS  
// TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.                
//
//------------------------------------------------------------------------------------------------------------------------------------------

package com.fps.util;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public abstract class Config {

	public final static Locale DEFAULT_LOCALE = Locale.US;
	
	public final static SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.UK);
	public final static SimpleDateFormat MEDIUM_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm", Locale.UK);
	public final static SimpleDateFormat LONG_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.UK);

	// UTC Date Formats
	public final static SimpleDateFormat UTC_SHORT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	public final static SimpleDateFormat UTC_MEDIUM_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	public final static SimpleDateFormat UTC_LONG_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	static {
		UTC_SHORT_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		UTC_MEDIUM_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		UTC_LONG_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	// JSON SerDes Optimizations
	public static final boolean JSON_SERDES_USE_AFTERBURNER_DEFAULT = false;

	public static final UUID UUID_EMPTY = UUID
			.fromString("00000000-0000-0000-0000-000000000000");

//	public static final URI DEFAULT_MESSAGEBROKER_CONNECT_STRING = URI
//			.create("failover:(tcp://n2-messagebroker-1:61616?wireFormat.maxInactivityDuration=60000,tcp://n2-messagebroker-2:61616?wireFormat.maxInactivityDuration=60000)");

	public static final URI DEFAULT_MESSAGEBROKER_CONNECT_STRING = URI
			.create("failover:(tcp://n2-messagebroker-1:61616,tcp://n2-messagebroker-2:61616)");

	public static final int DEFAULT_JMS_CONNECTION_POOL_MAX_CONNECTIONS = 10;
	public static final int MAX_JMS_CONNECTION_POOL_MAX_CONNECTIONS = 100;
	
	//	Configuration Manager
	public static final Long DEFAULT_CONFIGURATION_MANAGER_DELEGATE_CALL_WAIT_TIMEOUT = 10000L;
	public static final String CONFIGURATION_REQUEST_QUEUE = "N2.MAESTRO.ConfigurationRequest";
	public static final String CONFIGURATION_UPDATE_TOPIC = "N2.MAESTRO.ConfigurationUpdate";
	
	//	Election Manager
	public static final String ELECTION_TOPIC = "N2.MAESTRO.Election";
	public static final String ELECTION_QUEUE = "N2.MAESTRO.Election";
	public static final long MAX_ELECTION_INACTIVITY_THRESHOLD = 60000;
	
	//	Transaction Manager
	public static final Long DEFAULT_TRANSACTION_MANAGER_DELEGATE_CALL_WAIT_TIMEOUT = 10000L;
	public static final String TRANSACTION_REQUEST_QUEUE = "N2.MAESTRO.TransactionRequest";
	
	//	Service Defaults
	public static final String DEFAULT_SERVICE_JVM_OPTIONS = "-XX:+UseParallelOldGC";
	public static final String DEFAULT_SERVICE_EXECUTABLE_TYPE = "JAVA";
	
	public static final String DEFAULT_VERSION = "0.0.0";
	
	public static final String NIMBIO_CENTRAL_IDENTIFIER = "59eb9fb7-6af7-4a8b-bb88-5fdc535a3b25";
	
}
