package org.ATBSelenium.DataDrivenTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Lab02 {
    @DataProvider(name ="loginTest", parallel = true)
    public Object[][] getData() {
        return new Object[][]{
                new Object[]{"admin@gmail.com", "pass123"},
                new Object[]{"admin123@gmail.com", "pass123"},
                new Object[]{"admin123@gmail.com", "pass124"}
        };

    }
    @Test(dataProvider = "loginTest")
    public void loginTest(String email , String password){
        System.out.println(email+ "|"+ password);

    }
}
