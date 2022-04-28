//alert, confirm, blockUi 관련 선언
const ToastAlert = Swal.mixin({
	showConfirmButton: true,
	showClass: {
		popup: 'animate__animated animate__fadeInDown'
  	},
  	hideClass: {
    	popup: 'animate__animated animate__fadeOutUp'
  	}
});

const ToastConfirm = Swal.mixin({
	showConfirmButton: true,
	showCancelButton: true,
	confirmButtonText : "예",
	cancelButtonText : "아니오",
	showClass: {
		popup: 'animate__animated animate__fadeInDown'
  	},
  	hideClass: {
    	popup: 'animate__animated animate__fadeOutUp'
  	}
});

const ToastBlockUi = Swal.mixin({
	showConfirmButton: false,
	allowOutsideClick: false,
	padding: '3em',
  	background: '#fff',
  	backdrop: 'rgba(0, 0, 0, 0.7) left top no-repeat',
  	imageUrl: '/images/blockbusy.gif', 
	imageWidth: 15, 
	imageHeight: 15
});
//alert, confirm, blockUi 관련 선언

const ajaxCall = {
	mainCall:function(url, type, dataType, successCallBack){
		$.ajax({
			url:url,
			type:type,
			dataType:dataType,
			beforeSend:function(xhr){
				//csrf 토큰 추가시 활성화 필요
				//xhr.setRequestHeader(token_header, token);
			},
			success:function(response){
				if(!cmmnUtil.isEmpty(successCallBack)){
					successCallBack(response);
				}
			}
		});
	}
}

