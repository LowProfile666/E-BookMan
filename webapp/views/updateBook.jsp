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
    <title>E-BookMan-修改</title>
    <style>
        .container {
            min-height: 100vh;
        }
    </style>
</head>
<body>
<jsp:include page="../components/Navbar.jsp"/>
<div class="container" style="padding-top: 80px">
    <h3>请修改书籍信息~🤗</h3>
    <div class="page-content">
        <div class="card">
            <div class="card-header">
                <form action="${pageContext.request.contextPath}/search">
                    <div class="row">
                        <div class="col">
                            <input type="text" name="isbn" class="form-control"
                                   placeholder="输入要修改书籍的ISBN，点击查找"
                                   aria-label="输入要修改书籍的ISBN，点击查找">
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary">查找</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-body">
                <%
                    Object obj = request.getAttribute("searchResult");
                    boolean searchResult = obj == null || (Boolean) obj;
                    if (!searchResult) {
                %>
                    <h3>没有该书信息😭</h3>
                <%
                    } else {
                %>
                <div class="row">
                    <div class="col">
                        <%
                            Book book = (Book) request.getAttribute("updateBook");
                            if (book != null) {
                        %>
                        <form action="${pageContext.request.contextPath}/update" method="post">
                            <input type="hidden" name="id" value="<%=book.getId()%>" required>
                            <div class="mb-3">
                                <label for="isbn" class="form-label">ISBN</label>
                                <input type="text" class="form-control" id="isbn" name="isbn"
                                       placeholder="请输入书籍的ISBN编号" value="<%=book.getIsbn()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label">书名</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="请输入书籍名称" value="<%=book.getName()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="category" class="form-label">分类</label>
                                <input type="text" class="form-control" id="category" name="category"
                                       placeholder="请输入书籍分类" value="<%=book.getCategory()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="publisher" class="form-label">出版社</label>
                                <input type="text" class="form-control" id="publisher" name="publisher"
                                       placeholder="请输入出版社名称" value="<%=book.getPublisher()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">价格</label>
                                <input type="number" class="form-control" id="price" name="price"
                                       placeholder="请输入书籍价格" step="0.01" min="0" value="<%=book.getPrice()%>"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="remarks" class="form-label">备注</label>
                                <textarea class="form-control" id="remarks" name="remarks"
                                          placeholder="请输入书籍备注（可选）" rows="3"><%=book.getRemarks() == null ? "" : book.getRemarks()%></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">提交修改</button>
                        </form>
                        <%
                            }
                        %>
                        <%
                            Book updatedBook = (Book) request.getAttribute("updatedBook");
                            if (updatedBook == null) return;
                        %>
                        <form>
                            <fieldset disabled>
                                <legend>${updateResult ? "修改成功😊" : "修改失败😢"}</legend>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="isbn" placeholder="请输入书籍的ISBN编号" value="<%=updatedBook.getIsbn()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="name" placeholder="请输入书籍名称" value="<%=updatedBook.getName()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" name="category" placeholder="请输入书籍分类" value="<%=updatedBook.getCategory()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control"  name="publisher" placeholder="请输入出版社名称" value="<%=updatedBook.getPublisher()%>">
                                </div>
                                <div class="mb-3">
                                    <input type="number" class="form-control" name="price" placeholder="请输入书籍价格" step="0.01" min="0" value="<%=updatedBook.getPrice()%>">
                                </div>
                                <div class="mb-3">
                                    <textarea class="form-control" name="remarks" rows="3" ><%=updatedBook.getRemarks() == null ? "" : updatedBook.getRemarks()%></textarea>
                                </div>
                            </fieldset>
                        </form>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../components/Footerbar.jsp"/>
</body>
</html>
