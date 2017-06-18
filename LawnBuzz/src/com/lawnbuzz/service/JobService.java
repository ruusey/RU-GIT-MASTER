package com.lawnbuzz.service;

import java.util.ArrayList;
import java.util.List;

import com.lawnbuzz.models.GeoLocation;
import com.lawnbuzz.models.JobRequest;

public interface JobService {
	public List<JobRequest> getAllJobs();
	public List<JobRequest> getAllIncompleteJobs();
	public void addJob(JobRequest jr);
	public ArrayList<JobRequest> getJobsInRadius(GeoLocation center, int radius);
}
