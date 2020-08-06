package com.mycompany.mygroup.core.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IBankAccountController {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
