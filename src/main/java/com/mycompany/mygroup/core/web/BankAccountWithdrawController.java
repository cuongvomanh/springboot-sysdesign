package com.mycompany.mygroup.core.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.mygroup.core.infradi.DependencyResolver;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import com.mycompany.mygroup.core.usecase.account.BankAccountBoundary;
import com.mycompany.mygroup.core.web.utils.FormUtil;

import javax.management.InstanceNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/bankAccount/withdraw"})
public class BankAccountWithdrawController extends HttpServlet implements IBankAccountController {
    private static final long serialVersionUID = 2686801510274002166L;
    private BankAccountBoundary bankAccountBoundary;

    public BankAccountWithdrawController() throws InstanceNotFoundException {
        this.bankAccountBoundary = (BankAccountBoundary) DependencyResolver.getInstance("BankAccountBoundary");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestModel requestModel = FormUtil.toModel(RequestModel.class, request);
        ResponseModel responseModel = this.bankAccountBoundary.withdraw(requestModel);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseModel);
    }
}
