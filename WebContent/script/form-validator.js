String.prototype.trim=function(){ //开始结尾空格
	var pattern=/^\s*|\s*$/g;
	return this.replace(pattern,"");
}
String.prototype.getByteStr=function(bLength){
	    var len = 0; //截取固定字节数的字符串
		for (var i=0;i<this.length;i++)
		{if (this.charCodeAt(i)>255)
			    len+=2; 
			else 
			    len++;
			if(len>bLength){
			   return this.substring(0,i);
			}
		}
		return this;
}

String.prototype.byteLength=function(){
	    var len = 0; 
		for (var i=0;i<this.length;i++)
		{if (this.charCodeAt(i)>255)
			    len+=2; 
			else 
			    len++;
		}
		return len;
}
FormValidator = {	
    //必填 
	Require : /.+/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	//Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Phone : /\d{1,4}-\d{6,8}.*/,
	//TODO test
	Mobile : /^1\d{10}$/,
	//TODO test
	Url : /^http(s)?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/i,
	//两位小数 不能以0开始 Currency : /^\d+(\.\d{2})?$/,
	Currency : /^[1-9](\.\d{2})?$/, 
	//z正整数
	Number : /^[1-9]\d*$/,
	//整数 已经验证
	Integer :/^[-\+]?([1-9]\d*|0)$/,
	Double  :/^[-\+]?\d+(\.\d+)?$/,
	Double_1 :/^[-\+]?\d+(\.\d{1})?$/,
	Double_2 :/^[-\+]?\d+(\.\d{2})?$/,
	Double_3 :/^[-\+]?\d+(\.\d{3})?$/,
	Double_4 :/^[-\+]?\d+(\.\d{4})?$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,8}$/,
	English : /^[A-Za-z]+$/,
	Word : /^([A-Z]|[a-z]|\d)*$/,
	Chinese : /^[\u0391-\uFFE5]+$/,
	Username : /^[a-z]\w{3,}$/i,
	//6位密码
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return   !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",
	Filter :  "this.DoFilter(value, getAttribute('accept'))",
	Limit :   "this.limit(value.length,getAttribute('min'), getAttribute('max'))",
	LimitB :  "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
	Date :    "this.IsDate(value,getAttribute('format'))",
	Repeat :  "value ==document.getElementById(getAttribute('to')).value",
	Range  :  "getAttribute('min') <= (value|0) && (value|0) <= getAttribute('max')",
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
	Custom :  "this.Exec(value, getAttribute('regexp'))",
	Group :   "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
	IdCard :  "this.IsIdCard(value,getAttribute('to'))",
	MyFunction : "this.myFunction(getAttribute('funName'))",
	ErrorItem : [document.forms[0]],
	ErrorMessage :["以下原因导致提交失败：\t\t\t\t"],
	msgResource:{
	failed : '验证失败.',
	Require :'请输入值.',
	Email :  '请输入有效的邮件地址,如 username@163.com.',
	Phone :  '请输入正确的电话号码,如010-29392929',
	Mobile : '请输入正确的手机号码,如13801234567',
	Url : '请输入有效的URL地址.',
	IdCard : '请输入真实身份证号码',
	Currency : '货币最多两位小数',
	Number : '请输入正整数.',
	Integer : '请输入整数',
	Double : '请输入数字.',
	Double_1 : '请输入一位数.',
	Double_2 : '请输入两位数.',
	Double_3 : '请输入三位小数.',
	Double_4 : '请输入四位小数.',
	Zip : '请输入有效的邮政编码',
	QQ : '请输入有效的QQ号码.',
	English : '请输入英文字母.',
	Word : '请输入英文字母或数字.',
	Chinese : '请输入中文',
	Username : '请输入合法用户名',
	IsSafe :'请输入符合安全要求的密码',
	SafeString : '请输入符合安全要求的密码',
	Filter : "仅能选择是 %s 类型的文件",
	Limit : "输入长度限制为 [%s ,%s]",
	LimitB : "输入字节数为限制为 [%s ,%s]",
	Date :  "日期格式为: %s ",
	Repeat : "输入不一致",
	Range :  "输入范围 [%s ,%s]",
	Compare :"输入关系为%s",
	Custom : "输入不符合正则 %s ",
	Group :  "选择个数范围 %s-%s"
	},
	//输入数字，输入小数, 正负数,长度, 
	FormInit:function(formObj) {
	        //在页面bodyonload事件中去调用 ,实现及时验证
	        if(formObj==null){
	           alert('检测的form为空');
	           return ;
	        }
			var count = formObj.elements.length;
			for ( var i = 0; i < count; i++) {
			      var fieldObj=formObj.elements[i];
			      this.initElement(fieldObj);
			}
	},
	TestCheckField:function(fieldIdPre,length) {
	      //功能测试页面专用的
          for(var i=1;i<=length;i++){
            this.CheckField(fieldIdPre+i,3);
          }
	},
    CheckField:function(fieldId,mode) {
           //手动检测单个对象
           var fieldObj=fieldId;
           if(typeof fieldId =="string"){
              fieldObj=document.getElementById(fieldId);
              if(fieldObj==null){
                alert("对象没有发现");
                return ;
             }
           }
		   var formObj=null;
		   try{
			   formObj= fieldObj["form"]|| event.srcElement;
		   }catch(e){}
		   this.ErrorMessage.length = 1;
		   //出错的对象
		   this.ErrorItem.length = 1;
		   this.ErrorItem[0] = formObj;
		   this._checkedField(fieldObj);
		   
		   if (this.ErrorMessage.length > 1) {
			 this._showError(mode);
			 return false;
		   }
		   return true;
	},
	Validate : function(theForm,mode,callback) {
	    //验证formObj表单
		var formObj = theForm || event.srcElement;
		var count = formObj.elements.length;
		//以下者两个数组按照一一对应的
		//错误消息 
		this.ErrorMessage.length = 1;
		//出错的对象
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = formObj;
		for ( var i = 0; i < count; i++) {
		     var fieldObj=formObj.elements[i];
		     this._checkedField(fieldObj);

		}
		if (this.ErrorMessage.length > 1) {
			this._showError(mode);
			return false;
		}
		return true;
	},
	_checkedField:function(fieldObj){
	        //内部检测单个域的方法
	        if(!fieldObj["getAttribute"]){
		            return;
		    }
		    if(fieldObj["disabled"]){//不可用不做判断
					return;
			}
			if(fieldObj["type"]=="hidden"){ //隐藏不做判断
					return;
			}
			if(fieldObj.getAttribute("exclude_v")){ //排除在验证之外
					return;
			}
			with(fieldObj) {
				if(getAttribute("require") == "true"){
					//必填项不填写 
					 this.ClearErrShow(fieldObj);
					 if(value==""){
					     var msgReq=getAttribute("msgReq");
					     if(msgReq==null){msgReq=FormValidator.msgResource.Require;}
					     this.AddError(fieldObj, msgReq);
					     return;
					 }
				}
				var _dataTypeAttr = getAttribute("dataType");
			    if(_dataTypeAttr==null||""._dataTypeAttr){
			    	//没有设置验证的时候 
			    	return;
			    }
				//_dataType不是字符串的时候 不能验证||  验证必须被当前支持
				if (typeof (_dataTypeAttr) == "object")
					return;
				
				this.ClearErrShow(fieldObj);
				//非必填项不填写  这里注释掉是为了兼容 dateType="require"的情况
				if (value == ""){
					if(getAttribute("require") != "true" || _dataTypeAttr.indexOf("Require")==-1){
					     return;
					}
				}
				//替换空格
				_dataTypeAttr=_dataTypeAttr.replace(/\s*/gi,"");
			    var _dataTypeArray=_dataTypeAttr.split(",");
                
			    for(var dIndex=0;dIndex<_dataTypeArray.length; dIndex++){
					var _dataType = _dataTypeArray[dIndex]; 
					//||验证必须被当前支持
	                if(typeof (this[_dataType]) == "undefined"){
					   continue;
					}
					var msgTemp=getAttribute("msg"+dIndex);
					if(dIndex==0 && msgTemp==null){
						 msgTemp=getAttribute("msg");
					}
					if(msgTemp==null){
						msgTemp=FormValidator.msgResource[_dataType];
						if(msgTemp==null)
							msgTemp=FormValidator.msgResource["failed"];
					}

					var params=[];
					switch (_dataType) {		
						
					//Range ,Group,,Limit ,LimitB,Custom,Date,Repeat ,IdCard ,Compare ,SafeString ,myFunction,Filter,	
					case "Range":
					case "Group":
					case "Limit":
					case "LimitB":
						   if(params.length==0){
								params[0]=getAttribute("min");
								params[1]=getAttribute("max");
								msgTemp=FormValidator.msgFormat(msgTemp,params);
							}
					case "Custom":
						   if(params.length==0){
							  params[0]=getAttribute("regexp");
							  msgTemp=FormValidator.msgFormat(msgTemp,params);
							}
					case "Date":
						   if(params.length==0){
							  params[0]=getAttribute("format");
							  msgTemp=FormValidator.msgFormat(msgTemp,params);
							}
					case "Repeat":
					case "IdCard":	
					case "Compare":
					case "SafeString":
					case "MyFunction":
					case "Filter":
						  if(params.length==0){
							  params[0]=getAttribute("accept");
							  msgTemp=FormValidator.msgFormat(msgTemp,params);
						  }
					   // 以上的case 调用下列函数
						  if (!eval(this[_dataType])) {
							 this.AddError(fieldObj, msgTemp);
						  }
						  break;
					default:
					   //不在以上包换的调用正则表达式
						if (!this[_dataType].test(value)) {
							this.AddError(fieldObj, msgTemp);
						}
						break;
					}
				}//end for
			}//end with
	},
	_showError:function(mode){
	        //显示不同格式的错误信息
	        mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch (mode) {
				case 2:
					 for ( var i = 1; i < errCount; i++){
						this.ErrorItem[i].style.color = "red";
					 }
				case 1:
					 alert(this.ErrorMessage.join("\n"));
					 this.ErrorItem[1].focus();
					 break;
				case 3:
					for ( var i = 1; i < errCount; i++) {
						try {
						    var filedObj=this.ErrorItem[i];
						    var errMsg= this.ErrorMessage[i].replace(/\d+:/," *");
							this._showErrorField(filedObj,errMsg)
						} catch (e) {
							alert(e.description);
						}
					}
					this.ErrorItem[1].focus();
					break;
				default:
					alert(this.ErrorMessage.join("\n"));
					break;
			}
	},
	_showErrorField:function(filedObj,errMsg){
	    //在表单域后显示错误信息
	    var span = document.createElement("SPAN");
		span.id = "__ErrorMessagePanel";
		span.style.color = "#b01c1c";
		span.style.background="#ffebeb";
		span.style.fontSize= "11px";
		span.style.padding= "2px";
		span.style.border= "1px solid #f28788";
		filedObj.parentNode.appendChild(span);
		filedObj.style.border="1px solid  #b01c1c";
		span.innerHTML =errMsg;
	},
	ShowError:function(filedObj,errMsg){
	    //在表单域后显示错误信息
	   FormValidator.ClearErrShow(filedObj);
	   FormValidator._showErrorField(filedObj,errMsg)
	},
	ClearErrShow : function(elem) {
		//清除单个域上次验证信息
		with (elem) {
			if (style.color == "red")
				style.color = "";
			var lastNode = parentNode.childNodes[parentNode.childNodes.length - 1];
			if (lastNode.id == "__ErrorMessagePanel"){
				parentNode.removeChild(lastNode);
				//项目不同这里需要修改TDOD
				elem.style.border="1px solid  #999999"
			}else{
				
			}
		}
	},
	AddError : function(elementObj, str){
	    //添加每一个域的错误信息
		var  lastErroObj=this.ErrorItem[this.ErrorItem.length-1];
		if(elementObj!=lastErroObj){//
		    this.ErrorItem[this.ErrorItem.length]=elementObj;
		    this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length
				+ ":" + str;
		}else{
		     if(this.ErrorMessage.length>1){
                this.ErrorMessage[this.ErrorMessage.length-1] = this.ErrorMessage[this.ErrorMessage.length-1] +" | " + str;
             }
		}
	},
	myFunction : function(functionName) {
	    //用户自定义验证函数的验证
		if(window[functionName]){
		  var value= window[functionName]();
		  if(value==false){
		    return false;
		  }
		}
		return true;
	},
	limit : function(len, min, max) {
		min = min || 0;
		max = max || Number.MAX_VALUE;
		return min <= len && len <= max;
	},
	LenB : function(str) {
	     //字节长度
	    var len = 0; 
		for (var i=0;i<str.length;i++)
		{if (str.charCodeAt(i)>255)
			    len+=2; 
			else 
			    len++;
		}
		return len;
	},
	Exec : function(op, reg) {
		return new RegExp(reg, "g").test(op);
	},
	compare : function(op1, operator, op2) {
		switch (operator) {
			case "!=":
				return (op1 != op2);
			case ">":
				return (op1 > op2);
			case ">=":
				return (op1 >= op2);
			case "<":
				return (op1 < op2);
			case "<=":
				return (op1 <= op2);
			default:
				return (op1 == op2);
		}
	},
	MustChecked : function(fieldName, min, max) {
	    //radio选择个数
		var groups = document.getElementsByName(fieldName);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for ( var i = groups.length - 1; i >= 0; i--)
			if (groups[i].checked)
				hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	DoFilter : function(filePath, accept) {
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, accept.split(
				/\s*,\s*/).join("|")), "gi").test(filePath);
	},
	SetIdCardInfo:function(idCard,cardBirthDayId,sexFn){
	  //通过页面控件设置出生日期，通过回调函数设置性别
	   if(idCard.length==18){
	        var flag=  this.IsIdCard(idCard,cardBirthDayId);
	        if(flag==true){
		        var sexCode=0;//0是男 1是女
				if(idCard.length==15){
				   sexCode=parseInt(idCard.substr(15,1))%2;
				}
				if(idCard.length==18){
				   sexCode=parseInt(idCard.substr(17,1))%2;
				}
				var sexArray=['女','男'];
				if(sexFn){
				   sexFn(sexArray[sexCode]);
				}
			}
	   }
	   return;
	},
	IsIdCard :function(idCard,cardBirthdayId) {
	    idCard=idCard.toLowerCase();
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
		var area = [ '', '', '', '', '', '', '', '', '', '', '', '北京', '天津',
				'河北', '山西', '内蒙古', '', '', '', '', '', '辽宁', '吉林', '黑龙江', '',
				'', '', '', '', '', '', '上海', '江苏', '浙江', '安微', '福建', '江西',
				'山东', '', '', '', '河南', '湖北', '湖南', '广东', '广西', '海南', '', '',
				'', '重庆', '四川', '贵州', '云南', '西藏', '', '', '', '', '', '', '陕西',
				'甘肃', '青海', '宁夏', '新疆', '', '', '', '', '', '台湾', '', '', '',
				'', '', '', '', '', '', '香港', '澳门', '', '', '', '', '', '', '',
				'', '国外' ];
		var re = idCard.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if (re == null)
			return false;
		if (re[1] >= area.length || area[re[1]] == "")
			return false;
		if (re[2].length == 12) {
			Ai = idCard.substr(0, 17);
			date = [ re[9], re[10], re[11] ].join("-");
		} else {
			Ai = idCard.substr(0, 6) + "19" + idCard.substr(6);
			date = [ "19" + re[4], re[5], re[6] ].join("-");
		}
		if (!this.IsDate(date, "ymd"))
			return false;
		var sum = 0;
		for ( var i = 0; i <= 16; i++) {
			sum += Ai.charAt(i) * Wi[i];
		}
		
		Ai += verify.charAt(sum % 11);
		var  flag= (idCard.length == 15 || idCard.length == 18 && idCard == Ai);
		if(flag==true){ //设置出生日期表单项
			if(cardBirthdayId!=null && ""!=cardBirthdayId ){
			   var cardBirthdayObj=document.getElementById(cardBirthdayId);
			   if(cardBirthdayObj!=null){
			    cardBirthdayObj["value"]=date;
			   }
			}
		}
		return flag;
	},
	IsDate : function(dataStr, formatString) {
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch (formatString) {
		case "ymd":
			m = dataStr.match(new RegExp(
					"^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
			if (m == null)
				return false;
			day = m[6];
			month = m[5] * 1;
			year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
			break;
		case "dmy":
			m = dataStr.match(new RegExp(
					"^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
			if (m == null)
				return false;
			day = m[1];
			month = m[3] * 1;
			year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
			break;
		default:
			break;
		}
		if (!parseInt(month))
			return false;
		month = month == 0 ? 12 : month;
		var date = new Date(year, month - 1, day);
		return (typeof (date) == "object" && year == date.getFullYear()
				&& month == (date.getMonth() + 1) && day == date.getDate());
		function GetFullYear(y) {
			  return ((y < 30 ? "20" : "19") + y) | 0;
		}
	},
    msgFormat : function(msgTemplate,args) {
		args = args || [];
		var result = msgTemplate
		for (var i = 0; i < args.length; i++){
			result = result.replace(/%s/, args[i]);	
		}
		return result;
	},
    initElement:function(element){
	       var initChildren=true;//是否需要初始化孩子
	       var dataType=element.getAttribute("dataType");
	       if(dataType==null ||""==dataType){ return;}
	       
		   with (element){//editore.js处理  直接扔给另一个框架处理
					  language="javascript";
					   //onkeypress=this._editor_onkeypress;//
					  //limit max maxlength
	       }
	       this.addEvent(element, "keypress", this._editor_onkeypress);
	       //this.addEvent(element, "paste", this._editor_onchange);
	       this.addEvent(element, "change", this._editor_onchange);
	       //利用浏览器显示长度
		   if(dataType.indexOf("Limit")>-1){
		    var max= element.getAttribute("max");
		    if(max!=null||""!=max){
		       element["maxLength"]=max;
		    }
		   }
    },
	_editor_onkeypress:function(event) {
		if(!event) event=window.event;
		var target=event.srcElement?event.srcElement:event.target;
		if (target.readOnly){
			event.returnValue=false;
			return;
		}
		var result=true;
        var _keyCode=event.keyCode;
		if(event.which){//兼容性代码
		    _keyCode=event.which;
		}
		var keychar = String.fromCharCode(_keyCode);
		//alert("keyCode="+_keyCode +"  charCode="+event.charCode);
		//火狐  点 keyCode=46 charCode=46 
		//删除 keyCode=46 charCode=0
		//IE  
		// 点  keyCode=46 charCode=undefined
		// 删除  捕获不到
		//IE  
		// 点  keyCode=46 charCode=undefined
		// 删除  捕获不到
		//google
		if(_keyCode== 45 ||(_keyCode== 46  && event.charCode==0) ||_keyCode==8){
		    //keycode 8 =  BackSpace  
		    //keycode 45 = Insert 
            //keycode 46 = Delete 点 ，
		    return true;
		}
		var dataType=  target.getAttribute("dataType").split(",")[0].trim();
		switch (dataType){ 
			case "Number":// only "0-9" 正确
			 	  result=(_keyCode>=48 && _keyCode<=57 );
			 	  if(target.value=="" &&_keyCode==48){
			 	     result==false; //首位不能是零
			 	     break;
			 	  }
				  break;
			case "Integer"://已经完成 [+-]/d
			     result = (_keyCode>=48  && _keyCode<=57);
			     if(target.value=="" &&  _keyCode==48){//第一位不能输入零
			 	     result==false;
			 	     break;
			 	 }
			     if(result==false){
				     if(target.value==""){
				        var keychar = String.fromCharCode(_keyCode);
				        if(keychar=="-"||keychar=="+"){
				          result=true;
				        }
				     }
				  }
				  break;

			case "Mobile":
			      result=(_keyCode>=48 && _keyCode<=57);
			      if(target.value.length>11){//TODO test
			         result=false;
			      }
				  break;
			case "Currency":
			case "Double":
			      result = (_keyCode>=48  && _keyCode<=57);
			      if(target.value=="" &&_keyCode==48){
			 	     result==false;
			 	     break;
			 	  }
			      if(result==false){
				      if(target.value==""){
				         var keychar = String.fromCharCode(_keyCode);
				         if(keychar=="-"||keychar=="+"){
				            result=true;
				         }
				      }else if(target.value.indexOf("\.")==-1 && _keyCode==46){//小数点
				           result=true;
				      }
				   }
				   break;
			case "English":// only "/", ":", space and "A-Z"
				 result=((_keyCode>=65 && _keyCode<=90 )||( _keyCode>=97 && _keyCode<=122));
				 break;
			case "Word":// only "/", ":", space and "A-Z"
				 result=((_keyCode>=65 && _keyCode<=90 )||( _keyCode>=97 && _keyCode<=122)||(_keyCode>=48  && _keyCode<=57));
				 break;
			case "Limit"://字数 status 完成
				 //请使用 maxlength 属性
				 break;
			
			case "LimitB"://字节数 status 完成
			     var max=target.getAttribute("max");
			     if(max!=null && ""!=max){
			         var oldValue=target.value;
			         target.value=target.value.getByteStr(parseInt(max));
				 }
				 break;
			case "Phone":
				  result=true;
				  break;
			case "IdCard": // only "X" and "0-9" status 完成
			      if(target.value.length<18){
			            result=(_keyCode ==88 || (_keyCode>=48 && _keyCode<=57));
			      }else{
			            return false;
			      }
			      break;
		

		  }
		  ///**IE：
		  ///*	window.event.cancelBubble = true;//停止冒泡
		  //*	window.event.returnValue = false;//阻止事件的默认行为
		  //	Firefox：
		  //	event.preventDefault();// 取消事件的默认行为  
		  //	event.stopPropagation(); // 阻止事件的传播
	
		  if( event["preventDefault"] && result==false){//兼容性代码
		    event.preventDefault();
		    event.stopPropagation()
		  }
		  event.returnValue=result;
		  return result;
   },
   _editor_onchange:function(event) {
           //onchange事件
		   if(!event) event=window.event;
		   var target=event.srcElement?event.srcElement:event.target;
		   if (target.readOnly){
			 event.returnValue=false;
			 return;
		   }
	  	   FormValidator.CheckField(target,3);
   },
   addEvent: function(obj, type, fn) {
	   
      //添加事件 希望是和用户函数能兼容
	  if(obj["attachEvent"]) { //IE
		  obj.attachEvent('on' + type, fn);
	  } else{//Mozilla
		  obj.addEventListener(type, fn, false);
	  }
	  //removeEventListener(event,function,capture/bubble);
	  //Windows IE的格式如下:
	  //detachEvent(event,function);
	  
  }
}