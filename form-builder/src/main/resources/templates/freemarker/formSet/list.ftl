<div class="card">
    <div class="card-header">
        <h5>폼빌더</h5>
        <span>폼빌더 생성 목록 <b>확인</b> 및 <b>관리</b></span>
    </div>
    <div class="card-block table-border-style">
        <div class="table-responsive">
            <table class="table">
            	<colgroup>
					<col class="col-md-1">
					<col class="col-md-2">
					<col class="col-md-3">
					<col class="col-md-2">
					<col class="col-md-1">
					<col class="col-md-1">
					<col class="col-md-2">
				</colgroup>
                <thead>
                    <tr>
                        <th class="text-center">key</th>
                        <th class="text-center">폼타입</th>
                        <th class="text-center">폼빌더명</th>
                        <th class="text-center">테이블명</th>
                        <th class="text-center">등록일</th>
                        <th class="text-center">수정일</th>
                        <th class="text-center">편집</th>
                    </tr>
                </thead>
                <tbody>
                	<#if formSetList?size gt 0>
	                    <#list formSetList as formSetInfo>
						<tr>
	                        <td class="text-center">${formSetInfo.formSetSeq}</td>
	                        <td class="text-center">${formSetInfo.formSetTypeName}</td>
	                        <td>${formSetInfo.formSetName}</td>
	                        <td class="text-center">${formSetInfo.targetTableName}</td>
	                        <td class="text-center">${formSetInfo.regDate?string.short}</td>
	                        <td class="text-center">${formSetInfo.modDate?string.short}</td>
	                        <td class="text-center">
	                        	<div class="dropdown">
								  <button class="btn btn-outline-secondary btn-sm dropdown-toggle" type="button" id="settingMenuBtn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    설정
								  </button>
								  <div class="dropdown-menu" aria-labelledby="settingMenuBtn">
								    <a class="dropdown-item" href="/formItem/item?formSetSeq=${formSetInfo.formSetSeq}">항목관리</a>
								    <a class="dropdown-item" href="/formItem/list?formSetSeq=${formSetInfo.formSetSeq}">목록관리</a>
								    <a class="dropdown-item" href="/formAuth/list?formSetSeq=${formSetInfo.formSetSeq}" title="나중에 거의다 변경할 기능 기본 구현을 위해 작업">권한관리</a>
								    <a class="dropdown-item" href="javascript:void(0);" onclick="distribute('${formSetInfo.formSetSeq}');" title="데이터가 저장될 물리적 DB공간 생성">배포</a>
								  </div>
								</div>
	                        </td>
	                    </tr>
						</#list>
					<#else>
						<tr>
	                        <td colspan="6" class="text-center">No DATA.</td>
	                    </tr>
					</#if>
                </tbody>
            </table>
        </div>
    </div>
    <!-- <div class="card-footer text-center">
    	&lt;&lt; &lt; [1] 2 3... &gt; &gt;&gt;
    </div> -->
</div>
<div class="card">
	<div class="card-body text-right">
         <button class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-target="#formSetModal">추가</button>
	</div>
</div>

<div class="modal fade" id="formSetModal" tabindex="-1" role="dialog" aria-labelledby="formSetModalLabel" aria-hidden="true" style="z-index:1041;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title" id="formSetModalLabel">폼빌더</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
			        <div class="card-block">
			            <form name="formSetForm" id="formSetForm" class="form-material" action="" method="POST">
			                <div class="form-group form-info">
			                    <input type="text" name="formSetName" class="form-control" title="폼게시판 명" required>
			                    <span class="form-bar"></span>
			                    <label class="float-label">폼게시판 명</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="formSetType" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">폼타입</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="formSetSkin" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">스킨</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="targetTableName" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">대사 테이블명</label>
			                </div>
			            </form>
			        </div>
			    </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="submitBtn">저장</button>
			</div>
		</div>
	</div>
</div>
<script>
const distribute =(formSetSeq)=> {
	ToastBlockUi.fire({title: '잠시만 기다려주세요.', imageUrl: "/images/blockbusy.gif"});
	fetch("/formSet/distribute?formSetSeq="+formSetSeq, {method: 'GET'}).then((response) => response.json()).then((json) => distributeComplate(json)).catch((error) => console.log("error:", error));
}
const distributeComplate =(json)=> {
	ToastBlockUi.close();
	if(json.result){
		ToastAlert.fire({	icon: 'success', title: '베포가 완료 되었습니다.'});
	}else{
		ToastAlert.fire({	icon: 'warning', title: '확인 해주세요', text: json.message});
	}
}
$(function () {
	validatorReset.set_defaults();
	
	$("#formSetForm").validate({
	    submitHandler: function(form) {
	        $('#formSetForm').ajaxSubmit({
	        	type:"POST",
			    contentType: "application/x-www-form-urlencoded; charset=utf-8",
				beforeSubmit : function() {
					ToastBlockUi.fire({title: '잠시만 기다려주세요.', imageUrl: "/images/blockbusy.gif"});	
					$("#formSetModal").modal('hide');
				},
				success : function(json) {
					ToastBlockUi.close();
					htmlInputClass.reSetData('formSetForm');
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
	
	$(document).on("click", "#submitBtn", function () {
		ToastConfirm.fire({icon: 'info', title: '폼빌더 정보를 저장 하겠습니까?'}).then((result)=>{
			if(result.isConfirmed){
				$('#formSetForm').attr("action",'/formSet/add');
				$('#formSetForm').submit();
			}
		});
	});
});
</script>