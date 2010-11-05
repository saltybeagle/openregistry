<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>
<%--

	Licensed to Jasig under one or more contributor license
	agreements. See the NOTICE file distributed with this work
	for additional information regarding copyright ownership.
	Jasig licenses this file to you under the Apache License,
	Version 2.0 (the "License"); you may not use this file
	except in compliance with the License. You may obtain a
	copy of the License at:

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on
	an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
	KIND, either express or implied. See the License for the
	specific language governing permissions and limitations
	under the License.

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	SecurityContext context = SecurityContextHolder.getContext();
	Authentication auth = context.getAuthentication();
%>

<div id="or-status-bar-left">Welcome <%= ((User)auth.getPrincipal()).getUsername() %></div>
<div id="or-status-bar-right"><a href="<c:url value="/logout.htm" />">Logout</a></div>
