<div class="row">
	<div class="col-md-6">
		<div class="card">
			<div class="card-header">
				<h5>항목유형</h5>
				<span>추가 하려는 항목 (+) 버튼 선택 후 추가 정보 입력 후 추가 할 수 있습니다.</span>
			</div>
			<div class="card-block">
				<div class="form-group row">
					<label class="col-md-3 col-form-label">항목 선택</label>
					<div class="col-md-8">
						<select name="inputSelect" class="form-control itemSelect">
							<option value="">선택</option>
							<option value="textInputSample">텍스트 입력</option>
							<option value="fileInputSample">파일 입력</option>
							<option value="selectBoxSample">선택박스</option>
							<option value="checkBoxSample">체크 박스</option>
							<option value="radioSample">라디오 박스</option>
							<option value="textareaSample">텍스트 영역</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="card textInputSample sampleInput d-none" data-item-type="text">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">텍스트 입력</label>
					<div class="col-md-8">
						<input type="text" class="form-control">
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="card fileInputSample sampleInput d-none" data-item-type="file">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">파일 입력</label>
					<div class="col-md-8">
						<input type="file" class="form-control">
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="card selectBoxSample sampleInput d-none" data-item-type="selectBox">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">선택박스</label>
					<div class="col-md-8">
						<select name="select" class="form-control">
							<option value="opt1">선택</option>
							<option value="opt2">옵션1</option>
							<option value="opt3">옵션2</option>
						</select>
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="card checkBoxSample sampleInput d-none" data-item-type="checkBox">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">체크 박스</label>
					<div class="col-md-8">
						<input class="form-check-input" type="checkbox" value="1" id="flexCheckDefault1">
						<label class="form-check-label" for="flexCheckDefault1">선택1</label>
						<input class="form-check-input" type="checkbox" value="2" id="flexCheckDefault2">
						<label class="form-check-label" for="flexCheckDefault1">선택2</label>
						<input class="form-check-input" type="checkbox" value="3" id="flexCheckDefault3">
						<label class="form-check-label" for="flexCheckDefault1">선택3</label>
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="card radioSample sampleInput d-none" data-item-type="radio">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">라디오 박스</label>
					<div class="col-md-8">
						<input class="form-check-input" type="radio" name="flexRadioDefault1" id="flexRadioDefault1">
						<label class="form-check-label" for="flexRadioDefault1">선택1</label>
						<input class="form-check-input" type="radio" name="flexRadioDefault2" id="flexRadioDefault2">
						<label class="form-check-label" for="flexRadioDefault2">선택2</label>
						<input class="form-check-input" type="radio" name="flexRadioDefault3" id="flexRadioDefault3">
						<label class="form-check-label" for="flexRadioDefault2">선택3</label>
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
		<div class="card textareaSample sampleInput d-none" data-item-type="textArea">
			<div class="card-block">
				<div class="row">
					<label class="col-md-3 col-form-label">텍스트 영역</label>
					<div class="col-md-8">
						<textarea rows="5" cols="5" class="form-control" placeholder="입력항목"></textarea>
					</div>
					<div class="col-md-1">
						<i class="fa fa-plus addItem"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card">
			<div class="card-header">
				<h5>등록 항목</h5>
				<span>추가 하려는 항목 (+) 버튼 선택 후 추가 정보 입력 후 추가 할 수 있습니다.</span>
			</div>
			<div class="card-block">
				<ul id="sortable">
				</ul>
				<form name="formItemForm" id="formItemForm" action="" method="post">
					<input type="hidden" name="formSetSeq" value="${searchParam.formSetSeq}" title="폴빌더 번호" required>
					<input type="hidden" name="itemInfoData" title="항목 상세 정보" required>
					<input type="hidden" name="deleteItemSeq" title="삭제 항목">
				</form>
			</div>
		</div>
	</div>
</div>
<div class="card">
	<div class="card-body text-right">
         <button class="btn btn-primary waves-effect waves-light" id="submitBtn">저장</button>
         <button class="btn btn-secondary waves-effect waves-light" id="goListBtn">목록</button>
	</div>
