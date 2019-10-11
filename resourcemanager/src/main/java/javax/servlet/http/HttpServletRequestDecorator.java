/*
 *
 */
package javax.servlet.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * HttpServletRequest Decorator pattern for convenience. Customer methods are for extracting parameters from the request.
 */
public class HttpServletRequestDecorator implements HttpServletRequest {

	/** The request. */
	private HttpServletRequest request;

	/**
	 * Instantiates a new http servlet request decorator.
	 *
	 * @param request
	 *            the request
	 */
	public HttpServletRequestDecorator(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Adds the message.
	 *
	 * @param string
	 *            the string
	 */
	@SuppressWarnings("unchecked")
	public void addMessage(String string) {
		HttpSession session = request.getSession();
		LinkedList<String> messages;
		if (session.getAttribute("messages") != null && session.getAttribute("messages") instanceof LinkedList<?>) {
			messages = (LinkedList<String>) session.getAttribute("messages");
		} else {
			messages = new LinkedList<String>();
		}
		messages.add(string);
		session.setAttribute("messages", messages);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#authenticate(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return request.authenticate(response);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#changeSessionId()
	 */
	@Override
	public String changeSessionId() {
		return request.changeSessionId();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAsyncContext()
	 */
	@Override
	public AsyncContext getAsyncContext() {
		return request.getAsyncContext();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAttributeNames()
	 */
	@Override
	public Enumeration<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getAuthType()
	 */
	@Override
	public String getAuthType() {
		return request.getAuthType();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getCharacterEncoding()
	 */
	@Override
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getContentLength()
	 */
	@Override
	public int getContentLength() {
		return request.getContentLength();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getContentLengthLong()
	 */
	@Override
	public long getContentLengthLong() {
		return request.getContentLengthLong();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getContentType()
	 */
	@Override
	public String getContentType() {
		return request.getContentType();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getContextPath()
	 */
	@Override
	public String getContextPath() {
		return request.getContextPath();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getCookies()
	 */
	@Override
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getDateHeader(java.lang.String)
	 */
	@Override
	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getDispatcherType()
	 */
	@Override
	public DispatcherType getDispatcherType() {
		return request.getDispatcherType();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeaderNames()
	 */
	@Override
	public Enumeration<String> getHeaderNames() {
		return request.getHeaderNames();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getHeaders(java.lang.String)
	 */
	@Override
	public Enumeration<String> getHeaders(String name) {
		return request.getHeaders(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getInputStream()
	 */
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * Gets an int from the request parameters or attributes if possible, or 0.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the int
	 */
	public int getInt(String parameter) {
		Integer integer = getInteger(parameter);
		return integer != null ? integer.intValue() : 0;
	}

	/**
	 * Gets an integer from the request parameters or attributes if possible, or null.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the integer
	 */
	public Integer getInteger(String parameter) {
		Integer integer = null;
		try {
			integer = Integer.valueOf(request.getParameter(parameter));
		} catch (NumberFormatException nfe) {
			integer = (Integer) request.getAttribute(parameter);
		}
		return integer;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getIntHeader(java.lang.String)
	 */
	@Override
	public int getIntHeader(String name) {
		return request.getIntHeader(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalAddr()
	 */
	@Override
	public String getLocalAddr() {
		return request.getLocalAddr();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocale()
	 */
	@Override
	public Locale getLocale() {
		return request.getLocale();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocales()
	 */
	@Override
	public Enumeration<Locale> getLocales() {
		return request.getLocales();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalName()
	 */
	@Override
	public String getLocalName() {
		return request.getLocalName();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getLocalPort()
	 */
	@Override
	public int getLocalPort() {
		return request.getLocalPort();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getMethod()
	 */
	@Override
	public String getMethod() {
		return request.getMethod();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterMap()
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterNames()
	 */
	@Override
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
	 */
	@Override
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPart(java.lang.String)
	 */
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return request.getPart(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getParts()
	 */
	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return request.getParts();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPathInfo()
	 */
	@Override
	public String getPathInfo() {
		return request.getPathInfo();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPathTranslated()
	 */
	@Override
	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getProtocol()
	 */
	@Override
	public String getProtocol() {
		return request.getProtocol();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getQueryString()
	 */
	@Override
	public String getQueryString() {
		return request.getQueryString();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getReader()
	 */
	@Override
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
	 */
	@Override
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemoteAddr()
	 */
	@Override
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemoteHost()
	 */
	@Override
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRemotePort()
	 */
	@Override
	public int getRemotePort() {
		return request.getRemotePort();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRemoteUser()
	 */
	@Override
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
	 */
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestedSessionId()
	 */
	@Override
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestURI()
	 */
	@Override
	public String getRequestURI() {
		return request.getRequestURI();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getRequestURL()
	 */
	@Override
	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	/**
	 * Gets an int from the request parameters or attributes, throws an NumberFormatException if not possible.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the int
	 * @throws NumberFormatException
	 *             the number format exception
	 */
	public int getRequiredInt(String parameter) throws NumberFormatException {
		return Integer.valueOf(getString(parameter)).intValue();
	}

	/**
	 * Gets the required string.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the required string
	 */
	public String getRequiredString(String parameter) {
		String value = getString(parameter);
		if (StringUtils.isNotBlank(value)) {
			return value;
		} else {
			throw new IllegalArgumentException("parameter was not set");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getScheme()
	 */
	@Override
	public String getScheme() {
		return request.getScheme();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServerName()
	 */
	@Override
	public String getServerName() {
		return request.getServerName();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServerPort()
	 */
	@Override
	public int getServerPort() {
		return request.getServerPort();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServletContext()
	 */
	@Override
	public ServletContext getServletContext() {
		return request.getServletContext();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getServletPath()
	 */
	@Override
	public String getServletPath() {
		return request.getServletPath();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getSession()
	 */
	@Override
	public HttpSession getSession() {
		return request.getSession();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getSession(boolean)
	 */
	@Override
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * Gets a string from the request parameters or attributes, or a blank string if null.
	 *
	 * @param parameter
	 *            the parameter
	 * @return the string
	 */
	public String getString(String parameter) {
		String string = request.getParameter(parameter);
		if (string == null) {
			string = (String) request.getAttribute(parameter);
			if (string == null) {
				return "";
			}
		}
		return string;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getUserPrincipal()
	 */
	@Override
	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isAsyncStarted()
	 */
	@Override
	public boolean isAsyncStarted() {
		return request.isAsyncStarted();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isAsyncSupported()
	 */
	@Override
	public boolean isAsyncSupported() {
		return request.isAsyncSupported();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromCookie()
	 */
	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromUrl()
	 */
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdFromURL()
	 */
	@Override
	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isRequestedSessionIdValid()
	 */
	@Override
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isSecure()
	 */
	@Override
	public boolean isSecure() {
		return request.isSecure();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#isUserInRole(java.lang.String)
	 */
	@Override
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#login(java.lang.String, java.lang.String)
	 */
	@Override
	public void login(String username, String password) throws ServletException {
		request.login(username, password);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#logout()
	 */
	@Override
	public void logout() throws ServletException {
		request.logout();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
	 */
	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		request.setCharacterEncoding(env);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#startAsync()
	 */
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return request.startAsync();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.ServletRequest#startAsync(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
		return request.startAsync(servletRequest, servletResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#upgrade(java.lang.Class)
	 */
	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
		throws IOException, ServletException {
		return request.upgrade(httpUpgradeHandlerClass);
	}

}
