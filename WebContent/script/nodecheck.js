  	function submitForm(formId) {
  		$("#"+formId).submit();
  	}
  	
	function setAllCheckboxState(name,state) {
  		
  		var elms = document.getElementsByName(name);
  		for(var i = 0; i < elms.length; i++) {
  			elms[i].checked = state;
  		}
  	}
  
  	function doCheckedItem(thisChecked){
  	
  		 var  id=thisChecked.name+"_Hander"
  		 doCheckedSyn(thisChecked.name,id);
  	}	
  	
  	
  	function showModal(msg,formId) {
  		if(confirm(msg)) {
  			submitForm(formId);
		} else {
  			return;
		}
  		
  	}
  	
  	function doCheckedSyn(itemsName,id){
  		
  		 var selectall = document.getElementById(id);
  		 var checkboxs = document.getElementsByName(itemsName);
  	 	 if(checkboxs && selectall){
  		 			for(var i=0;i<checkboxs.length;i++){
  		 				if(checkboxs[i].checked==false){
  		 					 selectall.checked=false;
  		 					 return;
  		 				}
  		 			 }
  		 			 selectall.checked=true;		
  	     }
  	}
    javascript:submitForm('studentExitForm')
  	function batch(checkboxName,msg,formId){
  	    if (!hasOneChecked(checkboxName)){
  	            alert('请选择要操作的对象!');
  	            return;
  	    }

  		 showModal(msg,formId);
  	}
  	
  	function hideModal() {
  		
  	}	
  	function hasOneChecked(name){
  		
  	    var items = document.getElementsByName(name);
  	   
  		for (var i = 0; i < items.length; i++){
  		    if(items[i].checked == true){
  		        return true;
  		    }
  		}
  	    return false;
  	}