</div>
<div class="modal fade" id="formItemModal1" tabindex="-1" role="dialog" aria-labelledby="formItemModal1Label" aria-hidden="true" style="z-index:1041;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title" id="formItemModal1Label">항목정보</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
			        <div class="card-block">
			            <form name="formItemForm1" id="formItemForm1" class="form-material" action="" method="POST">
			                <div class="form-group form-info">
			                    <input type="text" name="itemName" class="form-control" required>
			                    <input type="hidden" name="itemLiIndex">
			                    <input type="hidden" name="itemType">
			                    <span class="form-bar"></span>
			                    <label class="float-label">항목 명</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="itemView" class="form-control">
									<option value="">선택</option>
									<option value="Y">출력</option>
									<option value="N">미출력</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">항목 출력</label>
			                </div>
			                <div class="form-group form-info">
			                    <select name="listView" class="form-control">
									<option value="">선택</option>
									<option value="Y">출력</option>
									<option value="N">미출력</option>
								</select>
			                    <span class="form-bar"></span>
			                    <label class="float-label">목록 출력</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="filedDefault" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">항목 기본값</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="fieldPhd" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">항목 힌트</label>
			                </div>
			                <div class="form-group form-info">
			                    <input type="text" name="itemDes" class="form-control">
			                    <span class="form-bar"></span>
			                    <label class="float-label">설명</label>
			                </div>
			            </form>
			        </div>
			    </div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="addInputBtn">추가</button>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="formItemModal2" tabindex="-1" role="dialog" aria-labelledby="formItemModal2Label" aria-hidden="true" style="z-index:1041;">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<h5 class="modal-title" id="formItemModal2Label">항목정보</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form name="formItemForm2" id="formItemForm2" class="form-material" action="" method="POST">
					<div class="row">
						<div class="col-md-6">
							<div class="card">
						        <div class="card-block">
					                <div class="form-group form-info">
					                    <input type="text" name="itemName" class="form-control" required>
					                    <input type="hidden" name="itemLiIndex">
					                    <input type="hidden" name="itemType">
					                    <span class="form-bar"></span>
					                    <label class="float-label">항목 명</label>
					                </div>
					                <div class="form-group form-info">
					                    <select name="itemView" class="form-control">
											<option value="">선택</option>
											<option value="Y">출력</option>
											<option value="N">미출력</option>
										</select>
					                    <span class="form-bar"></span>
					                    <label class="float-label">항목 출력</label>
					                </div>
					                <div class="form-group form-info">
					                    <select name="listView" class="form-control">
											<option value="">선택</option>
											<option value="Y">출력</option>
											<option value="N">미출력</option>
										</select>
					                    <span class="form-bar"></span>
					                    <label class="float-label">목록 출력</label>
					                </div>
					                <div class="form-group form-info">
					                    <input type="text" name="itemDes" class="form-control">
					                    <span class="form-bar"></span>
					                    <label class="float-label">설명</label>
					                </div>
						        </div>
						    </div>
						</div>
						<div class="col-md-6">
							<div class="card">
						        <div class="card-block" id="addFieldDivId">
					                <div class="form-group form-info fieldInfoDiv">
					                	<i class="fa fa-plus addFieldBtn" title="필드추가"></i>
					                </div>
						        </div>
						    </div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary" id="addBoxBtn">추가</button>
			</div>
		</div>
	</div>
</div>
<script>
let form1Type = ['text','file','textArea']; //작업시 값 체크를 위한 배열
let form2Type = ['selectBox','checkBox','radio']; //작업시 값 체크를 위한 배열
let inputNames = ['fieldText','fieldValue','fieldDefault','fieldPhd','fieldDes']; //작업시 값 체크를 위한 배열

