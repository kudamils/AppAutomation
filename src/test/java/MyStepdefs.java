import io.cucumber.java.en.Given;

public class MyStepdefs {
    @Given("mitra signing in as {string}")
    public void mitraSigningInAs(String arg0) {
        apps.signin()
    }
}
