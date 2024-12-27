<%@ page import="com.zsm.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: 20620
  Date: 2024-12-27
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-BookMan-详情</title>
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
        <h3>查看书籍详情~😀</h3>
        <div class="card">
            <div class="card-body">
                <form>
                    <%
                        Book book = (Book) request.getAttribute("detailBook");
                        if (book == null) return;
                    %>
                    <fieldset disabled>
                        <div class="mb-3">
                            <label class="form-label">ISBN</label>
                            <input type="text" class="form-control" value="<%=book.getIsbn()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">名称</label>
                            <input type="text" class="form-control" value="<%=book.getName()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">分类</label>
                            <input type="text" class="form-control" value="<%=book.getCategory()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">出版社</label>
                            <input type="text" class="form-control"  value="<%=book.getPublisher()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">价格</label>
                            <input type="number" class="form-control" step="0.01" min="0" value="<%=book.getPrice()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">备注</label>
                            <textarea class="form-control" rows="3" ><%=book.getRemarks()%></textarea>
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">入库时间</label>
                            <input type="text" class="form-control"  value="<%=book.getCreateTime()%>">
                        </div>
                        <div class="mb-3">
                            <label  class="form-label">修改时间</label>
                            <input type="text" class="form-control"  value="<%=book.getUpdateTime()%>">
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../components/Footerbar.jsp"/>
</body>
</html>