const mustacheRender =(templateHtml, formData)=> {
	Mustache.parse(templateHtml);
	return Mustache.render(templateHtml, formData);
}
const addInput =(itemJson, itemSeq)=> {
	let setData			= false;
	let formData 		= null;
	if(itemJson==undefined){formData 		= $("#formItemForm1").serializeObject();console.log("formData set");}
	else{formData = JSON.parse(itemJson); setData=true;}
	let itemType 			= formData.itemType;
	let itemLiIndex		= formData.itemLiIndex;
	
	let rendered = mustacheRender($("#add_"+itemType+"_div").html(), formData);
	rendered = rendered.replace("+#itemJson#+",JSON.stringify(formData));
	
	if(!setData && itemLiIndex){
		$('#sortable li:eq(' + itemLiIndex + ')').replaceWith(rendered);
	}else{
		$('#sortable').append(rendered);
		if(itemSeq){$("#sortable .ui-state-default").last().find('input:hidden[name=formItemSeq]').val(itemSeq);}
	}
	$('#formItemModal1').modal('hide');
}
const dataArrToMap =(dataArr, fieldArray, fieldMap, itemMap)=> {
	if(dataArr){
		$.each(dataArr, function() {
			if(inputNames.indexOf(this.name)!=-1){
				if(this.name=='fieldText'){
					if(fieldMap.size>0){fieldArray.push(Object.fromEntries(fieldMap));}
					fieldMap.clear();
				}
				fieldMap.set(this.name, this.value);
			}else{
				itemMap.set(this.name, this.value);
			}
        });
		if(fieldMap.size>0){fieldArray.push(Object.fromEntries(fieldMap));}
		itemMap.set("fieldDatas", fieldArray);
	}
}
const addBox =(itemJson, itemSeq)=> {
	let setData					= false;
	let itemMap 					= new Map();
	let fieldMap 					= new Map();
	let fieldArray					= [];
	let dataArr 					= itemJson;
	if(itemJson==undefined){formData 		= $("#formItemForm2").serializeArray();}
	else{formData = JSON.parse(itemJson); setData=true;}
	dataArrToMap(dataArr, fieldArray, fieldMap, itemMap);
	
	let formData 		= Object.fromEntries(itemMap);
	let itemType 			= formData.itemType;
	let itemLiIndex		= formData.itemLiIndex;
	
	let rendered 	= mustacheRender($("#add_"+itemType+"_div").html(), formData);
	rendered 		= rendered.replace("+#itemJson#+",JSON.stringify(formData));
	
	if(!setData && itemLiIndex){
		$('#sortable li:eq(' + itemLiIndex + ')').replaceWith(rendered);
	}else{
		$('#sortable').append(rendered);
		if(itemSeq){$("#sortable .ui-state-default").last().find('input:hidden[name=formItemSeq]').val(itemSeq);}
	}
	$('#formItemModal2').modal('hide');
}
const setFieldDatas =(itemJson)=> {
	//작업에 시간이 걸리는 경우 모달 출력 후 그려질 수 있어 promise사용
	return new Promise(function(resolve, reject) {
		//fieldDatas 부분은 수동으로 처리
		let fieldDatas = itemJson.fieldDatas;
		$.each(fieldDatas, function(key, value){
			$("#addFieldDivId").append($("#addFieldInfoArea").html());
			$("#addFieldDivId .addFieldInfoDiv").last().find('input[name=fieldText]').val(value.fieldText);
			$("#addFieldDivId .addFieldInfoDiv").last().find('input[name=fieldValue]').val(value.fieldValue);
			$("#addFieldDivId .addFieldInfoDiv").last().find('input[name=fieldDes]').val(value.fieldDes);
			if(value.fieldDefault=="Y"){
				$("#addFieldDivId .addFieldInfoDiv").last().find('input:checkbox[name=fieldDefault]').prop("checked", true);
			}
		});
	});
}
const modItem =(itemLiIndex, itemJson, itemType)=> {
	if(form1Type.indexOf(itemType)!=-1){
		htmlInputClass.setData(itemJson, 'formItemForm1');
		$("#formItemForm1").find('input[name=itemLiIndex]').val(itemLiIndex);
		$('#formItemModal1').modal('show');
	}else{
		htmlInputClass.setData(itemJson, 'formItemForm2');
		$("#formItemForm2").find('input[name=itemLiIndex]').val(itemLiIndex);
		setFieldDatas(itemJson).then($('#formItemModal2').modal('show'));
	}
}
const initItemList =()=> {
	$("#workItemListDiv .work-item-data-list").each(function(){
		let itemSeq = $(this).find(".itemSeq").text();
		let itemJson = $(this).find(".itemJson").text();
		let itemType = $(this).find(".itemType").text();
		
		if(form1Type.indexOf(itemType)!=-1){
			addInput(itemJson, itemSeq);
		}else{
			addBox(itemJson, itemSeq);
		}
	});
}
$(function () {
	$( "#sortable" ).sortable({
		revert: true
	});
	$( "ul, li" ).disableSelection();
	$(".itemSelect").change(function(){
		let selectItemVal = $(this).val();
		$(".sampleInput").removeClass("d-block");
		$(".sampleInput").addClass("d-none");
		$("."+selectItemVal).removeClass("d-none").addClass("d-block");
	});
	$(".addItem").click(function(){
		let itemType = $(this).parents('.sampleInput').attr("data-item-type");
		
		if(form2Type.indexOf(itemType)!=-1){
			$('#formItemModal2').find("input:hidden[name='itemType']").val(itemType);
			$('#formItemModal2').modal('show');
		}else{
			$('#formItemModal1').find("input:hidden[name='itemType']").val(itemType);
			$('#formItemModal1').modal('show');
		}
	});
	$("#addInputBtn").click(function(){
		addInput();
	});
	$("#addBoxBtn").click(function(){
		addBox();
	});
	$(document).on("click", ".addFieldBtn", function () {
		$("#addFieldDivId").append($("#addFieldInfoArea").html());
	});
	$(document).on("click", ".deleteFieldInfoBtn", function () {
		$(this).parent("div").remove();
	});
	$(document).on("click", ".deleteItem", function () {
		let formItemSeq 		= $(this).parent("div").parent("div").find("input:hidden[name='formItemSeq']").val();
		let deleteItemSeq 	= $("#formItemForm").find("input:hidden[name='deleteItemSeq']").val();
		$("#formItemForm").find("input:hidden[name='deleteItemSeq']").val(deleteItemSeq+","+formItemSeq);
		$(this).parent("div").parent("div").parent("li").remove();
	});
	$(document).on("click", ".modItem", function () {
		let itemLiIndex 		= $(".ui-state-default").index($(this).parents('.ui-state-default'));
		let itemJson			= JSON.parse($(this).nextAll(".itemJsonDiv").text());
		let itemType			= itemJson.itemType;
		
		modItem(itemLiIndex, itemJson, itemType);
	});
	$('#formItemModal1').on('hidden.bs.modal', function (e) {
		htmlInputClass.reSetData('formItemForm1');
	});
	$('#formItemModal2').on('hidden.bs.modal', function (e) {
		$("#formItemForm2 .addFieldInfoDiv").remove();
		htmlInputClass.reSetData('formItemForm2');
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
		if(!$("#sortable .ui-state-default").length){
			ToastAlert.fire({	icon: 'warning', title: '등록할 정보가 없습니다.', text: '추가할 항목에 대한 정보를 1개 이상 추가 하셔야 합니다.'});
			return;
		}
		let itemInfoDataMap = new Map();
		let itemArray					= [];
		$('#sortable .ui-state-default').each(function(){
			let liIndex = $(this).index();
			let formItemSeq = $(this).find("input:hidden[name='formItemSeq']").val();
			let itemJson = $(this).find(".itemJsonDiv").text();
			
			itemInfoDataMap.set("itemIndex", liIndex);
			itemInfoDataMap.set("itemSeq", formItemSeq);
			itemInfoDataMap.set("itemJson", itemJson);
			
			itemArray.push(Object.fromEntries(itemInfoDataMap));
			itemInfoDataMap.clear();
		});
		let itemInfoData = JSON.stringify(itemArray);
		$("#formItemForm").find("input:hidden[name='itemInfoData']").val(itemInfoData);
		
		ToastConfirm.fire({icon: 'info', title: '항목 정보를 저장 하겠습니까?'}).then((result)=>{
			if(result.isConfirmed){
				$('#formItemForm').attr("action",'/formItem/add');
				$('#formItemForm').submit();
			}
		});
	});
	
	initItemList();
});
</script>
<div id="addFieldInfoArea" type="x-tmpl-mustache" class="d-none">
	<div class="form-group form-info addFieldInfoDiv">
		<input type="text" name="fieldText" class="form-control w-25" placeholder="필드명">
		<input type="text" name="fieldValue" class="form-control w-25" placeholder="필드값">
		<input type="text" name="fieldDes" class="form-control w-25" placeholder="필드설명">
		<input type="checkbox" name="fieldDefault" class="form-check-input" value="Y">
		<label class="form-check-label">기본</label>
		<i class="fa fa-minus deleteFieldInfoBtn" title="필드삭제"></i>
		<span class="form-bar"></span>
	</div>