const cmmnUtil = {
	isEmpty:function(value){
		if(value=="" || value==null || value==undefined){
			return true;
		}else{
			return false;
		}
	},
	replaceNull:function(value){
		if(!value){
			return "";
		}else{
			return value;
		}		
	}
}
//input 태그 검증 및 설정 관련
const htmlInputClass = {
	setData:function(jsonData, targetObjId){
		let keys = Object.keys(jsonData);
		let targetObj = null;
		if(targetObjId==null || document.getElementById(targetObjId)==undefined){
			targetObj = document;
		}else{
			targetObj = document.querySelector('#'+targetObjId);
		}
		
		for (let i = 0; i < keys.length; i++) {
  			let key = keys[i];
  			
  			let chilNode = targetObj.querySelector('input[name="'+key+'"]');
  			if(chilNode==null || chilNode==undefined){chilNode = targetObj.querySelector('select[name="'+key+'"]');}
  			if(chilNode==null || chilNode==undefined){chilNode = targetObj.querySelector('textarea[name="'+key+'"]');}
  			if(chilNode==null || chilNode==undefined){chilNode = targetObj.querySelector('textarea[name="'+key+'"]');}
  			if(chilNode==null || chilNode==undefined){chilNode = targetObj.querySelector('[data-target="'+key+'"]');}
  			
  			if(chilNode!=null && chilNode!=undefined){
  				htmlInputClass.inputSetData(key, chilNode, jsonData[key]);
  			}
		}
	},
	inputSetData:function(targetName, targetObj, targetValue){
		if(targetObj.type=="text" || targetObj.type=="textarea" || targetObj.type=="hidden"){
			targetObj.value = targetValue;
		}else if(targetObj.type=="select-one" && targetObj.options!= undefined && targetObj.options.length>0){
			for(let i = 0; i < targetObj.options.length; i++) {
				if(targetObj.options[i].value==targetValue){
					targetObj.options[i].selected = true;
				}
			}
		}else if(targetObj!= undefined && targetObj.type=="checkbox"){
			let checkBoxList = document.getElementsByName(targetName);
			if(checkBoxList.length>0){
				for(let i = 0; i < checkBoxList.length; i++) {
					if(checkBoxList[i].value==targetValue){checkBoxList[i].checked=true;}
				}
			}
		}else if(targetObj!= undefined && targetObj.type=="radio"){
			let radioList = document.getElementsByName(targetName);
			if(radioList.length>0){
				for(let i = 0; i < radioList.length; i++) {
					if(radioList[i].value==targetValue){radioList[i].checked=true;}
				}
			}
		}else{
			targetObj.innerHTML = cmmnUtil.replaceNull(targetValue);
		}
	},
	reSetData:function(targetObjId, hiddenChk){
		let targetObj = null;
		if(targetObjId==null || document.getElementById(targetObjId)==undefined){
			targetObj = document;
		}else{
			targetObj = document.querySelector('#'+targetObjId);
		}
		
		let inputList = [];
		htmlInputClass.inputChildList(targetObj, inputList, hiddenChk);
		
		for(let i = 0; i < inputList.length; i++) {
			htmlInputClass.inputReSetData(inputList[i]);
		}
	},
	inputReSetData:function(targetObj){
		if(targetObj.type=="text" || targetObj.type=="textarea" || targetObj.type=="hidden"){
			targetObj.value = "";
		}else if(targetObj.type=="select-one" && targetObj.options!= undefined && targetObj.options.length>0){
			targetObj.options[0].selected = true;
		}else if(targetObj.type=="checkbox" && targetObj!= undefined){
			let checkBoxList = document.getElementsByName(targetObj.name);
			if(checkBoxList.length>0){
				for(let i = 0; i < checkBoxList.length; i++) {
					checkBoxList[i].checked=false;
				}
			}
		}else if(targetObj.type=="radio"  && targetObj!= undefined){
			let radioList = document.getElementsByName(targetObj.name);
			if(radioList.length>0){
				for(let i = 0; i < radioList.length; i++) {
					radioList[i].checked=false;
				}
			}
		}else{
			targetObj.innerHTML = "";
		}
	},
	inputChildList:function(targetObj, inputList, hiddenChk){
		let inputChildren = targetObj.children;
		if(inputChildren.length>0){
			for(let i = 0; i < inputChildren.length; i++) {
				if(inputChildren[i].type!=undefined && (inputChildren[i].type=="text" || inputChildren[i].type=="select-one" || inputChildren[i].type=="checkbox" || inputChildren[i].type=="radio" || inputChildren[i].type=="textarea")){
					inputList.push(inputChildren[i]);
				}
				if(hiddenChk){
					if(inputChildren[i].type!=undefined && inputChildren[i].type=="hidden"){
						inputList.push(inputChildren[i]);
					}
				}
				//class명이 TempSettingArea(임시설정영역)인 경우 데이터 셋팅 진행
				if(inputChildren[i].getAttribute("class")!=null && inputChildren[i].getAttribute("class").indexOf("TempSettingArea")!=-1){
					inputList.push(inputChildren[i]);
				}
				htmlInputClass.inputChildList(inputChildren[i], inputList);
			}
		}
	}
}
//input 태그 검증 및 설정 관련

//validator 사용시 alert메시지 출력 되게 수정
const validatorReset = {
	set_defaults:function(){
		//페이지 로드 후 설정 동작하게(js, alert관련 내용이 먼저 로드 되어야 해서)
		$.validator.setDefaults({
		    onkeyup:false,
		    onclick:false,
		    onfocusout:false,
		    ignoreTitle: true,
		    showErrors:function(errorMap, errorList){
		        if(this.numberOfInvalids()) {
		        	let targetTitle = errorList[0].message; 
		        	try{
		        		this.errorList[0].element.focus();
		        		targetTitle = this.errorList[0].element.title+"은(는) ";
		        	}catch(error){}
		        	
		            ToastAlert.fire({	icon: 'error', title: targetTitle+errorList[0].message});
		        }
		    }
		});
	}
}

$("document").ready(function() {
	$(".numOnly").on('keyup',function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}		
	});
	$(".numOnly").css('imeMode', 'disabled');
});

jQuery.fn.serializeObject = function() {
    let obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            let arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }
 
    return obj;
};