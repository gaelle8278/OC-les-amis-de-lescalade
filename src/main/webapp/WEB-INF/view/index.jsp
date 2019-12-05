<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	
	<title>Les amis de l'escalade</title>
</head>
<body>
	<header>
		<div class="container">
			<div class="row">
				<div class="col">
					<!--  session dependant : link adapt if user connected and member -->
					<a class="btn btn-primary float-md-right" href="<%=application.getContextPath()%>/acces-compte">Accéder à mon compte</a>
				</div>
			</div>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarText" aria-controls="navbarText"
						aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarText">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active"><a class="nav-link"
								href="<%=application.getContextPath()%>/les-sites">Les sites d'escalade</a></li>
						<li class="nav-item"><a class="nav-link"
								href="<%=application.getContextPath()%>/recherche-site">Trouver un site</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</header>
	<main role="main">
	    <div class="container">
	    	<div class="row">
			    <div class="col">
			    	<h1>Page d'accueil</h1>
	            	<h2>${title}</h2>
	            	<div>
			      		Carrousel des derniers sites ajoutés ?
			      	</div>
			      	<div>${user.pseudo}</div>
			      	<p><c:out value="${ user.pseudo }" /></p>
			    </div>
			</div>
			<div class="row">
		    	<div class="col-sm text-center">
			      <a class="btn btn-primary" href="<%=application.getContextPath()%>/les-sites">Les sites d'escalade</a>
			    </div>
			    <div class="col-sm text-center">
			      <a class="btn btn-primary" href="<%=application.getContextPath()%>/recherche-site">Trouver un site</a>
			    </div>
	  		</div>
	  	</div>
	</main>
	<footer class="container">
	</footer>
    <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</body>
</html>