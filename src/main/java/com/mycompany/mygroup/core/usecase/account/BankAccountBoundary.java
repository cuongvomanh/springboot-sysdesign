
package com.mycompany.mygroup.core.usecase.account;
import com.mycompany.mygroup.core.usecase.RequestModel;
import com.mycompany.mygroup.core.usecase.ResponseModel;
public interface BankAccountBoundary {
    public ResponseModel withdraw(RequestModel request);
    public ResponseModel deposit(RequestModel request);
}

