<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${path}/vendor/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="${path}/vendor/jquery/jquery-3.2.1.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js" charset="UTF-8"></script>
    <title>预约列表</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>预约列表</h2>
                </div>
                <div>
                    <c:forEach items="${requestScope.book_list}" var="bookItem">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                ${bookItem.plateNumber}
                                </h3>
                            </div>
                            <div class="panel-body">
                                <p class="text-primary">预约类型: ${bookItem.typeName}</p>
                                <p class="text-danger">到店时间: ${bookItem.bookTime}</p>
                                <c:if test="${bookItem.state == 0}">
                                    <p class="text-primary" name="state" id="state">已预约</p>
                                </c:if>
                                <c:if test="${bookItem.state == 2}">
                                    <p class="text-success" name="state" id="state">已接车</p>
                                </c:if>
                                <c:if test="${bookItem.state == 3}">
                                    <p class="text-danger" name="state" id="state">已取消</p>
                                </c:if>
                                <c:choose>
                                    <c:when test="${requestScope.isUser}">
                                        <!--如果 -->
                                        <c:if test="${bookItem.state == 0}">
                                            <button id="button_cancel" class="btn btn-danger" onclick="cancelClick('${bookItem.id}')">取消</button>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <!--否则 -->
                                        <c:if test="${bookItem.state == 0}">
                                            <button id="button_accept" class="btn btn-primary" onclick="checkinClick('${bookItem.id}')">接车</button>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    function preData($id) {
        var data = {};
        data['id'] = $id;
        console.log(data);
        return data;
    }

    function cancelClick(obj) {
        var $data = preData(obj);
        console.log($data);
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/car_book/cancel',
                type: 'post',
                data: $data,
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    location.reload();
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    }

    function checkinClick(obj) {
        var $data = preData(obj);
        console.log($data);
        $.ajax( //ajax方式提交表单  
            {
                url: '/web-autoshop/garage/car_book/checkin',
                type: 'post',
                data: $data,
                dataType: 'json',
                beforeSubmit: function() {},
                success: function(data) {
                    location.reload();
                },
                clearForm: false, //禁止清楚表单  
                resetForm: false //禁止重置表单  
            }
        );
    }
    </script>
</body>

</html>