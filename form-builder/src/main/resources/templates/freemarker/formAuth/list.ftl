<div class="card">
    <div class="card-header">
        <h5>폼빌더 권한</h5>
        <span>폴빌더 권한 생성 및 관리</span>
    </div>
    <div class="card-block table-border-style">
        <div class="table-responsive">
            <table class="table">
            	<colgroup>
					<col class="col-md-1">
					<col class="col-md-5">
					<col class="col-md-1">
					<col class="col-md-1">
					<col class="col-md-1">
					<col class="col-md-1">
					<col class="col-md-2">
				</colgroup>
                <thead>
                    <tr>
                        <th class="text-center">key</th>
                        <th class="text-center">권한명</th>
                        <th class="text-center">목록권한</th>
                        <th class="text-center">보기권한</th>
                        <th class="text-center">쓰기권한</th>
                        <th class="text-center">파일다운</th>
                        <th class="text-center">등록일</th>
                        <th class="text-center">편집</th>
                    </tr>
                </thead>
                <tbody>
                	<#if formAuthList?size gt 0>
	                    <#list formAuthList as formAuthInfo>
						<tr>
	                        <td class="text-center">${formAuthInfo.authSeq}</td>
	                        <td class="text-center">${formAuthInfo.authName}</td>
	                        <td class="text-center">${formAuthInfo.authList}</td>
	                        <td class="text-center">${formAuthInfo.authView}</td>
	                        <td class="text-center">${formAuthInfo.authWrite}</td>
	                        <td class="text-center">${formAuthInfo.authFileDown}</td>
	                        <td class="text-center">${formAuthInfo.regDate?string.short}</td>
	                        <td class="text-center">
	                        	<div class="dropdown">
								  <button class="btn btn-outline-secondary btn-sm dropdown-toggle" type="button" id="settingMenuBtn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								    설정
								  </button>
								  <div class="dropdown-menu" aria-labelledby="settingMenuBtn">
								  	<a class="dropdown-item" href="javascript:void(0);" onclick="authModModal('${formAuthInfo.authSeq}');" title="수정">수정</a>
									<a class="dropdown-item" href="javascript:void(0);" onclick="delAuth('${formAuthInfo.authSeq}');" title="삭제">삭제</a>
								  </div>
								</div>
	                        </td>
	                    </tr>
						</#list>
					<#else>
						<tr>
	                        <td colspan="7" class="text-center">No DATA.</td>
	                    </tr>
					</#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="card">
	<div class="card-body text-right">
         <button class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-target="#formAuthModal">추가</button>
         <button class="btn btn-secondary waves-effect waves-light" id="goListBtn">목록</button>
	</div>
</div>

<div class="modal fade" id="formAuthModal" tabindex="-1" role="dialog" aria-labelledby="formAuthModalLabel" aria-hidden="true" style="z-index:1041;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title" id="formAuthModalLabel">권한</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
			        <div class="card-block">
			            <form name="formAuthForm" id="formAuthForm" class="form-material" action="" method="POST">
			            	<input type="hidden" name="authSeq" title="권한 번호">
			            	<input type="hidden" name="formSetSeq" value="${searchParam.formSetSeq}" title="폴빌더 번호" required>
			                <div class="form-group form-info">
			                    <input type="text" name="authName" class="form-control" required>
			                    <span class="form-bar"></span>
			                    <label class="float-label">권한명</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="authList" class="form-control">
									<option value="">선택</option>
									<option value="Y">접근</option>
									<option value="N">미접근</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">목록</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="authView" class="form-control">
									<option value="">선택</option>
									<option value="Y">접근</option>
									<option value="N">미접근</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">상세</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="authWrite" class="form-control">
									<option value="">선택</option>
									<option value="Y">접근</option>
									<option value="N">미접근</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">쓰기</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="authFileDown" class="form-control">
									<option value="">선택</option>
									<option value="Y">접근</option>
									<option value="N">미접근</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">파일다운</label>
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
const authModModal =(authSeq)=> {
	fetch("/formAuth/info/"+authSeq, {method: 'GET'}).then((response) => response.json()).then((json) => setAuthModal(json)).catch((error) => console.log("error:", error));
}
const setAuthModal =(json)=> {
	if(json.result){
		htmlInputClass.setData(json.authInfo, 'formAuthForm');
		$("#formAuthModal").modal('show');
	}else{
		ToastAlert.fire({	icon: 'error', title: '문제가 발생 되었습니다.', text: json.message});
	}
}
const delAuth =(authSeq)=> {
	ToastConfirm.fire({icon: 'info', title: '권한 정보를 삭제 하겠습니까?'}).then((result)=>{
		if(result.isConfirmed){
			fetch("/formAuth/del/"+authSeq, {method: 'DELETE'}).then((response) => response.json()).then((json) => delComplate(json)).catch((error) => console.log("error:", error));
		}
	});
}
const delComplate =(json)=> {
	if(json.result){
		ToastAlert.fire({	icon: 'success', title: '정상적으로 삭제 되었습니다.'}).then((result)=>{
			if(result.isConfirmed){
				location.href="/formAuth/list?formSetSeq=${searchParam.formSetSeq}";
			}
		});
	}else{
		ToastAlert.fire({	icon: 'error', title: '문제가 발생 되었습니다.', text: json.message});
	}
}
$(function () {
	validatorReset.set_defaults();
	
	$("#formAuthForm").validate({
	    submitHandler: function(form) {
	        $('#formAuthForm').ajaxSubmit({
	        	type:"POST",
			    contentType: "application/x-www-form-urlencoded; charset=utf-8",
				beforeSubmit : function() {
					ToastBlockUi.fire({title: '잠시만 기다려주세요.', imageUrl: "/images/blockbusy.gif"});	
				},
				success : function(json) {
					ToastBlockUi.close();
					htmlInputClass.reSetData('formAuthForm');
					if(json.result){
						ToastAlert.fire({	icon: 'success', title: '정상적으로 저장 되었습니다.'}).then((result)=>{
							if(result.isConfirmed){
								location.href="/formAuth/list?formSetSeq=${searchParam.formSetSeq}";
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
		ToastConfirm.fire({icon: 'info', title: '권한 정보를 저장 하겠습니까?'}).then((result)=>{
			if(result.isConfirmed){
				$('#formAuthForm').attr("action",'/formAuth/add');
				$('#formAuthForm').submit();
			}
		});
	});
	
	$("#goListBtn").click(function(){
		location.href="/formSet/list";
	});
});
</script>