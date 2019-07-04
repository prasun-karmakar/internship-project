package com.signify.internshipproject.useridentityservice.service;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.signify.internshipproject.useridentityservice.dto.UserDTO;
import com.signify.internshipproject.useridentityservice.model.UserInfo;
import com.signify.internshipproject.useridentityservice.model.UserRegisterInfo;


@Path("/userinfo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserImpl implements UserService {


	
	
	@Override
	@GET
	@Path("/{username}/get")
	public UserInfo getUserProfile(@PathParam("username") String username) { //myprofile
		UserDTO userDTO = new UserDTO();
		RestHandler handler=new RestHandler();
		UserInfo userinfo=new UserInfo();
		userDTO.setUsername(username);
		userinfo=handler.getProfileDetail(userDTO);
		
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

		/*
		 * for(int count=0;count<res.size();count++) { UserDTO
		 * userDTO1=(UserDTO)res.get(count);
		 * userinfo.setUsername(userDTO1.getUsername());
		 * userinfo.setEmail(userDTO1.getEmail());
		 * userinfo.setMobileno(userDTO1.getMobileno());
		 * userinfo.setLanguage_name(userDTO1.getLanguage_name());
		 * userinfo.setName(userDTO1.getName());
		 * 
		 * }
		 */
		return userinfo;
	
	}
	
	@Override
	@POST
    @Path("/registerconfirm")
	public Response registerUserDetails(UserRegisterInfo userReg) { //Register confirm
		UserDTO userDTO = new UserDTO();
	
		RestHandler handler=new RestHandler();
		userDTO.setUsername(userReg.getUsername());
		userDTO.setPassword(userReg.getPassword());
		userDTO.setRegisterconfirmpassword(userReg.getUsername());
		userDTO.setEmail(userReg.getEmail());
		userDTO.setMobileno(userReg.getMobileno());
		userDTO.setTimezone_id(userReg.getTimezone_id());
		userDTO.setLanguage_id(userReg.getLanguage_id());
		boolean status=handler.registerUserDetails(userDTO);
		System.out.println(status); 

		return Response.status(Response.Status.CREATED.getStatusCode()).build();
	} 
	
	
	@Override
	@GET
    @Path("/{username}/delete")
	public Response deletePerson(@PathParam("username") String username) { //DeleteRecords
		UserDTO userDTO = new UserDTO();
		RestHandler info=new RestHandler();
		userDTO.setUsername(username);
		boolean status=info.deleteUserDetail(userDTO);
		System.out.println(status);
		return Response.status(200).entity(status).build();
	}
	
	@Override
	@PUT
	@Path("/{username}/update")
	public Response updateUserDetail(@PathParam("username") String username,UserRegisterInfo userReg) { //editUserDetails in submission
		UserDTO userDTO = new UserDTO();
		
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
		return Response.status(200).entity(status).build();
}
	
	@Override
	@GET
	@Path("/{username}/getlastlogin")
	public UserInfo getLastlogintime(@PathParam("username") String username) { //Users lastlogintime
		UserDTO userDTO = new UserDTO();
		RestHandler handler=new RestHandler();
		UserInfo userInfo=new UserInfo();
		userDTO.setUsername(username);
		handler.getLastlogintime(userDTO);
		userInfo.setLastLogintime(userDTO.getZonedDateTime());
		
		return userInfo;
	}
	
	@Override
	@GET
	@Path("/{username}/language")
	public UserInfo getLanguageId(@PathParam("username") String username) { //Users lastlogintime
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);

		RestHandler handler=new RestHandler();
		UserInfo userInfo=handler.getLanguageId(userDTO);
		System.out.println(userInfo.getLanguageId());
		return userInfo;
	}

	@Override
	@POST
    @Path("/authenticateuser")
	public Response authenticatelogincredentials(UserInfo userInfo) { //Login authentication
		
		
		RestHandler handler=new RestHandler();
		
		boolean status=handler.authenticateuserlogin(userInfo);
		
		System.out.println(status); 
		if(status) {
		return Response.status(200).entity(status).build();
		}
		else {
			return Response.status(404).entity(status).build();
		}
	
	}
	
	@GET
	@Path("/{username}/timezone-name")
	public UserInfo getTimezoneName(@PathParam("username") String userName) {
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(userName);
		RestHandler handler=new RestHandler();
		
		String timezoneName=handler.getTimezoneName(userDTO);
		UserInfo userInfo=new UserInfo();
		userInfo.setTimezoneName(timezoneName);
		
		return userInfo;
		
	}
}

	
	




