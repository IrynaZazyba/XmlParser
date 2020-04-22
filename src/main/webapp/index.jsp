<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Welcome</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<div class="row">
    <div class="col-4"></div>
    <div class="col-3">

        <c:if test="${not empty requestScope.invalid_xml_message}">
            <div class="alert alert-danger" role="alert">
           <c:out value="${requestScope.invalid_xml_message}"/>
            </div>
        </c:if>
        <form action="xml-parser" method="POST" role="form" enctype="multipart/form-data">
            <input type="hidden" name="command" value="parse_xml">
            <div class="form-group">
                <label for="exampleFormControlFile1">Загрузите xml-файл</label>
                <input type="file" name="myfile" class="form-control-file" required id="exampleFormControlFile1">
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="type" id="SAX" value="sax" checked>
                <label class="form-check-label" for="SAX">
                    SAX
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="type" id="StAX" value="stax">
                <label class="form-check-label" for="StAX">
                    StAX
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="type" id="DOM" value="dom">
                <label class="form-check-label" for="DOM">
                    DOM
                </label>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-sm-offset-2 col-sm-10 p-top-7">
                        <button type="submit" class="card-exe-btn btn btn-outline-primary">Запустить
                        </button>
                    </div>
                </div>
            </div>

        </form>

    </div>
    <div class="col-4"></div>

</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

</body>
</html>