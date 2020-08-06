package com.mycompany.mygroup;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(UpmauApp.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println( "Hello World!" );
    }
}
