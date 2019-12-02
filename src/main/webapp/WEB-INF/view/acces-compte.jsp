<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
		<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
			rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<title>${title}</title>
	</head>
	<body>
		<header>
			<div class="container">
				<div class="row">
					<div class="col">
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
						<h1>Accès à mon compte</h1>
					</div>
				</div>
			</div>
		</main>
		<footer class="container"> </footer>
		<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	</body>
</html>