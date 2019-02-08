<%@ page import="metube.domain.models.view.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- The Link below gives functionality to import other templates -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% TubeDetailsViewModel tubeDetailsViewModel =
        (TubeDetailsViewModel) request.getAttribute("tubeDetailsViewModel");%>
    <div class="container">
        <main>
            <div class="jumbotron">
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h1><%=tubeDetailsViewModel.getName()%>
                        </h1>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <h3><%=tubeDetailsViewModel.getDescription()%></h3>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col col-md-6 d-flex justify-content-center">
                        <a href="<%=tubeDetailsViewModel.getYouTubeLink()%>">Link to video</a>
                    </div>
                    <div class="col col-md-6 d-flex justify-content-center">
                        <p><%=tubeDetailsViewModel.getUploader()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col col-md-12 d-flex justify-content-center">
                        <a href="/tubes/all">Back to all Tubes.</a>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <footer><c:import url="templates/footer.jsp"/></footer>
</body>
</html>
