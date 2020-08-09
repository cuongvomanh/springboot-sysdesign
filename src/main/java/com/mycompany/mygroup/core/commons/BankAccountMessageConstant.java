package com.mycompany.mygroup.core.commons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bankaccountmessage")
@PropertySource("classpath:application.yml")
public class BankAccountMessageConstant {
  private String withdrawFailed;
  private String depositFailed;
  private String withdrawSuccessful;
  private String depositSuccessful;

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
