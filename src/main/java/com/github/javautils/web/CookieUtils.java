package com.github.javautils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtils {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Get cookie value by cookie name.
     */
	public String getCookie(String name, String defaultValue) {
		Cookie cookie = getCookieObject(name);
		return cookie != null ? cookie.getValue() : defaultValue;
	}

	/**
	 * Get cookie value by cookie name.
	 */
	public String getCookie(String name) {
		return getCookie(name, null);
	}

	/**
	 * Get cookie value by cookie name and convert to Integer.
	 */
	public Integer getCookieToInt(String name) {
		String result = getCookie(name);
		return result != null ? Integer.parseInt(result) : null;
	}

	/**
	 * Get cookie value by cookie name and convert to Integer.
	 */
	public Integer getCookieToInt(String name, Integer defaultValue) {
		String result = getCookie(name);
		return result != null ? Integer.parseInt(result) : defaultValue;
	}

	/**
	 * Get cookie value by cookie name and convert to Long.
	 */
	public Long getCookieToLong(String name) {
		String result = getCookie(name);
		return result != null ? Long.parseLong(result) : null;
	}

	/**
	 * Get cookie value by cookie name and convert to Long.
	 */
	public Long getCookieToLong(String name, Long defaultValue) {
		String result = getCookie(name);
		return result != null ? Long.parseLong(result) : defaultValue;
	}

	/**
	 * Get cookie object by cookie name.
	 */
	public Cookie getCookieObject(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}

	/**
	 * Get all cookie objects.
	 */
	public Cookie[] getCookieObjects() {
		Cookie[] result = request.getCookies();
		return result != null ? result : new Cookie[0];
	}

	/**
	 * Set Cookie.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
	 */
	public void setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
	}

	/**
	 * Set Cookie.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 */
	public void setCookie(String name, String value, int maxAgeInSeconds) {
		doSetCookie(name, value, maxAgeInSeconds, null, null, null);
	}

	/**
	 * Set Cookie to response.
	 */
	public void setCookie(HttpServletResponse response, Cookie cookie) {
		response.addCookie(cookie);
	}

	/**
	 * Set Cookie to response.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 * @param path see Cookie.setPath(String)
	 * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
	 */
	public void setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
	}

	/**
	 * Set Cookie to response.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 * @param path see Cookie.setPath(String)
	 */
	public void setCookie(String name, String value, int maxAgeInSeconds, String path) {
		doSetCookie(name, value, maxAgeInSeconds, path, null, null);
	}

	/**
	 * Set Cookie to response.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 * @param path see Cookie.setPath(String)
	 * @param domain the domain name within which this cookie is visible; form is according to RFC 2109
	 * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
	 */
	public void setCookie(String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
		doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
	}

	/**
	 * Remove Cookie.
	 */
	public void removeCookie(String name) {
		doSetCookie(name, null, 0, null, null, null);
	}

	/**
	 * Remove Cookie.
	 */
	public void removeCookie(String name, String path) {
		doSetCookie(name, null, 0, path, null, null);
	}

	/**
	 * Remove Cookie.
	 */
	public void removeCookie(String name, String path, String domain) {
		doSetCookie(name, null, 0, path, domain, null);
	}

	private void doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAgeInSeconds);
		// set the default path value to "/"
		if (path == null) {
			path = "/";
		}
		cookie.setPath(path);

		if (domain != null) {
			cookie.setDomain(domain);
		}
		if (isHttpOnly != null) {
			cookie.setHttpOnly(isHttpOnly);
		}
		response.addCookie(cookie);
	}

}