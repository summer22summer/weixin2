<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<title>        </title>
<script type="text/javascript">
	$(function(){
		$('#manNewsTable').edatagrid({
			url : 'back/news.action?op=findAll',
			pagination : true,
			pageSize : 50,
			pageList : [5,10,15,20,25,30,35,40,45,50,100,200,500],
			
			title : '新闻类别管理',
			idField : 'tid',
			rownumbers : true,
			nowrap : true,
			sortName : 'tid',
			sortOrder : 'desc',
			singleSelect : true,
			
			onError : function(a,b){
				$messager.alert('错误','操作失败');
			},
			
			columns : [[{
				field : 'nid',
				title : '新闻编号',
				width : 100,
				align : 'center',
				hidden : true
			},{
				field : 'title',
				title : '新闻标题',
				width : 350,
				align : 'center'
			},{
				field : 'auth',
				title : '作者名',
				width : 100,
				align : 'center'
			},{
				field : 'ndate',
				title : '新闻时间',
				width : 100,
				align : 'center'
			},{
				field : 'views',
				title : '浏览次数',
				width : 100,
				align : 'center'
			},{
				field : 'weight',
				title : '权重',
				width : 100,
				align : 'center'
			},{
				field : 'tname',
				title : '新闻类别',
				width : 100,
				align : 'center'
			}]]
		});
	});
</script>
</head>
<body>
	<table id="manNewsTable"></table>
</body>
</html>