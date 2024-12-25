package com.cm.cm2.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {

//       private static final String BaseURL="http://localhost:8080";
    private static final String BaseURL="https://www.soumojitmakar.site";
    public static String getEmailOfLoginUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof OAuth2AuthenticatedPrincipal oAuth2AuthenticatedPrincipal) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from Google");
                return oAuth2AuthenticatedPrincipal.getAttribute("email");
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from GitHub");
                String email = oAuth2AuthenticatedPrincipal.getAttribute("email");
                String login = oAuth2AuthenticatedPrincipal.getAttribute("login");
                return email != null ? email : login + "@gmail.com";
            }
        } else if (principal instanceof UserDetails userDetails) {
            System.out.println("Getting email from regular authentication");
            return userDetails.getUsername();
        } else {
            System.out.println("Unknown principal type. ");
        }

        return "";
    }

    public static String getLinkForEmailVarification(String emailToken) {

         String link =BaseURL+"/auth/varify-email?token=" + emailToken;
        String emailBody="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Verify Your Email</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f9;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            background: #ffffff;\n" +
                "            border-radius: 8px;\n" +
                "            overflow: hidden;\n" +
                "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .email-header {\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .email-body {\n" +
                "            padding: 20px;\n" +
                "            color: #333333;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .email-body h1 {\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .email-body p {\n" +
                "            margin: 10px 0;\n" +
                "        }\n" +
                "        .verify-button {\n" +
                "            display: inline-block;\n" +
                "            margin-top: 20px;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 4px;\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "        .verify-button:hover {\n" +
                "            background-color: #45a049;\n" +
                "        }\n" +
                "        .email-footer {\n" +
                "            background-color: #f4f4f9;\n" +
                "            text-align: center;\n" +
                "            padding: 10px;\n" +
                "            font-size: 12px;\n" +
                "            color: #777777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <div class=\"email-header\">\n" +
                "            <h1>Contact-Manager</h1>\n" +
                "        </div>\n" +
                "        <div class=\"email-body\">\n" +
                "            <h1>Verify Your Email</h1>\n" +
                "            <p>Thank you for signing up for Contact-Manager! To get started, we need to verify your email address.</p>\n" +
                "            <p>Click the button below to verify your email:</p>\n" +
                "            <a href="+link+" class=\"verify-button\">Verify Email</a>\n" +
                "            <p>If you did not sign up for Contact-Manager, please ignore this email.</p>\n" +
                "        </div>\n" +
                "        <div class=\"email-footer\">\n" +
                "            <p>&copy; 2024 Contact-Manager. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";

        return emailBody;
    }

    public static String getLinkForForgetPasswoed(String Token) {

         String link = BaseURL+"/auth/changePassword?token=" + Token;
//
        String linkBody="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Reset Your Password</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f9;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            background: #ffffff;\n" +
                "            border-radius: 8px;\n" +
                "            overflow: hidden;\n" +
                "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .email-header {\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .email-body {\n" +
                "            padding: 20px;\n" +
                "            color: #333333;\n" +
                "            line-height: 1.6;\n" +
                "        }\n" +
                "        .email-body h1 {\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .email-body p {\n" +
                "            margin: 10px 0;\n" +
                "        }\n" +
                "        .reset-button {\n" +
                "            display: inline-block;\n" +
                "            margin-top: 20px;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #4CAF50;\n" +
                "            color: white;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 4px;\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "        .reset-button:hover {\n" +
                "            background-color: #45a049;\n" +
                "        }\n" +
                "        .email-footer {\n" +
                "            background-color: #f4f4f9;\n" +
                "            text-align: center;\n" +
                "            padding: 10px;\n" +
                "            font-size: 12px;\n" +
                "            color: #777777;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <div class=\"email-header\">\n" +
                "            <h1>Contact-Manager</h1>\n" +
                "        </div>\n" +
                "        <div class=\"email-body\">\n" +
                "            <h1>Reset Your Password</h1>\n" +
                "            <p>We received a request to reset your password for your Contact-Manager account. If you made this request, click the button below to reset your password:</p>\n" +
                "            <a href=\""+link+"\" class=\"reset-button\">Reset Password</a>\n"+
                "            <p>If you did not request a password reset, you can safely ignore this email. Your password will remain unchanged.</p>\n" +
                "        </div>\n" +
                "        <div class=\"email-footer\">\n" +
                "            <p>&copy; 2024 Contact-Manager. All rights reserved.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        return linkBody;
    }
}
