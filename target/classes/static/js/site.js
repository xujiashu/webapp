$(function(){

	$(".delbtn").click(function(){
		return confirm("确认删除？操作不可恢复!");
	});
	
	var uids = {"uids":[]};
	
	$(".cuid").click(function(){
		uids.uids = [];
		$(".cuid:checked").each(function(){
			uids.uids.push($(this).val());
		})
	})
	
	
	
	$(".delbtns").click(function(){
		
		if(uids.uids.length <= 0) return;
		if(confirm("确认删除？操作不可恢复!")){
			var json = JSON.stringify(uids);
			console.log(json);
			$("#delteusers").val(json);
			$("#form1").attr("action", "/deleteusers");
			console.log($("#form1"));
			$("#form1").submit();
		}
	});
	
	$(".commodity-delbtns").click(function(){
		
		if(uids.uids.length <= 0) return;
		if(confirm("确认删除？操作不可恢复!")){
			var json = JSON.stringify(uids);
			console.log(json);
			$("#deltecommodities").val(json);
			$("#form1").attr("action", "/deletecommodities");
			$("#form1").submit();
		}
	});
	$(".news-delbtns").click(function(){
		
		if(uids.uids.length <= 0) return;
		if(confirm("确认删除？操作不可恢复!")){
			var json = JSON.stringify(uids);
			console.log(json);
			$("#deltenewss").val(json);
			$("#form1").attr("action", "/deletenewss");
			$("#form1").submit();

		}
	});
	
});


