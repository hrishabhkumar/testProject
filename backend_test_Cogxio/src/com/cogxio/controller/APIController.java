package com.cogxio.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cogxio.database.LocationJDO;
import com.cogxio.services.Services;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value={"/api/*"})
public class APIController 
{
	@RequestMapping(value={"list"})
	public @ResponseBody String getList(HttpServletRequest request, HttpServletResponse response)
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<LocationJDO> locationJDOs = new Services().getLocationsList(); 
		return gson.toJson(locationJDOs);
	}
	@RequestMapping(value={"count"})
	public @ResponseBody String getCount(HttpServletRequest request, HttpServletResponse response)
	{
		return new Services().getCount()+"";
	}
	
	@RequestMapping(value={"search"})
	public @ResponseBody String getSearch(HttpServletRequest request, HttpServletResponse response, @RequestParam("q") String query)
	{
		Gson gson = new GsonBuilder().serializeNulls().create();
		if(query == null)
		{
			return "[]";
		}
		List<LocationJDO> locationJDOs = new Services().getSearch(query); 
		return gson.toJson(locationJDOs);
	}
}
