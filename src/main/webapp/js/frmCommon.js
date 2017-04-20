	var pickupOrgFun = function(id) {
		if(id == "60366688-0001-0001-0011-888888user01"){
			parent.$.messager.alert("提示信息","您没有权限操作超级管理员!");
		}else{
			var dialog = parent.frm.modalDialog({
				title : '修改机构',
				url : frm.contextPath + '/jsp/base/FrmUserOrganizationGrant.jsp?id=' + id,
				buttons : [ {
					text : '选择',
					handler : function() {
						var row = dialog.find('iframe').get(0).contentWindow.selectSingle();
						$('#orgId').val(row.id);
						$('#orgName').text(row.name);
						$('#orgCode').val(row.orgCode);
						dialog.dialog('close');
					}
				} ]
			});
		}
	};