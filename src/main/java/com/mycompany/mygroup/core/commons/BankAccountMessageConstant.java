package com.mycompany.mygroup.core.commons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bankaccountmessage")
public class BankAccountMessageConstant {
    private String withdrawFailed;
    private String depositFailed;
    private String withdrawSuccessful;
    private String depositSuccessful;

    private String withdrawBadRequest;
    private String withdrawBadRequestNotFound;
    private String depositBadRequest;
    private String depositRequestNotFound;

  public String getWithdrawBadRequest() {
    return withdrawBadRequest;
  }

  public void setWithdrawBadRequest(String withdrawBadRequest) {
    this.withdrawBadRequest = withdrawBadRequest;
  }

  public String getWithdrawBadRequestNotFound() {
    return withdrawBadRequestNotFound;
  }

  public void setWithdrawBadRequestNotFound(String withdrawBadRequestNotFound) {
    this.withdrawBadRequestNotFound = withdrawBadRequestNotFound;
  }

  public String getDepositBadRequest() {
    return depositBadRequest;
  }

  public void setDepositBadRequest(String depositBadRequest) {
    this.depositBadRequest = depositBadRequest;
  }

  public String getDepositRequestNotFound() {
    return depositRequestNotFound;
  }

  public void setDepositRequestNotFound(String depositRequestNotFound) {
    this.depositRequestNotFound = depositRequestNotFound;
  }

  public String getWithdrawFailed() {
        return this.withdrawFailed;
    }

    public void setWithdrawFailed(String withdrawFailed) {
        this.withdrawFailed = withdrawFailed;
    }

    public String getWithdrawSuccessful() {
        return this.withdrawSuccessful;
    }

    public void setWithdrawSuccessful(String withdrawSuccessful) {
        this.withdrawSuccessful = withdrawSuccessful;
    }

    public String getDepositFailed() {
        return this.depositFailed;
    }

    public void setDepositFailed(String depositFailed) {
        this.depositFailed = depositFailed;
    }

    public String getDepositSuccessful() {
        return this.depositSuccessful;
    }

    public void setDepositSuccessful(String depositSuccessful) {
        this.depositSuccessful = depositSuccessful;
    }
}
