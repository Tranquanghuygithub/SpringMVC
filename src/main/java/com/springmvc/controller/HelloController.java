package com.springmvc.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.User;
import com.springmvc.validator.UserValidator;

@Controller
public class HelloController {
	// xem ali cai nay nha
	@Autowired // tuwj ddoongj tim doi tuong phu hop gan bean vao
	private UserValidator userValidator;

	// Requestparam
	@RequestMapping("/say-hello")
	public ModelAndView sayHello(HttpServletRequest request, HttpServletResponse respose, HttpSession session,
			@RequestParam(name = "user", required = true) String username,
			@RequestHeader(name = "Accept", required = false) String contentType) {

		request.setAttribute("msg", contentType);
		return new ModelAndView("hello");
	}

	// pathVariable
	@RequestMapping("/hello/{name}")
	public String hello(HttpServletRequest request, @PathVariable(name = "name") String name) {
		request.setAttribute("msg", name);
		return "hello";
	}
	// Binding vào object
	// B1: khoi tao object va truyen sang view

	@RequestMapping(value = "/them-user", method = RequestMethod.GET)
	public String hello(HttpServletRequest request) {
		User user = new User();
		user.setName("Spring");// value default
		request.setAttribute("user", user);
		// co thể set 1 loat cac favourite sang view= cachs request.at 1 list . ben kia
		// lamf binhf thuong thoi
		return "addUser";
	}

	// B2: ben trang view su dung Spring form vói modelAtribute....
	// B3: Sau khi bam submit se tu đong add vào object
	@RequestMapping(value = "/them-user", method = RequestMethod.POST)
	public String hello(HttpServletRequest request, @ModelAttribute("user") User user,

			BindingResult bindingResult) {

		userValidator.validate(user, bindingResult);// neu ko co ham nay
		if (bindingResult.hasErrors()) {// thì auto kiem tra nay ko co loi j ca nha// hai 2 nay rang buoc lan nhau

			// kiem tra xe co loi ko. neu loi tra ve form nhap cho nguoi dung
			System.out.println("Co loi af"+ bindingResult.getFieldError());
			return "addUser";
		}
		MultipartFile file = user.getAvatar();
		File newfile = new File("F:\\Web\\Course\\" + file.getOriginalFilename());
		FileOutputStream fileOutputStream;
		try {

			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(file.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("u", user);
		return "viewUser";
	}

	@RequestMapping(value = "/upload-file", method = RequestMethod.GET)
	public String upload(HttpServletRequest request) {
		return "upload";
	}

	@RequestMapping(value = "/upload-file", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam(name = "file") List<MultipartFile> files) {
		// luu file xuong o cung
		for (MultipartFile file : files) {

			File newfile = new File("F:\\Web\\Course\\File" + file.getOriginalFilename());
			FileOutputStream fileOutputStream;
			try {

				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(file.getBytes());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		request.setAttribute("file", files);

		return "viewFile";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response
			) {
		//String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/"); tra ve 1 duong dan file trong ung dung
		String dataDirectory = "F:\\Web\\Course\\File";
		Path file= Paths.get(dataDirectory,"7.jpg");// de dinh vi 1 cai file trong he thong

		if(Files.exists(file)) {
			//doi voi moi 1 loai file image/ pdf lai co 1 kieu contenttype khac nhau
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename="+"7.jpg");
            try
            {
                Files.copy(file, response.getOutputStream());// sao chep byte tu cai file muon download-> outputStream= ghi vef phia client
                response.getOutputStream().flush();
            } 
            catch (IOException ex) {
                ex.printStackTrace();
            }
		}
	}
}
