package com.lld.splitwise;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.lld.splitwise.commands.CommandRegistry;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
	
	Scanner scanner;
	CommandRegistry commandRegistry;
	
	@Autowired
	public SplitwiseApplication(CommandRegistry commandRegistry) {
		scanner = new Scanner(System.in);
		this.commandRegistry = commandRegistry; 
	}

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		while(true) {
			System.out.println("Tell what you want to do");
			String input = scanner.nextLine();
			commandRegistry.execute(input);
		}
		
	}

}
