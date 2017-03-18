	var showPopGrid = function(collectionses) {
		grid = $('#popGrid').datagrid({
			title : '导览结果',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'createDatetime ',
			sortOrder : 'desc',
			pageSize : 5,
			pageList : [ 5,10],
			frozenColumns : [ [
				{
					width : '130',
					title : '名称',
					field : 'name',
					sortable : true
				}
			] ],
			columns : [ [
				{
					width : '60',
					title : '编号',
					field : 'number',
					sortable : true
				},
				{
					width : '80',
					title : '经度',
					field : 'longitude',
					sortable : true
				},
				{
					width : '80',
					title : '纬度',
					field : 'latitude',
					sortable : true
				},
				{
					width : '100',
					title : '图片URI',
					field : 'pictureUrl'
				},{
					width : '100',
					title : '解说URI',
					field : 'voiceUrl'
				},{
					width : '160',
					title : '文字',
					field : 'commentText'
				},{
					width : '130',
					title : '创建时间',
					field : 'createDatetime',
					sortable : true
				} , {
					width : '130',
					title : '修改时间',
					field : 'updateDatetime',
					sortable : true
				} 
			] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', frm.pixel_0);
				parent.$.messager.progress('close');
			}
		}).datagrid('loadData', collectionses);
	};