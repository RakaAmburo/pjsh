<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/personas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<link type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/css/personas.css" rel="stylesheet">
</head>
<body>
     <div id="errorMsg" class="center-block" style="visibility:hidden; padding-top: 4px; background-color: #f9edbe; border: 1px solid; border-color: #f0c36d;  display:block ; text-align: center; width: 200px; height: 30px;  font-weight: bold;">Connection problem</div>
     <div style="padding-left: 30px">
		 <h1>ABM... from <s:property value="%{nameUser}"></s:property></h1>
	
		 <i class="icon-signout"></i>&nbsp;<a href="${pageContext.request.contextPath}/j_spring_security_logout" style="text-decoration: none;">Logout</a>
		 <div style="height: 10px"></div>
		<input type="hidden" id="orderChanged" value="false">
	</div>
	<div style="padding-left: 30px; padding-right: 30px">
		<table class="table table-bordered" >
	       <thead>
	           <tr>
	               <th style="text-align: center;padding: 6px; width: 60px"><s:text name="headers.orden"></s:text></th>
	               <th style="text-align: center;padding: 8px;"><s:text name="headers.nombre"></s:text></th>
	               <th style="text-align: center;padding: 8px;"><s:text name="headers.apellido"></s:text></th>
	               <th style="text-align: center;padding: 8px;width: 8em;"><s:text name="headers.telefono"></s:text></th>
	               <th style="text-align: center;padding: 8px;width: 10em;"><s:text name="headers.pais"></s:text></th>
	               <th style="text-align: center;padding: 8px;width: 3em;" colspan="1" ><s:text name="headers.more"></s:text></th>
	               <th style="text-align: center;padding: 8px;" colspan="2" ><s:text name="headers.saveDel"></s:text></th>
	               <th style="text-align: center;padding: 8px;" colspan="1" ><s:text name="headers.add"></s:text></th>
	               <th style="text-align: center;padding: 8px;" colspan="1" ><s:text name="headers.move"></s:text></th>
	           </tr>
	    </thead>
	    <tbody id="tabla">
	        
	        <s:iterator status="stat" value="personas">
	            <tr id="line">
	                <!--td style="padding: 10px" class="orden"><s:property value="#stat.index+1"></s:property></td-->
	                <td style="vertical-align: middle; text-align: center;" class="orden"><s:property value="%{orden}"></s:property></td>
	                <td style="vertical-align: middle;"><input name="nombre" style="border: none;width: 100%;" type="text" value="<s:property value="%{nombre}"></s:property>"></td>
	                <td style="vertical-align: middle;"><input name="apellido" style="border: none;width: 100%;" type="text" value="<s:property value="%{apellido}"></s:property>"></td>
	                <td style="vertical-align: middle;"><input name="telefono" class="telefono"  style="border: none;width: 100%;" type="text" value="<s:property value="%{telefono}"></s:property>"></td>
	                <td style="vertical-align: middle;"><input class="pais" name="pais"  style="border: none;width: 100%;" type="text" value="<s:property value="%{country.name}"></s:property>"></td>
	                <td style="vertical-align: middle;" class="text-center">
	                  
	                 <button type="button" class="openMore"><i class="icon-list-ol"></i></button>
	                 <div style="display: none" class="moreOptions">
	                 	<table>
						  <tr>
						    <td class="text-left"><s:text name="headers.direccion"></s:text>:&nbsp;&nbsp;&nbsp;</td>
						    <td><input name="direccion"  style="border: none;width: 100%;" type="text" value="<s:property value="%{direccion}"></s:property>"></td>
						  </tr>
						  <tr>
						    <td class="text-left"><s:text name="headers.cpostal"></s:text>:&nbsp;&nbsp;&nbsp;</td>
						    <td><input name="personaExt.codigoPostal"  style="border: none;width: 100%;" type="text" value="<s:property value="%{personaExt.codigoPostal}"></s:property>"></td>
						  </tr>
						  <tr>
						    <td class="text-left"><s:text name="headers.edad"></s:text>:&nbsp;&nbsp;&nbsp;</td>
						    <td><input name="personaExt.edad" maxlength="3"  style="border: none;width: 100%;" type="text" value="<s:property value="%{personaExt.edad}"></s:property>"></td>
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
	                <td style="width: 90px;" class="text-center">
	                   <span><button type="button" class="mvUp btn btn-primary btn-sm"><i class="icon-double-angle-up"></i></button></span>
	                   <span><button type="button" class="mvDown btn btn-primary btn-sm"><i class="icon-double-angle-down"></i></button></span>
	                   <input type="hidden" class="id" name="id" value="<s:property value="%{id}"></s:property>">
	                   <input type="hidden" class="paisId" name="paisId" value="<s:property value="%{country.id}"></s:property>">
	                   <input type="hidden" class="personaExt_id" name="personaExt.id" value="<s:property value="%{id}"></s:property>">
	                   <input type="hidden" name="uniqueId" class="uniqueId" >
	                </td>
	            </tr>
	        </s:iterator>
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