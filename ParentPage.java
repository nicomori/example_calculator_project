package com.demo.nicolas.mori.example_calculator_project;

import org.openqa.selenium.WebDriver;

/**
 * This Class is an abstract class, and created for make the interaction of
 * all the methods in the class DSL with all different parts of the pages of the app.
 */
public abstract class ParentPage extends DSL{
 
	public ParentPage(WebDriver driver) {
        super(driver);
    }
 
}
