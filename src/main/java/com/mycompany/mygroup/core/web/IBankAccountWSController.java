package com.mycompany.mygroup.core.web;

import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletException;
import java.io.IOException;

public interface IBankAccountWSController {
    public ResponseEntity<ResponseModel> withdraw(@RequestBody RequestModel requestModel) throws ServletException, IOException;
    public ResponseEntity<ResponseModel> deposit(@RequestBody RequestModel requestModel) throws ServletException, IOException;
}