</div>
<div id="add_text_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
			<div class="col-md-7">
				<input type="text" class="form-control" value="{{fieldDefault}}" placeholder="{{fieldPhd}}">
				<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
			</div>
			<div class="col-md-2">
				<i class="fa fa-gear modItem"></i>
				<i class="fa fa-minus deleteItem"></i>
				<div class="itemJsonDiv d-none">+#itemJson#+</div>
			</div>
		</div>
	</li>
</div>
<div id="add_textArea_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
			<div class="col-md-7">
				<textarea rows="5" cols="5" class="form-control" placeholder="{{fieldPhd}}">{{fieldDefault}}</textarea>
				<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
			</div>
			<div class="col-md-2">
				<i class="fa fa-gear modItem"></i>
				<i class="fa fa-minus deleteItem"></i>
				<div class="itemJsonDiv d-none">+#itemJson#+</div>
			</div>
		</div>
	</li>
</div>
<div id="add_file_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
			<div class="col-md-7">
				<input type="file" class="form-control">
				<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
			</div>
			<div class="col-md-2">
				<i class="fa fa-gear modItem"></i>
				<i class="fa fa-minus deleteItem"></i>
				<div class="itemJsonDiv d-none">+#itemJson#+</div>
			</div>
		</div>
	</li>
</div>
<div id="add_selectBox_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
				<div class="col-md-7">
					<select name="dumySelect" class="form-control">
						<option value="">선택</option>
						{{#fieldDatas}}
						<option value="{{fieldValue}}">{{fieldText}}</option>
						{{/fieldDatas}}
					</select>
					<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
				</div>
				<div class="col-md-2">
					<i class="fa fa-gear modItem"></i>
					<i class="fa fa-minus deleteItem"></i>
					<div class="itemJsonDiv d-none">+#itemJson#+</div>
				</div>
		</div>
	</li>
</div>
<div id="add_checkBox_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
			<div class="col-md-7">
				{{#fieldDatas}}
				<input class="form-check-input" type="checkbox" value="{{fieldValue}}">
				<label class="form-check-label">{{fieldText}}</label>
				{{/fieldDatas}}
				<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
			</div>
			<div class="col-md-2">
				<i class="fa fa-gear modItem"></i>
				<i class="fa fa-minus deleteItem"></i>
				<div class="itemJsonDiv d-none">+#itemJson#+</div>
			</div>
		</div>
	</li>
</div>
<div id="add_radio_div" type="x-tmpl-mustache" class="d-none">
	<li class="ui-state-default">
		<div class="form-group row">
			<label class="col-md-3 col-form-label">{{itemName}}</label>
			<div class="col-md-7">
				{{#fieldDatas}}
				<input class="form-check-input" type="radio" value="{{fieldValue}}">
				<label class="form-check-label">{{fieldText}}</label>
				{{/fieldDatas}}
				<input type="hidden" name="formItemSeq" value="{{formItemSeq}}">
			</div>
			<div class="col-md-2">
				<i class="fa fa-gear modItem"></i>
				<i class="fa fa-minus deleteItem"></i>
				<div class="itemJsonDiv d-none">+#itemJson#+</div>
			</div>
		</div>
	</li>
</div>
<div id="workItemListDiv" class="d-none">
		<#list formItemList as formItemInfo>
		<div class="work-item-data-list">
			<div class="itemSeq">${formItemInfo.formItemSeq}</div>
			<div class="itemType">${formItemInfo.formItemType}</div>
			<div class="itemJson">${formItemInfo.itemJson}</div>
		</div>
		</#list>
</div>