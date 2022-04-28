<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<h5>목록 관리</h5>
				<span>추가 하려는 항목을 선택 후 목록 항목에 drag&drop으로 추가 할 수 있습니다.</span>
				<span>항목 삭제를 원하는 경우 마우스 우클릭 후 삭제 메뉴를 선택 하시면 됩니다.</span>
				<span>항목의 순서를 변경 하면 변경된 순서로 사용자 화면에 노출 됩니다.</span>
			</div>
			<div class="card-block">
				<div class="row">
					<div class="col-md-10">
						<ul id="sortable" class="list-group list-group-horizontal">
							<#list formItemViewList as formItemViewInfo>
							<li class="list-group-item" data-item-seq="${formItemViewInfo.formItemSeq}">${formItemViewInfo.formItemName}</li>
							</#list>
						</ul>
					</div>
					<div class="col-md-2" style="height: 300px;overflow: auto;">
						<ul class="list-group">
							<#list formItemList as formItemInfo>
							<li class="list-group-item draggable" data-item-seq="${formItemInfo.formItemSeq}">${formItemInfo.formItemName}</li>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="card">
	<div class="card-body text-right">
         <button class="btn btn-primary waves-effect waves-light" id="submitBtn">저장</button>
         <button class="btn btn-secondary waves-effect waves-light" id="goListBtn">목록</button>
	</div>
	<form name="formItemForm" id="formItemForm" action="" method="post">
		<input type="hidden" name="formSetSeq" title="폴빌더 번호" value="${searchParam.formSetSeq}" required>
		<input type="hidden" name="itemInfoData" title="항목 상세 정보" required>
	</form>
</div>
<script>
$( function() {
	$( "#sortable" ).sortable({
		revert: true
	});
	$( ".draggable" ).draggable({
		connectToSortable: "#sortable",
		helper: "clone",
		revert: "invalid",
		start: function(event, ui) {  
			if($('#sortable').find('[data-item-seq='+$(this).attr('data-item-seq')+']').length){
				ToastAlert.fire({	icon: 'warning', title: '이미 등록된 항목 입니다.'});
			}
		}
	});
	$( "ul, li" ).disableSelection();
	$.contextMenu({
         selector: '#sortable .list-group-item', 
         callback: function(key, options) {
        	 if(key=="delete"){
        		 options.$trigger.remove();
        	 }
             window.console; 
         },
         items: {
             "delete": {name: "삭제", icon: "delete"},
             "sep1": "---------",
             "quit": {name: "닫기", icon: function(){
                 return 'context-menu-icon context-menu-icon-quit';
				}
             }
         }
     });
	$("#goListBtn").click(function(){
		location.href="/formSet/list";
	});
	
	validatorReset.set_defaults();
	$("#formItemForm").validate({
	    submitHandler: function(form) {
	        $('#formItemForm').ajaxSubmit({
	        	type:"POST",
			    contentType: "application/x-www-form-urlencoded; charset=utf-8",
				beforeSubmit : function() {
					ToastBlockUi.fire({title: '잠시만 기다려주세요.', imageUrl: "/images/blockbusy.gif"});	
				},
				success : function(json) {
					ToastBlockUi.close();
					if(json.result){
						ToastAlert.fire({	icon: 'success', title: '정상적으로 저장 되었습니다.'}).then((result)=>{
							if(result.isConfirmed){
								location.href="/formSet/list";
							}
						});
					}else{
						ToastAlert.fire({	icon: 'error', title: '문제가 발생 되었습니다.', text: json.message});
					}
				}
			});
	    }
	}).settings.ignore = [];
	$("#submitBtn").click(function(){
		//등록된 항목이 하나도 없는경우 경고 메시지 출력 !0 조건
		if(!$("#sortable .list-group-item").length){
			ToastAlert.fire({	icon: 'warning', title: '등록할 정보가 없습니다.', text: '항목에 대한 정보를 1개 이상 추가 하셔야 합니다.'});
			return;
		}
		let itemInfoDataMap = new Map();
		let itemArray					= [];
		$('#sortable .list-group-item').each(function(){
			let liIndex 			= $(this).index();
			let formItemSeq 	= $(this).attr("data-item-seq");
			
			itemInfoDataMap.set("itemIndex", liIndex);
			itemInfoDataMap.set("itemSeq", formItemSeq);
			
			itemArray.push(Object.fromEntries(itemInfoDataMap));
			itemInfoDataMap.clear();
		});
		let itemInfoData = JSON.stringify(itemArray);
		$("#formItemForm").find("input:hidden[name='itemInfoData']").val(itemInfoData);
		
		ToastConfirm.fire({icon: 'info', title: '항목 정보를 저장 하겠습니까?'}).then((result)=>{
			if(result.isConfirmed){
				$('#formItemForm').attr("action",'/formItem/list/order/add');
				$('#formItemForm').submit();
			}
		});
	});
} );
</script>