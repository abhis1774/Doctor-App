package com.priscripto.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CookieUtil {

	 private static final boolean SECURE = true;  // Set to true in production
	    private static final boolean HTTP_ONLY = true;
	    private static final String PATH = "/";
	    private static final String SAME_SITE = "None"; // Set to "Lax" or "None" as needed
	    private static final Logger logger = Logger.getLogger(CookieUtil.class.getName());

	    public static ResponseCookie setCookie(HttpServletResponse response,String name, String value, long maxAgeMillis) {
	        //  Create ResponseCookie
	        ResponseCookie cookie = ResponseCookie.from(name, value)
	                .httpOnly(HTTP_ONLY)
	                .secure(SECURE)
	                .path(PATH)
	                .maxAge(maxAgeMillis / 1000) // Convert milliseconds to seconds
	                .sameSite(SAME_SITE) // Ensures cross-site security
	                //.domain("localhost")
	                .build();

	      
	        //  Convert expiration time to IST format for logging
	        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
	        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

	        long expiryTimestamp = System.currentTimeMillis() + maxAgeMillis;
	        String expiryDateIST = sdf.format(new Date(expiryTimestamp));

	        logger.info("Cookie '" + name + "' set with expiration at (IST): " + expiryDateIST);
	        response.addHeader("Set-Cookie", cookie.toString());
	        logger.info(cookie.toString());
	        return cookie;
	    }

    // Delete cookies
	    public static void deleteCookie(HttpServletResponse response, String name) {
	        ResponseCookie cookie = ResponseCookie.from(name, "")
	                .httpOnly(HTTP_ONLY)
	                .secure(SECURE)
	                .path(PATH)
	                .maxAge(0) // Expire immediately
	                .sameSite(SAME_SITE)
	                .build();

	        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString()); // Send the deletion cookie
	    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
