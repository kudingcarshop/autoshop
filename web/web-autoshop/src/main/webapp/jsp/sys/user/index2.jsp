<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../path.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${path}/js/bootstrap/3.3.7/css/bootstrap.min.css"/>  
	<link rel="stylesheet" href="${path}/js/bv/css/bootstrapValidator.css"/>
	
	<script src="${path}/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/vendor/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${path}/vendor/bv/js/bootstrapValidator.min.js"></script>
<title>用户注册</title>
</head>
<body>
<br>
	<div class="container">
		<div class="row">
			<div class="col-sm-10">
				<font id="errors" color="red"></font>
			</div>
		</div>
	</div>
	<form class="form-horizontal" role="form" id="userform" action="${path}/user/register" style="padding-left:5px;padding-right:5px">
		<div class="form-group">
			<label for="user_name" class="col-sm-1 col-sm-offset-1 control-label">用户名</label> 
			<div class="col-sm-8">
				<input type="text" name="user_name" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-sm-1 col-sm-offset-1 control-label">密码</label> 
			<div class="col-sm-8">
				<input type="password" name="password" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password_confirm" class="col-sm-1 col-sm-offset-1 control-label">确认密码</label> 
			<div class="col-sm-8">
				<input type="password" name="password_confirm" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-10 col-sm-offset-2 ">
				<button id="cancleBtn" type="reset" class="btn btn-default">取消</button>
				<button type="submit" class="btn btn-default">提交</button>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#userform").bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
            	user_name: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: 'The username is required and can\'t be empty'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: 'The username must be more than 6 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: 'The password  is required and can\'t be empty'
                        },
                        stringLength: {
                            min: 6,
                            max: 30,
                            message: 'The password must be more than 6 and less than 30 characters long'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: 'The username can only consist of alphabetical, number, dot and underscore'
                        }
                    }
                },
                password_confirm: {
                    validators: {
                    	 notEmpty: {
                             message: 'The password  is required and can\'t be empty'
                         },
                         stringLength: {
                             min: 6,
                             max: 30,
                             message: 'The password must be more than 6 and less than 30 characters long'
                         },
                         regexp: {
                             regexp: /^[a-zA-Z0-9_\.]+$/,
                             message: 'The username can only consist of alphabetical, number, dot and underscore'
                         },
                    	identical: {
                            field: 'password'
                        }
                    }
                }
            }
        })
        .on('success.form.bv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');
            console.log("start to register");
            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
                $error = $("#errors");
                if(result.flag == 1){
                	$('#cancleBtn').trigger('click');
                	$error.text("");
                } else {
                	$error.text(result.message);
                }
                $form.find('button[type="submit"]').prop('disabled',false);
            }, 'json');
        });
	});
	</script>
</body>
</html>