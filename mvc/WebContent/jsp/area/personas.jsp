<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/plugins/jquery-ui-1.10.4.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/personas.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="/resources/js/bootstrap-tagsinput.js"></script>	
<script type="text/javascript" src="/resources/js/plugins/chosen/chosen.jquery.min.js"></script>
<link type="text/css" href="<c:url value="/resources/css/jquery-ui-1.10.3.custom.css" />" rel="stylesheet" />
<link type="text/css" href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link type="text/css" href="/resources/css/bootstrap-tagsinput.css" rel="stylesheet">
<link type="text/css" href="<c:url value="/resources/css/personas.css" />" rel="stylesheet">
<link href="/resources/js/plugins/chosen/chosen.min.css" rel="stylesheet" type="text/css">
<link type="text/css" href="/resources/css/bootstrap-theme.css"></link>
</head>
<body>
     <div id="errorMsg" class="center-block" style="visibility:hidden; padding-top: 4px; background-color: #f9edbe; border: 1px solid; border-color: #f0c36d;  display:block ; text-align: center; width: 200px; height: 30px;  font-weight: bold;">Connection problem</div>
     <div style="padding-left: 30px">
		 <h1>ABM... from ${nameUser}</h1>
	
		 <i class="icon-signout"></i>&nbsp;<a href="${pageContext.request.contextPath}/j_spring_security_logout" style="text-decoration: none;">Logout</a>
		 <div style="height: 10px"></div>
		<input type="hidden" id="orderChanged" value="false">
	</div>
	<div style="padding-left: 30px; padding-right: 30px">
		<table class="table table-bordered" >
	       <thead>
	           <tr>
	               <th style="text-align: center;padding: 6px; width: 60px">${hOrden}</th>
	               <th style="text-align: center;padding: 8px;">${hNombre}</th>
	               <th style="text-align: center;padding: 8px;">${hApellido}</th>
	               <%-- <th style="text-align: center;padding: 8px;width: 18em;">${hDir}</th> --%>
	               <th style="text-align: center;padding: 8px;width: 10em;">${hTel}</th>
	               <th style="text-align: center;padding: 8px;width: 10em;">${hPais}</th>
	               <%-- <th style="text-align: center;padding: 8px;width: 4em;">${hEdad}</th> --%>
	               <%-- <th style="text-align: center;padding: 9px;width: 8em;">${hCPost}</th> --%>
	               <th style="text-align: center;padding: 8px;" colspan="1" >${hMore}</th>
	               <th style="text-align: center;padding: 8px;" colspan="2" >${hSavDel}</th>
	               <th style="text-align: center;padding: 8px;" colspan="1" >${hAdd}</th>
	               <th style="text-align: center;padding: 8px;" colspan="1" >${hMove}</th>
	               
	                
	
	        </tr>
	    </thead>
	    <tbody id="tabla">
	        
	        <c:forEach items="${personas}" var="persona">
	            <tr id="line">
	                <!--td style="padding: 10px" class="orden"><s:property value="#stat.index+1"></s:property></td-->
	                <td style="vertical-align: middle; text-align: center;" class="orden">${persona.orden}</td>
	                <td style="vertical-align: middle;"><input name="nombre" style="border: none;width: 100%;" type="text" value="${persona.nombre}"></td>
	                <td style="vertical-align: middle;"><input name="apellido" style="border: none;width: 100%;" type="text" value="${persona.apellido}"></td>
	                <%-- <td style="vertical-align: middle;"><input name="direccion"  style="border: none;width: 100%;" type="text" value="${persona.direccion}"></td> --%>
	                <td style="vertical-align: middle;"><input name="telefono" class="telefono"  style="border: none;width: 100%;" type="text" value="${persona.telefono}"></td>
	                <td style="vertical-align: middle;"><input class="pais" name="pais"  style="border: none;width: 100%;" type="text" value="${persona.country.name}"></td>
	                <%-- <td style="vertical-align: middle;padding-left: 15px;padding-right: 15px"><input name="personaExt.edad" maxlength="3"  style="border: none;width: 100%;" type="text" value="${persona.personaExt.edad}"></td> --%>
	                <%-- <td style="vertical-align: middle;"><input name="personaExt.codigoPostal"  style="border: none;width: 100%;" type="text" value="${persona.personaExt.codigoPostal}"></td> --%>
	                <td style="vertical-align: middle;">
	                
	                    <!-- <button type="button" class="openMore"><i class="icon-list-ol"></i></button> -->
		                <div style="display: none" class="moreOptions">
		                 	<table style="width: 100%">
							  <tr>
							    <td class="text-left" width="100px"><s:text name="headers.direccion"></s:text>:&nbsp;&nbsp;&nbsp;</td>
							    <td><input name="direccion"  style="border: none;width: 100%;" type="text" value="${persona.direccion}"></td>
							  </tr>
							  <tr>
							    <td class="text-left"><s:text name="headers.cpostal"></s:text>:&nbsp;&nbsp;&nbsp;</td>
							    <td><input name="personaExt.codigoPostal"  style="border: none;width: 100%;" type="text" value="${persona.personaExt.codigoPostal}"></td>
							  </tr>
							  <tr>
							    <td class="text-left"><s:text name="headers.edad"></s:text>:&nbsp;&nbsp;&nbsp;</td>
							    <td><input name="personaExt.edad" maxlength="3"  style="border: none;width: 100%;" type="text" value="${persona.personaExt.edad}"></td>
							  </tr>
							  <tr>
							    <td class="text-left"><s:text name="label.languages"></s:text>:</td>
							    <td class="chosentd">
							    	<select id="perLangSelect" cssClass="chosen-select" style="width: 450px; display: hidden;" tabindex="-1"
							            data-placeholder="Choose a Language..."	list="" 
										multiple="true" theme="simple" name="languages"	value=""/>
							    </td>
							  </tr>
							  <tr>
							    <td class="text-left">Tags:</td>
							    <td class="boot-tag"><!-- PONGO DIRECTAMENTE EL SET HAY QUE INSTANCIARLO!! -->
								    							              
								              <select name="tags" id="tags" class="boot-tag-class" style="display: none;" multiple="multiple" data-role="tagsinput">
											<option selected="" value="test">test</option>
											</select>			    	
							    </td>
							  </tr>
							</table>
		                 </div>
		                 
	                </td>
	                <td style="width: 50px" class="text-center"><button type="button" name="btnSave" class="btnSave btn btn-primary btn-sm"><i class="icon-save"></i></button></td>
	                <td style="width: 50px" class="text-center"><button type="button" class="btnDel btn btn-primary btn-sm"><i class="icon-trash"></i></button></td>
	                <td style="width: 80px" class="text-center">
	                   <span><button type="button" class="addUp btn btn-primary btn-sm"><i class="icon-long-arrow-up"></i></button></span>
	                   <span><button type="button" class="addDown btn btn-primary btn-sm"><i class="icon-long-arrow-down"></i></button></span>
	                </td>
	                <td style="width: 90px; " class="text-center">
	                   <span><button type="button" class="mvUp btn btn-primary btn-sm"><i class="icon-double-angle-up"></i></button></span>
	                   <span><button type="button" class="mvDown btn btn-primary btn-sm"><i class="icon-double-angle-down"></i></button></span>
	                   <input type="hidden" class="id" name="id" value="${persona.id}">
	                   <input type="hidden" class="paisId" name="paisId" value="${persona.country.id}">
	                   <input type="hidden" class="personaExt_id" name="personaExt.id" value="${persona.id}">
	                   <input type="hidden" name="uniqueId" class="uniqueId" >
	                </td>
	                
	
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
</div>
<div style="padding-left: 30px">
	<input type="checkbox" id="noInternet" name="noInternet" value="1">&nbsp;&nbsp;<i class="icon-bug" ></i>&nbsp;No Internet 	
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<input type="text" id="queue" size="3" value="0" >&nbsp;&nbsp;<i class="icon-stackexchange"></i>&nbsp;Queue
</div>
</body>
</html>
