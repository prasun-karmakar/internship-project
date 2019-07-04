package com.signify.internshipproject.webappservice.service;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.signify.internshipproject.webappservice.dto.UserDTO;
import com.signify.internshipproject.webappservice.model.Response;
import com.signify.internshipproject.webappservice.model.UserInfo;
import com.signify.internshipproject.webappservice.model.UserLastlogin;
import com.signify.internshipproject.webappservice.model.UserRegisterInfo;


@Path("/userinfo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserImpl implements UserService {


	
	
	@Override
	@GET
	@Path("/{username}/get")
	public UserInfo getUserProfile(@PathParam("username") String username) { //myprofile
		UserDTO userDTO = new UserDTO();
		RestHandler info=new RestHandler();
		UserInfo userinfo=new UserInfo();
		userDTO.setUsername(username);
		UserDTO res=info.getProfileDetail(userDTO);
		userinfo.setUsername(res.getUsername());
		userinfo.setName(res.getName());
		userinfo.setEmail(res.getEmail());
		userinfo.setLanguage_name(res.getLanguage_name());
		userinfo.setMobileno(res.getMobileno());
		userinfo.setLastlogin(res.getLastlogin());
		return userinfo;
	}
	
	@Override
	@GET
	@Path("/getAll")
	public UserInfo getAllRegisteredUsers() {  //getAllregisteredusers
		UserDTO userDTO = new UserDTO();
		RestHandler info=new RestHandler();
		ArrayList<UserDTO> res=info.getRegisteredUsers(userDTO);
		System.out.println(res);
		UserInfo userinfo=new UserInfo();

		for(int count=0;count<res.size();count++)
		{
		 UserDTO userDTO1=(UserDTO)res.get(count);
		 userinfo.setUsername(userDTO1.getUsername());
		 userinfo.setEmail(userDTO1.getEmail());
		 userinfo.setMobileno(userDTO1.getMobileno());
		 userinfo.setLanguage_name(userDTO1.getLanguage_name());
		 userinfo.setName(userDTO1.getName());
		
		}
		return userinfo;
	
	}
	
	@Override
	@POST
    @Path("/registerconfirm")
	public Response registerUserDetails(UserRegisterInfo userReg) { //Register confirm
		UserDTO userDTO = new UserDTO();
		Response response = new Response();
		RestHandler info=new RestHandler();
		userDTO.setUsername(userReg.getUsername());
		userDTO.setPassword(userReg.getPassword());
		userDTO.setRegisterconfirmpassword(userReg.getUsername());
		userDTO.setEmail(userReg.getEmail());
		userDTO.setMobileno(userReg.getMobileno());
		userDTO.setTimezone_id(userReg.getTimezone_id());
		userDTO.setLanguage_id(userReg.getLanguage_id());
		boolean status=info.registerUserDetails(userDTO);
		System.out.println(status); 
		response.setStatus(true);
		response.setMessage("Person created successfully");
		return response;
	} 
	
	
	@Override
	@GET
    @Path("/{username}/delete")
	public Response deletePerson(@PathParam("username") String username) { //DeleteRecords
		Response response = new Response();
		UserDTO userDTO = new UserDTO();
		RestHandler info=new RestHandler();
		userDTO.setUsername(username);
		boolean status=info.deleteUserDetail(userDTO);
		System.out.println(status);
		response.setStatus(true);
		response.setMessage("Person deleted successfully");
		return response;
	}
	
	@Override
	@PUT
	@Path("/{username}/update")
	public Response updateUserDetail(@PathParam("username") String username,UserRegisterInfo userReg) { //editUserDetails in submission
		UserDTO userDTO = new UserDTO();
		Response response = new Response();
		RestHandler info=new RestHandler();
		userDTO.setUsername(userReg.getUsername());
		userDTO.setPassword(userReg.getPassword());
		userDTO.setRegisterconfirmpassword(userReg.getUsername());
		userDTO.setEmail(userReg.getEmail());
		userDTO.setMobileno(userReg.getMobileno());
		userDTO.setTimezone_id(userReg.getTimezone_id());
		userDTO.setLanguage_id(userReg.getLanguage_id());
		boolean status=info.editUserDetails(userDTO);
		System.out.println(status); 
		response.setStatus(true);
		response.setMessage("Person profile updated successfully");
		return response;
}
	
	@Override
	@GET
	@Path("/{username}/getlastlogin")
	public UserLastlogin getLastlogintime(@PathParam("username") String username) { //Users lastlogintime
		UserDTO userDTO = new UserDTO();
		RestHandler info=new RestHandler();
		UserLastlogin userlastlogin=new UserLastlogin();
		userDTO.setUsername(username);
		info.getLastlogintime(userDTO);
		userlastlogin.setLastlogin(userDTO.getZonedDateTime());
		System.out.println(userlastlogin);
		return userlastlogin;
	}

	@Override
	@POST
    @Path("/authenticateuser")
	public Response authenticatelogincredentials(UserDTO userDTO) { //Login authentication
		
		Response response = new Response();
		RestHandler info=new RestHandler();
		userDTO.setUsername(userDTO.getUsername());
		userDTO.setPassword(userDTO.getPassword());
		boolean status=info.authenticateuserlogin(userDTO);
		System.out.println(status); 
		if(status) {
			response.setStatus(true);
			response.setMessage("Successfully authenticated");
			}
			else
			{
			
				response.setMessage("Data not found");
				response.setStatus(false);
			}
			return response;
			} 
	
}

	
	




