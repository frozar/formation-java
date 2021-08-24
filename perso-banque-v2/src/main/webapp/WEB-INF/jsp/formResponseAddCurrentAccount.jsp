<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Réception nouveau compte</title></head>
<body>
<h2>Réception nouveau compte</h2>
 <br />  
Nom : <%= request.getParameter("nom") %> 
<p></p> 
Prénom: <%= request.getParameter("prenom") %> 
<p></p>  
Code guichet : <%= request.getParameter("codeGuichet") %> 
<p></p>  
N° de compte : <%= request.getParameter("numCompte") %>
<p></p>  
Clé : <%= request.getParameter("cle") %> 
<p></p>  
Solde : <%= request.getParameter("solde") %>
<br /> 
Param: ${param}
<br /> 
<p></p>
<a href="/">Home</a>
</body>
</html>