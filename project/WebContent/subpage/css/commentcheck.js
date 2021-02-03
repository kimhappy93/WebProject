function commentcheck(){
	        	var id = "<%=id%>";
	        	
	        	if(id == "null"){
		    		alert("댓글을 작성하려면 로그인 해 주세요.");
		    		return false;
		    	}
	        	
	        	if(document.commentform.M_CONTENT.value==""){
	        		document.commentform.M_CONTENT.focus();
	        		alert("내용을 입력해 주세요.");
	        		
	        		return false;
	        	}
	        	
	        	else return true;
			};