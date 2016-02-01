/**
 * 
 */
package com.cogxio.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cogxio.services.DataUploader;

/**
 * @author hrishabh shukla
 *
 */
@Controller
public class MainController 
{
	@RequestMapping(value={"/", "/index"})
	public void homePage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.sendRedirect("list");
	}
	
	@RequestMapping(value={"/list"})
	public String showList()
	{
		return "list";
	}
	
	@RequestMapping(value={"/save"})
	public @ResponseBody String saveData(HttpServletRequest request, HttpServletResponse response)
	{
		new DataUploader().uploadLocationData();
		return "Success";
	}
}
