<%@ page import="com.zsm.bean.Book" %><%--
  Created by IntelliJ IDEA.
  User: 20620
  Date: 2024-12-26
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>E-BookMan-添加</title>
    <style>
        .container {
            min-height: 100vh;
        }
    </style>
</head>
<body>
<jsp:include page="../components/Navbar.jsp" />
<div class="container" style="padding-top: 80px">
    <h3>请添加书籍信息~😉</h3>
    <div class="page-content">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <form action="${pageContext.request.contextPath}/add" method="post" >
                            <div class="mb-3">
                                <label for="isbn" class="form-label">ISBN</label>
                                <input type="text" class="form-control" id="isbn" name="isbn" placeholder="请输入书籍的ISBN编号" required>
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label">书名</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="请输入书籍名称" required>
                            </div>
                            <div class="mb-3">
                                <label for="category" class="form-label">分类</label>
                                <input type="text" class="form-control" id="category" name="category" placeholder="请输入书籍分类" required>
                            </div>
                            <div class="mb-3">
                                <label for="publisher" class="form-label">出版社</label>
                                <input type="text" class="form-control" id="publisher" name="publisher" placeholder="请输入出版社名称" required>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">价格</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="请输入书籍价格" step="0.01" min="0" required>
                            </div>
                            <div class="mb-3">
                                <label for="remarks" class="form-label">备注</label>
                                <textarea class="form-control" id="remarks" name="remarks" placeholder="请输入书籍备注（可选）" rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">添加书籍</button>
                        </form>
                    </div>
                    <div class="col">
                        <form>
                            <%
                                Book book = (Book) request.getAttribute("book");
                                if (book == null) return;
                            %>
                            <fieldset disabled>
                                <legend>${addResult ? "添加成功😊" : "添加失败😢"}，<%=book.getIsbn()%>已存在</legend>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="isbn" placeholder="请输入书籍的ISBN编号" value="<%=book.getIsbn()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="name" placeholder="请输入书籍名称" value="<%=book.getName()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="category" placeholder="请输入书籍分类" value="<%=book.getCategory()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control"  name="publisher" placeholder="请输入出版社名称" value="<%=book.getPublisher()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="number" class="form-control" name="price" placeholder="请输入书籍价格" step="0.01" min="0" value="<%=book.getPrice()%>">
                                </div>
                                <div class="mb-3">
                                    <textarea class="form-control" name="remarks" placeholder="请输入书籍备注（可选）" rows="3" ><%=book.getRemarks()%></textarea>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../components/Footerbar.jsp" />
</body>
</html>
