<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Welcome</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .m-r-l{
            margin: 0px;
        }
    </style>
</head>
<body>

<div class="row m-r-l">
    <div class="col-1"></div>
    <div class="col-10">
        <table class="table table-bordered">
            <thead>

            <tr>
                <th scope="col">id</th>
                <th scope="col">title</th>
                <th scope="col">key</th>
                <th scope="col">time</th>
                <th scope="col">group-title</th>
                <th scope="col">deleted_at</th>
                <th scope="col">questions</th>
                <th colspan="4">answers</th>
            </tr>
            </thead>
            <tbody>



            <c:forEach var="item" items="${tests}">
            <c:set var="qSize" scope="page" value="${item.questions.size()+1}"/>

            <tr>
                <th scope="row" rowspan="11"><c:out value="${item.id}"/></th>
                <td rowspan="${qSize}">${item.title}</td>
                <td rowspan="${qSize}">${item.key}</td>
                <td rowspan="${qSize}">${item.time}</td>
                <td rowspan="${qSize}">${item.testGroup.groupTitle}</td>
                <td rowspan="${qSize}">${item.deletedAt}</td>
                <c:forEach var="quest" items="${item.questions}">
            <tr>
                <td>${quest.questionItem}</td>
                <c:forEach var="answ" items="${quest.answers}">
                    <td>${answ.answerItem}</td>

                </c:forEach>
            </tr>
            </c:forEach>
            </tr>

            </tbody>
            </c:forEach>

        </table>
        <form action="xml-parser" method="POST" role="form">
            <input type="hidden" name="command" value="start_page"/>
            <div class="form-group">
                <div class="row">
                    <div class="col-sm-offset-2 col-sm-10 p-top-7">
                        <button type="submit" class="card-exe-btn btn btn-outline-primary">Назад</button>
                    </div>
                </div>
            </div>

        </form>

    </div>
    <div class="col-1"></div>

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