<%@ taglib prefix="s" uri="/struts-tags"%>
 
  
<s:actionerror />

<s:url id="formSubmit" value='/j_spring_security_check' />

<s:form action="%{formSubmit}" method="post">
<div style="margin: 0 auto;">

  
    <p>
        <s:label value="Usuario" />
        <s:textfield name="j_username" autocomplete="off" />
        <s:fielderror fieldName="j_username" />
    </p>
    <p>
        <s:label  value="Clave" />
        <s:password name="j_password" autocomplete="off" />
        <s:fielderror fieldName="j_password" />
    </p>
    <p>
        Ingresar
        
    </p>
    <p>
        <s:submit key="label.submit" value="Aceptar" name="submit" />
       
    </p>
</div>
</s:form>