package dev.hltech.alexaauthmock;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

/**
 * @author Mateusz Urbański <matek2305@gmail.com>.
 */
@RestController
public class AuthController {

    private static final String TOKEN_TYPE = "Bearer";
    private static final int ACCESS_TOKEN_LENGTH = 16;

    private final RandomStringGenerator accessCodeGenerator = new RandomStringGenerator.Builder()
        .withinRange('0', 'z')
        .filteredBy(LETTERS, DIGITS)
        .build();

    @GetMapping("/auth")
    public void hello(
        HttpServletResponse response,
        @RequestParam("state") String state,
        @RequestParam("redirect_uri") String redirectUri) throws IOException {

        final String targetUrl = buildRedirectUri(redirectUri, state);
        System.out.println("Redirecting to: " + targetUrl);
        response.sendRedirect(targetUrl);
    }

    private String buildRedirectUri(String redirectUri, String state) {
        return UriComponentsBuilder.fromHttpUrl(redirectUri)
            .queryParam("state", state)
            .queryParam("access_code", accessCodeGenerator.generate(ACCESS_TOKEN_LENGTH))
            .queryParam("token_type", TOKEN_TYPE)
            .build()
            .toString();
    }
}
