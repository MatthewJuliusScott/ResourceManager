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

public class HttpServletRequestDecorator implements HttpServletRequest {

	private HttpServletRequest request;

	public HttpServletRequestDecorator(HttpServletRequest request) {
		this.request = request;
	}

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

	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		return request.authenticate(response);
	}

	@Override
	public String changeSessionId() {
		return request.changeSessionId();
	}

	@Override
	public AsyncContext getAsyncContext() {
		return request.getAsyncContext();
	}

	@Override
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return request.getAttributeNames();
	}

	@Override
	public String getAuthType() {
		return request.getAuthType();
	}

	@Override
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		return request.getContentLength();
	}

	@Override
	public long getContentLengthLong() {
		return request.getContentLengthLong();
	}

	@Override
	public String getContentType() {
		return request.getContentType();
	}

	@Override
	public String getContextPath() {
		return request.getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		return request.getCookies();
	}

	@Override
	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	@Override
	public DispatcherType getDispatcherType() {
		return request.getDispatcherType();
	}

	@Override
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return request.getHeaderNames();
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		return request.getHeaders(name);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * Gets an int from the request parameters or attributes if possible, or 0
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
	 * Gets an integer from the request parameters or attributes if possible, or null
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

	@Override
	public int getIntHeader(String name) {
		return request.getIntHeader(name);
	}

	@Override
	public String getLocalAddr() {
		return request.getLocalAddr();
	}

	@Override
	public Locale getLocale() {
		return request.getLocale();
	}

	@Override
	public Enumeration<Locale> getLocales() {
		return request.getLocales();
	}

	@Override
	public String getLocalName() {
		return request.getLocalName();
	}

	@Override
	public int getLocalPort() {
		return request.getLocalPort();
	}

	@Override
	public String getMethod() {
		return request.getMethod();
	}

	@Override
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	@Override
	public Part getPart(String name) throws IOException, ServletException {
		return request.getPart(name);
	}

	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		return request.getParts();
	}

	@Override
	public String getPathInfo() {
		return request.getPathInfo();
	}

	@Override
	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	@Override
	public String getProtocol() {
		return request.getProtocol();
	}

	@Override
	public String getQueryString() {
		return request.getQueryString();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	@Override
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	@Override
	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	@Override
	public int getRemotePort() {
		return request.getRemotePort();
	}

	@Override
	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	@Override
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	@Override
	public String getRequestURI() {
		return request.getRequestURI();
	}

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
	 * @throws Exception
	 *             the exception
	 */
	public int getRequiredInt(String parameter) throws NumberFormatException {
		return Integer.valueOf(getString(parameter)).intValue();
	}

	/**
	 * Gets the required string.
	 *
	 * @param param
	 *            the param
	 * @param message
	 *            the message
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

	@Override
	public String getScheme() {
		return request.getScheme();
	}

	@Override
	public String getServerName() {
		return request.getServerName();
	}

	@Override
	public int getServerPort() {
		return request.getServerPort();
	}

	@Override
	public ServletContext getServletContext() {
		return request.getServletContext();
	}

	@Override
	public String getServletPath() {
		return request.getServletPath();
	}

	@Override
	public HttpSession getSession() {
		return request.getSession();
	}

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

	@Override
	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	@Override
	public boolean isAsyncStarted() {
		return request.isAsyncStarted();
	}

	@Override
	public boolean isAsyncSupported() {
		return request.isAsyncSupported();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	@Override
	public boolean isSecure() {
		return request.isSecure();
	}

	@Override
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	@Override
	public void login(String username, String password) throws ServletException {
		request.login(username, password);
	}

	@Override
	public void logout() throws ServletException {
		request.logout();
	}

	@Override
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	@Override
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		request.setCharacterEncoding(env);
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return request.startAsync();
	}

	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
		return request.startAsync(servletRequest, servletResponse);
	}

	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass)
		throws IOException, ServletException {
		return request.upgrade(httpUpgradeHandlerClass);
	}

}
