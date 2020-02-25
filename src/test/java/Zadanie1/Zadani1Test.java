package Zadanie1;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Feature/Zadanie1.feature",
        plugin = {"pretty","html:out"})

public class Zadani1Test {
}
