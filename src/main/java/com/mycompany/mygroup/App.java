package com.mycompany.mygroup;

import com.mycompany.mygroup.core.commons.BankAccountMessageConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(App.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println( "Hello World!" );
    }
    @Autowired
    private BankAccountMessageConstant bankAccountMessageConstant;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Global variable:");
        System.out.println("\t Email: " + bankAccountMessageConstant.getWithdrawFailed());
    }
}
