<%@ page import="java.util.List" %>
<%@ page import="com.zsm.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: 20620
  Date: 2024-12-26
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-BookMan</title>
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico">
    <style>
        .container {
            min-height: 100vh;
        }
    </style>
</head>
<body>
<jsp:include page="../components/Navbar.jsp"/>

<div class="container" style="padding-top: 80px">
    <div class="page-content">
        <h3>欢迎使用E-BookMan电子书籍管理系统~😘</h3>
        <div class="card">
            <div class="card-header">
                <div class="search-form">
                    <form action="${pageContext.request.contextPath}/list">
                        <div class="row">
                            <input type="hidden" name="currentPage" id="currentPageInput" value="1">
                            <div class="col">
                                <input type="text" name="name" class="form-control" placeholder="书名"
                                       aria-label="书名" value="${sessionScope.name}">
                            </div>
                            <div class="col">
                                <input type="text" name="isbn" class="form-control" placeholder="ISBN号"
                                       aria-label="ISBN号" value="${sessionScope.isbn}">
                            </div>
                            <div class="col">
                                <input type="text" name="category" class="form-control" placeholder="分类"
                                       aria-label="分类" value="${category}">
                            </div>
                            <div class="col">
                                <input type="text" name="publisher" class="form-control" placeholder="出版社"
                                       aria-label="出版社" value="${sessionScope.publisher}">
                            </div>
                            <div class="col-1">
                                <button type="submit" class="btn btn-primary">搜索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="card-body">
                <div class="content-table">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th scope="col">序号</th>
                            <th scope="col">ISBN</th>
                            <th scope="col">书名</th>
                            <th scope="col">分类</th>
                            <th scope="col">出版社</th>
                            <th scope="col">价格</th>
                            <th scope="col">备注</th>
                            <th scope="col">操作</th>
                        </tr>
                        </thead>
                        <tbody class="table-group-divider">
                        <%
                            List<Book> books = (List<Book>) session.getAttribute("bookList");
                            if (books == null) return;
                            for (int i = 0; i < books.size(); i++) {
                                Book book = books.get(i);
                        %>
                        <tr>
                            <th scope="row"><%=i + 1%>
                            </th>
                            <td><%=book.getIsbn()%>
                            </td>
                            <td><%=book.getName()%>
                            </td>
                            <td><span class="badge text-bg-secondary"><%=book.getCategory()%></span></td>
                            <td><%=book.getPublisher()%>
                            </td>
                            <td><span class="badge text-bg-success"><%=book.getPrice()%></span></td>
                            <td><%=book.getRemarks() == null ? "无" : book.getRemarks()%>
                            </td>
                            <td>
                                <button type="button" class="btn btn-outline-primary btn-sm" onclick="detail(<%=book.getId()%>)">详情</button>
                                <button type="button" class="btn btn-outline-danger btn-sm" onclick="deleteById(<%=book.getId()%>)">删除</button>
                            </td>
                        </tr>
                        <%
                            }
                        %>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer">
                <nav aria-label="Page navigation example ">
                    <ul class="pagination justify-content-end">
                        <li class="page-item"><span class="page-link">总条数：${count}</span></li>
                        <% Integer count = (Integer) session.getAttribute("count");
                            int currentPage = 1;
                            String curPage = (String) session.getAttribute("currentPage");
                            if (curPage != null) currentPage = Integer.parseInt(curPage);
                            if (currentPage != 1) { %>

                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/list?currentPage=${currentPage - 1}&name=${name}&category=${category}&publisher=${publisher}&isbn=${isbn}">上一页</a>
                        </li>
                        <% } %>

                        <% int cnt = count / 10;
                            if (count % 10 != 0) cnt++;
                            int startPage = currentPage - 2;
                            int endPage = currentPage + 2;
                            for (int i = startPage; i <= endPage; i++) {
                                if (i >= 1 && i <= cnt) {%>

                        <li class="page-item"><a class="page-link <%=currentPage == i ? "active" : ""%>"
                                                 href="${pageContext.request.contextPath}/list?currentPage=<%=i%>&name=${name}&category=${category}&publisher=${publisher}&isbn=${isbn}"><%=i%>
                        </a></li>
                        <% }} %>

                        <% if (currentPage != cnt) { %>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/list?currentPage=${currentPage + 1}&name=${name}&category=${category}&publisher=${publisher}&isbn=${isbn}">下一页</a>
                        </li>
                        <% } %>

                    </ul>
                </nav>
            </div>
        </div>
    </div>

</div>
<jsp:include page="../components/Footerbar.jsp"/>
</body>
</html>

<script>
    function deleteById(id) {
        const confirmed = confirm("您确定要删除吗？");
        if (confirmed) {
            location.href = "/delete?id=" + id;
        }
    }
    function detail(id) {
        location.href = "/detail?id=" + id;
    }
</script>